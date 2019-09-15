package cardealer.domain.dto.view;

import java.io.Serializable;

public class SupplierNonImporterViewDto implements Serializable {

    private Long id;

    private String name;

    private Integer partsCount;

    public SupplierNonImporterViewDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}
