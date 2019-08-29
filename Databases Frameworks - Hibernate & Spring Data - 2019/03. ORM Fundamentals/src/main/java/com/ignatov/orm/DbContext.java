package com.ignatov.orm;

import java.sql.SQLException;

public interface DbContext<T> {
    boolean persist(T entity) throws ClassNotFoundException, IllegalAccessException, SQLException; //insert or update

    Iterable<T> find() throws SQLException;

    Iterable<T> find(String whereClause) throws SQLException;

    T findFirst() throws SQLException;

    T findFirst(String where) throws SQLException;

    T findById(long id) throws SQLException;
}
