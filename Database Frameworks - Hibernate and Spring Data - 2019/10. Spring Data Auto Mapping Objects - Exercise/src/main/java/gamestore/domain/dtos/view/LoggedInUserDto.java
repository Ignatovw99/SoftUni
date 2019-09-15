package gamestore.domain.dtos.view;

import gamestore.domain.entities.enums.Role;
import gamestore.domain.validators.email.Email;

public class LoggedInUserDto {

    private Long id;

    private String email;

    private String fullName;

    private Role role;

    public LoggedInUserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
