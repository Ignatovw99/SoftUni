package cardealer.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    private String name;

    private Boolean isImporter;

    public Supplier() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_importer", nullable = false)
    public Boolean getIsImporter() {
        return isImporter;
    }

    public void setIsImporter(Boolean isImporter) {
        this.isImporter = isImporter;
    }
}
