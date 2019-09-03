package utils;

import entities.Student;
import org.hibernate.Session;

import java.util.Date;

public class HibernatePersistData {
    public static void execute() {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();

        Student student1 = new Student();
        student1.setName("John");
        student1.setRegistrationDate(new Date());

        session.persist(student1);
        student1.setName("Changed name");

        Student retrievedStudent1 = session.get(Student.class, 1); //Where student id = 1
        System.out.println(retrievedStudent1);

        Student student2 = new Student("Adam", new Date());
        Student student3 = new Student("Alan", new Date());
        Student student4 = new Student("Alex", new Date());

        session.persist(student2);
        session.persist(student3);
        session.persist(student4);

        session.getTransaction()
                .commit();
        session.close();
    }
}
