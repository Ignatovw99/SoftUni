package kayzer.domain.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity implements GrantedAuthority {

    private String authority;

    private int power;

    public UserRole() {
    }

    @Override
    @Column(name = "authority", nullable = false)
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Column(name = "power", nullable = false, unique = true)
    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
