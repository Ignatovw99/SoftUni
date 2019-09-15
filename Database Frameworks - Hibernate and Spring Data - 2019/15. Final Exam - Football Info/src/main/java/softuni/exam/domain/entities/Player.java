package softuni.exam.domain.entities;

import org.hibernate.validator.constraints.Length;
import softuni.exam.domain.entities.base.BaseEntity;
import softuni.exam.domain.entities.enums.Position;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    private String firstName;

    private String lastName;

    private Integer number;

    private BigDecimal salary;

    private Position position;

    private Picture picture;

    private Team team;

    public Player() {
    }

    @Column(name = "first_name", nullable = false)
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    @NotNull
    @Length(min = 3, max = 15)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "number", nullable = false)
    @NotNull
    @Min(1)
    @Max(99)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Column(name = "salary", nullable = false)
    @NotNull
    @DecimalMin(value = "0")
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Column(name = "position", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @ManyToOne(targetEntity = Picture.class)
    @JoinColumn(name = "picture_id", referencedColumnName = "id", nullable = false)
    @NotNull
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    @NotNull
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
