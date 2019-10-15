package cardealer.domain.models.view;

import java.math.BigDecimal;
import java.util.Set;

public class CarWithPartsViewModel {

    private String make;

    private String model;

    private Long travelledDistance;

    private BigDecimal price;

    private Set<PartViewModel> parts;

    public CarWithPartsViewModel() {
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

    public Set<PartViewModel> getParts() {
        return parts;
    }

    public void setParts(Set<PartViewModel> parts) {
        this.parts = parts;
    }
}
