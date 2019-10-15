package cardealer.domain.models.service.parts;

import cardealer.domain.entities.Supplier;
import cardealer.domain.models.service.cars.CarServiceModel;

import java.math.BigDecimal;
import java.util.Set;

public class PartServiceModel {

    private Long id;

    private String name;

    private BigDecimal price;

    private Long quantity;

    private Supplier supplier;

    private Set<CarServiceModel> cars;

    public PartServiceModel() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<CarServiceModel> getCars() {
        return cars;
    }

    public void setCars(Set<CarServiceModel> cars) {
        this.cars = cars;
    }
}
