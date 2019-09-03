package utils;

import entities.Student;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateRetrieveDataByCriteria {
    public static void execute() {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        criteriaQuery.select(root)
                .where(
                        builder.or(
                                builder.like(root.get("name"), "F%"),
                                builder.like(root.get("name"), "A%")
                        ));

        List<Student> students = session.createQuery(criteriaQuery)
                .getResultList();

        for (Student student : students) {
            System.out.println(student);
        }

        session.getTransaction()
                .commit();
        session.close();
    }
}
