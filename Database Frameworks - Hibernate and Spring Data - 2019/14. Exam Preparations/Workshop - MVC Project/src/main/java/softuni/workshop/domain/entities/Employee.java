package softuni.workshop.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    private String firstName;

    private String lastName;

    private Integer age;

    private Project project;

    public Employee() {
    }

    @Column(name = "first_name", nullable = false)
    @Length(min = 3, max = 25, message = "First name should be between 3 and 25 symbols.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    @Length(min = 3, max = 25, message = "Last name should be between 3 and 25 symbols.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age", nullable = false)
    @Min(value = 18, message = "The minimum age to start a job is 18.")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToOne(targetEntity = Project.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
