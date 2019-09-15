package alararestaurant.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    private String name;

    private Integer age;

    private Position position;

    private Set<Order> orders;

    public Employee() {
    }

    @Column(name = "name", nullable = false)
    @Length(min = 3, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "age", nullable = false)
    @Min(value = 15)
    @Max(value = 80)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToOne(targetEntity = Position.class)
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false)
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @OneToMany(targetEntity = Order.class, mappedBy = "employee")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
