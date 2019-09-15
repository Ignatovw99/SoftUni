package softuni.exam.domain.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class PlayerImportDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer number;

    @Expose
    private BigDecimal salary;

    @Expose
    private String position;

    @Expose
    private PictureImportJsonDto picture;

    @Expose
    private TeamImportJsonDto team;

    public PlayerImportDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public PictureImportJsonDto getPicture() {
        return picture;
    }

    public void setPicture(PictureImportJsonDto picture) {
        this.picture = picture;
    }

    public TeamImportJsonDto getTeam() {
        return team;
    }

    public void setTeam(TeamImportJsonDto team) {
        this.team = team;
    }
}
