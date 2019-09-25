package residentevil.domain.models.binding;

import java.util.Set;

public class UserRoleEditBindingModel {

    private Set<String> authorities;

    public UserRoleEditBindingModel() {
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
