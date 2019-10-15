package cardealer.domain.models.service.sales;

import cardealer.domain.models.service.cars.CarServiceModel;
import cardealer.domain.models.service.customers.CustomerServiceModel;

import java.math.BigDecimal;

public class SaleDetailsServiceModel {

    private CarServiceModel car;

    private CustomerServiceModel customer;

    private BigDecimal priceWithoutDiscount;

    private BigDecimal priceWithDiscount;

    private Double discount;

    public SaleDetailsServiceModel() {
    }

    public CarServiceModel getCar() {
        return car;
    }

    public void setCar(CarServiceModel car) {
        this.car = car;
    }

    public CustomerServiceModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerServiceModel customer) {
        this.customer = customer;
    }

    public BigDecimal getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public void setPriceWithoutDiscount(BigDecimal priceWithoutDiscount) {
        this.priceWithoutDiscount = priceWithoutDiscount;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
