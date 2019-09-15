package productsshop.domain.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersAndProductsRootDto {

    @XmlAttribute(name = "count")
    private Integer count;

    @XmlElement(name = "user")
    private List<UserWithAllSoldProductsDto> users;

    public UsersAndProductsRootDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserWithAllSoldProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithAllSoldProductsDto> users) {
        this.users = users;
    }
}
