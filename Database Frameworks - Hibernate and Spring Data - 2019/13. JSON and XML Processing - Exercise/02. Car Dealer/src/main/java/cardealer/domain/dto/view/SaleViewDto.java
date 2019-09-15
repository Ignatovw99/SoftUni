package cardealer.domain.dto.view;

import java.io.Serializable;

public class SaleViewDto implements Serializable {

    private Double discount;

    private CarViewDto car;

    public SaleViewDto() {
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public CarViewDto getCar() {
        return car;
    }

    public void setCar(CarViewDto car) {
        this.car = car;
    }
}
