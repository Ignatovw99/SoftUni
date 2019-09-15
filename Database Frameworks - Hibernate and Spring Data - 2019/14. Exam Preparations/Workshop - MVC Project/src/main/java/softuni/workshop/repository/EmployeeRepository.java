package softuni.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.workshop.domain.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByAgeGreaterThan(Integer age);

    List<Employee> findAllByFirstNameOrderById(String name);

    List<Employee> findAllByProjectName(String projectName);
}
