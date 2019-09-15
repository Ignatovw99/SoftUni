package softuni.workshop.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    private String name;

    private String description;

    private Boolean isFinished;

    private BigDecimal payment;

    private String startDate;

    private Company company;

    private Set<Employee> employees;

    public Project() {
    }

    @Column(name = "name", nullable = false)
    @Length(min = 3, max = 25, message = "Project's name should be between 3 and 25 symbols.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false)
    @Length(min = 5, max = 2000, message = "Description should be between 5 and 2000 symbols.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "is_finished")
    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean finished) {
        isFinished = finished;
    }

    @Column(name = "payment", nullable = false)
    @Min(value = 1000, message = "The minimum payment value is 1000.")
    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Column(name = "start_date")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @ManyToOne(targetEntity = Company.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToMany(targetEntity = Employee.class, mappedBy = "project", fetch = FetchType.EAGER)
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
