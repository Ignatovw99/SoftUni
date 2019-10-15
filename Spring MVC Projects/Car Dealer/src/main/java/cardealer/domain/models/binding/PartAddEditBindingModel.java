package cardealer.domain.models.binding;

import cardealer.common.validations.annotations.IsSupplierPresent;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class PartAddEditBindingModel {

    @Length(min = 2, max = 25, message = "The name should be between 2 and 25 symbols")
    private String name;

    @DecimalMin(value = "0.01", message = "The price should be positive")
    private BigDecimal price;

    @Min(value = 1, message = "The quantity should be at least 1")
    private Long quantity;

    @IsSupplierPresent
    private String supplierName;

    public PartAddEditBindingModel() {
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
