package productsshop.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Set;

public class UserSalesDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer age;

    @Expose
    private SoldProductsWithCountDto soldProducts;

    public UserSalesDto() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SoldProductsWithCountDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductsWithCountDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
