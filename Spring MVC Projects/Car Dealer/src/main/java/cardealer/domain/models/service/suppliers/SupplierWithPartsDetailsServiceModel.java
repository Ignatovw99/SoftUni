package cardealer.domain.models.service.suppliers;

public class SupplierWithPartsDetailsServiceModel {

    private Long id;

    private String name;

    private Integer numberOfOfferedParts;

    public SupplierWithPartsDetailsServiceModel() {
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

    public Integer getNumberOfOfferedParts() {
        return numberOfOfferedParts;
    }

    public void setNumberOfOfferedParts(Integer numberOfOfferedParts) {
        this.numberOfOfferedParts = numberOfOfferedParts;
    }
}
