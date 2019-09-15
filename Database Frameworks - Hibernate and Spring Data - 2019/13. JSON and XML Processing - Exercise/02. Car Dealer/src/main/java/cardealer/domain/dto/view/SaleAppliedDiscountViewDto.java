package cardealer.domain.dto.view;

import cardealer.domain.dto.binding.CarDto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleAppliedDiscountViewDto implements Serializable {

    private CarDto car;

    private String customerName;

    private Double discount;

    private BigDecimal price;

    private BigDecimal priceWithDiscount;

    public SaleAppliedDiscountViewDto() {
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
