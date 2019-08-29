package com.ignatov;

import com.ignatov.entities.Department;
import com.ignatov.entities.User;
import com.ignatov.orm.Connector;
import com.ignatov.orm.DbContext;
import com.ignatov.orm.EntityManager;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, IllegalAccessException, ClassNotFoundException {
        Connector.createConnection("root", "1234qwer", "soft_uni");
        Connection connection = Connector.getConnection();
        //DbContext<User> usersDbContext = new EntityManager<>(connection);

        //usersDbContext.find(User.class)
                //.forEach(System.out::println);

        //connection.close();

        DbContext<Department> departmentsDbContext = new EntityManager<>(connection, Department.class);

        departmentsDbContext
                .find()
                .forEach(System.out::println);

        System.out.println("____________________________________");

        departmentsDbContext
                .find("name LIKE 'F%'")
                .forEach(System.out::println);

        System.out.println("--------------");

        System.out.println(departmentsDbContext
                .findFirst());

        System.out.println("---------------------");

        System.out.println(departmentsDbContext
                .findFirst("name LIKE 'F%'"));

        System.out.println(departmentsDbContext.findById(7));

        Department department = departmentsDbContext.findById(17);
        department.setName("Office");

        departmentsDbContext.persist(department);

        departmentsDbContext.find().forEach(System.out::println);
        connection.close();
    }
}
