package softuni.workshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class EmployeeExportDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer age;

    @Expose
    private ProjectExportDto project;

    public EmployeeExportDto() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProjectExportDto getProject() {
        return project;
    }

    public void setProject(ProjectExportDto project) {
        this.project = project;
    }
}
