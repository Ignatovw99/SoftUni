package cardealer.domain.models.service.customers;

import java.math.BigDecimal;

public class CustomerFinancialDetailsServiceModel {

    private String name;

    private Integer boughtCars;

    private BigDecimal totalSpentMoney;

    public CustomerFinancialDetailsServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getTotalSpentMoney() {
        return totalSpentMoney;
    }

    public void setTotalSpentMoney(BigDecimal totalSpentMoney) {
        this.totalSpentMoney = totalSpentMoney;
    }
}
