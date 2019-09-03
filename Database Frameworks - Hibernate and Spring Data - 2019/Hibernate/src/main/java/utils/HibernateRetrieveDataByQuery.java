package utils;

import entities.Student;
import org.hibernate.Session;

public class HibernateRetrieveDataByQuery {
    public static void execute() {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();

        String hibernateQuery = "FROM Student"; //If it is red underlined, the Hibernate Facet(XML) has to be added
        session.createQuery(hibernateQuery, Student.class)
                .list()
                .forEach(System.out::println);

        System.out.println(
                session.createQuery("FROM Student WHERE name = 'Alan'")
                        .getSingleResult()
        );

        session.getTransaction()
                .commit();
        session.close();
    }
}
