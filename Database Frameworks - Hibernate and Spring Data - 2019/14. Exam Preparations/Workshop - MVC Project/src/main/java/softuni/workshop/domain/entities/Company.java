package softuni.workshop.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{

    private String name;

    private Set<Project> projects;

    public Company() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Project.class, mappedBy = "company", fetch = FetchType.EAGER)
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
