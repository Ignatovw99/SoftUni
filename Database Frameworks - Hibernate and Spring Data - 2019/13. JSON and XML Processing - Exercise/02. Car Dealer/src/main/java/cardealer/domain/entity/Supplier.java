package cardealer.domain.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    private String name;

    private Boolean isImporter;

    private Set<Part> parts;

    public Supplier() {
        this.parts = new HashSet<>();
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_importer")
    public Boolean getIsImporter() {
        return isImporter;
    }

    public void setIsImporter(Boolean importer) {
        isImporter = importer;
    }

    @OneToMany(targetEntity = Part.class, mappedBy = "supplier")
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
