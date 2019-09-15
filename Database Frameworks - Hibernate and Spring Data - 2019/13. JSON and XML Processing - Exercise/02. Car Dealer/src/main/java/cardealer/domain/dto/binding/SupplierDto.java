package cardealer.domain.dto.binding;

import java.io.Serializable;

public class SupplierDto implements Serializable {

    private String name;

    private Boolean isImporter;

    public SupplierDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
