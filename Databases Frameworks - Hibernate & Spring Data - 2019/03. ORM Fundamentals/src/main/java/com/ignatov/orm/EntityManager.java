package com.ignatov.orm;

import com.ignatov.orm.annotations.Column;
import com.ignatov.orm.annotations.Entity;
import com.ignatov.orm.annotations.PrimaryKey;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<T> implements DbContext<T> {
    private static final String SELECT_QUERY_TEMPLATE = "SELECT * FROM `%s`;";
    private static final String SELECT_WHERE_QUERY_TEMPLATE = "SELECT * FROM `%s` WHERE %s;";
    private static final String SELECT_SINGLE_QUERY_TEMPLATE = "SELECT * FROM `%s` LIMIT 1;";
    private static final String SELECT_SINGLE_WHERE_QUERY_TEMPLATE = "SELECT * FROM `%s` WHERE %s LIMIT 1;";

    private static final String INSERT_QUERY_TEMPLATE = "INSERT INTO `%s`(%s) VALUES (%s);";
    private static final String UPDATE_QUERY_TEMPLATE = "UPDATE `{0}` SET {1} WHERE {2} = {3};";

    private static final String WHERE_PRIMARY_KEY_TEMPLATE = "%s = %d";
    private static final String SET_QUERY_TEMPLATE = "{0} = {1}";

    private Connection connection;
    private Class<T> table;

    public EntityManager(Connection connection, Class<T> clazz) {
        this.connection = connection;
        this.table = clazz;
    }

    public boolean persist(T entity) throws IllegalAccessException, SQLException {
        Field primaryKeyField = this.getPrimaryKeyField();
        primaryKeyField.setAccessible(true);
        Object primaryKeyValue = primaryKeyField.get(entity);

        if (primaryKeyValue == null || (long) primaryKeyValue <= 0) {
            return this.doInsert(entity);
        }
        return this.doUpdate(entity, primaryKeyField);
    }

    private boolean doInsert(T entity) throws SQLException {
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        this.getColumnFields()
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        String columnName = field.getAnnotation(Column.class).name();
                        Object columnValue = field.get(entity);

                        columns.add(columnName);
                        values.add(columnValue);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        String columnNames = String.join(", ", columns);
        String columnValues = values
                .stream()
                .map(value -> {
                    if (value instanceof String) {
                        return "\'" + value + "\'";
                    }
                    return value;
                })
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        String insertQuery = String.format(EntityManager.INSERT_QUERY_TEMPLATE, this.getTableName(), columnNames, columnValues);

        return this.connection.prepareStatement(insertQuery).execute();
    }

    private boolean doUpdate(T entity, Field primaryKeyField) throws SQLException, IllegalAccessException {
        //TODO - Update only columns, which were updated!

        List<String> updateQueries = this.getColumnFields().stream()
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        String columnName = field.getAnnotation(Column.class)
                                .name();
                        Object columnValue = field.get(entity);
                        if (columnValue instanceof String) {
                            columnValue = "\'" + columnValue + "\'";
                        }

                        return MessageFormat.format(
                                SET_QUERY_TEMPLATE,
                                columnName,
                                columnValue
                        );
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());

        String updateQueriesString = String.join(", ", updateQueries);
        String primaryKeyName = primaryKeyField
                .getAnnotation(PrimaryKey.class)
                .name();
        long primaryKeyValue = (long) primaryKeyField
                .get(entity);

        String queryString = MessageFormat.format(
                UPDATE_QUERY_TEMPLATE,
                this.getTableName(),
                updateQueriesString,
                primaryKeyName,
                primaryKeyValue
        );

        return this.connection.prepareStatement(queryString)
                .execute();
    }

    public Iterable<T> find() throws SQLException {
        return this.find(null);
    }

    public Iterable<T> find(String whereClause) throws SQLException {
        String queryTemplate =
                whereClause == null
                ? EntityManager.SELECT_QUERY_TEMPLATE
                : EntityManager.SELECT_WHERE_QUERY_TEMPLATE;

        return this.find(queryTemplate, whereClause);
    }

    public T findFirst() throws SQLException {
        return this.findFirst(null);
    }

    public T findFirst(String where) throws SQLException {
        String queryTemplate =
                where == null
                ? EntityManager.SELECT_SINGLE_QUERY_TEMPLATE
                : EntityManager.SELECT_SINGLE_WHERE_QUERY_TEMPLATE;

        return this.find(queryTemplate, where).get(0);
    }

    public T findById(long id) throws SQLException {
        String primaryKeyColumnName = this.getPrimaryKeyField().getAnnotation(PrimaryKey.class).name();
        String whereQuery = String.format(
                EntityManager.WHERE_PRIMARY_KEY_TEMPLATE,
                primaryKeyColumnName,
                id
        );

        return this.findFirst(whereQuery);
    }

    private List<T> find(String queryTemplate, String where) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(
                String.format(queryTemplate, this.getTableName(), where) //Result Query String
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        return toList(resultSet);
    }

    private String getTableName() {
        Annotation annotation = Arrays.stream(this.table.getAnnotations())
                .filter(a -> a.annotationType() == Entity.class)
                .findFirst()
                .orElse(null);

        String tableName;

        if (annotation == null) {
            tableName = this.table.getSimpleName().toLowerCase() + "s";
        } else {
            tableName = this.table.getAnnotation(Entity.class).name();
        }

        return tableName;
    }

    private List<T> toList(ResultSet resultSet) throws SQLException {
        List<T> result = new ArrayList<>();
        while (resultSet.next()) {
            T entity = this.createEntity(resultSet);
            result.add(entity);
        }
        return result;
    }

    private T createEntity(ResultSet resultSet) {
        T result = null;
        try {
            T entity = this.table.getConstructor().newInstance();

            Field primaryKeyField = this.getPrimaryKeyField();
            this.setPrimaryKeyField(primaryKeyField, entity, resultSet);

            List<Field> columnFields = this.getColumnFields();

            columnFields
                    .forEach(field -> this.fillField(field, entity, resultSet));
            result = entity;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Field getPrimaryKeyField() {
        return Arrays.stream(this.table.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Class" + this.table.getSimpleName() + "does not have a primary key!"));
    }

    private void setPrimaryKeyField(Field primaryKeyField, T entity, ResultSet resultSet) throws SQLException, IllegalAccessException {
        String primaryKeyColumnName = primaryKeyField.getAnnotation(PrimaryKey.class).name();
        primaryKeyField.setAccessible(true);
        int primaryKeyColumnValue = resultSet.getInt(primaryKeyColumnName);
        primaryKeyField.set(entity, primaryKeyColumnValue);
    }

    private List<Field> getColumnFields() {
        return Arrays.stream(this.table.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());
    }

    private void fillField(Field field, T entity, ResultSet resultSet) {
        String columnName = field.getAnnotation(Column.class)
                .name();
        try {
            field.setAccessible(true);
            if (field.getType() == String.class) {
                String value = resultSet.getString(columnName);
                field.set(entity, value);
            } else if (field.getType() == Integer.class || field.getType() == int.class) {
                int value = resultSet.getInt(columnName);
                field.set(entity, value);
            } else if (field.getType() == Date.class) {
                Date value = resultSet.getDate(columnName);
                field.set(entity, value);
            } else if (field.getType() == long.class || field.getType() == Long.class) {
                long value = resultSet.getLong(columnName);
                field.set(entity, value);
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
