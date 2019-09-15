package cardealer.domain.dto.view;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class OrderedCustomerViewDto implements Serializable {

    private Long id;

    private String name;

    private Date birthDate;

    private Boolean isYoungDriver;

    private Set<SaleViewDto> sales;

    public OrderedCustomerViewDto() {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleViewDto> getSales() {
        return sales;
    }

    public void setSales(Set<SaleViewDto> sales) {
        this.sales = sales;
    }
}
