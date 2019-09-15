package productsshop.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Set;

public class UserCountSalesDto implements Serializable {

    @Expose
    private Integer usersCount;

    @Expose
    private Set<UserSalesDto> users;

    public UserCountSalesDto() {
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public Set<UserSalesDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserSalesDto> users) {
        this.users = users;
    }
}
