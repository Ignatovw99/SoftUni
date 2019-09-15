package productsshop.domain.dto;

import com.google.gson.annotations.Expose;
import productsshop.domain.entity.Product;

import java.io.Serializable;
import java.util.Set;

public class UserFirstAndLastNamesWithSoldProductsDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Set<ProductNamePriceBuyerFirstAndLastNames> soldProducts;

    public UserFirstAndLastNamesWithSoldProductsDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductNamePriceBuyerFirstAndLastNames> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductNamePriceBuyerFirstAndLastNames> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
