package cardealer.domain.models.view;

import java.math.BigDecimal;

public class CustomerFinancialDetailsViewModel {

    private String name;

    private Integer boughtCars;

    private BigDecimal totalSpentMoney;

    public CustomerFinancialDetailsViewModel() {
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
