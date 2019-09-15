package productsshop.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSoldProductsRootDto {

    @XmlElement(name = "user")
    private List<UserWithSoldProduct> userWithSoldProducts;

    public UsersWithSoldProductsRootDto() {
    }

    public List<UserWithSoldProduct> getUserWithSoldProducts() {
        return userWithSoldProducts;
    }

    public void setUserWithSoldProducts(List<UserWithSoldProduct> userWithSoldProducts) {
        this.userWithSoldProducts = userWithSoldProducts;
    }
}
