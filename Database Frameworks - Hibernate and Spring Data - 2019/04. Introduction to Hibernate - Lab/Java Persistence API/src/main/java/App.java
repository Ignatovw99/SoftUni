import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("school");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Student student = new Student();
        student.setName("Teo");
        student.setBirthDate(new Date());

        entityManager.persist(student);

        Student foundStudent = entityManager.find(Student.class, 1);
        foundStudent.setName("New name");
        entityManager.persist(foundStudent);

        entityManager.detach(foundStudent);
        foundStudent.setName("Name after detachment");
        entityManager.merge(foundStudent);

        foundStudent = entityManager.find(Student.class, 1);
        System.out.println(foundStudent);
        entityManager.remove(foundStudent);

        entityManager.getTransaction()
                .commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
