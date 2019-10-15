package cardealer.domain.models.service.cars;

import cardealer.domain.models.service.parts.PartServiceModel;

import java.math.BigDecimal;
import java.util.Set;

public class CarServiceModel {

    private String make;

    private String model;

    private Long travelledDistance;

    private BigDecimal price;

    private Set<PartServiceModel> parts;

    public CarServiceModel() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<PartServiceModel> getParts() {
        return parts;
    }

    public void setParts(Set<PartServiceModel> parts) {
        this.parts = parts;
    }
}
