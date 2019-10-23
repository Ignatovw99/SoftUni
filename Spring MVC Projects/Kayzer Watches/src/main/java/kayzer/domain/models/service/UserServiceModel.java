package kayzer.domain.models.service;

import kayzer.domain.entities.UserRole;

import java.util.Optional;
import java.util.Set;

public class UserServiceModel {

    private String id;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String uniqueCitizenNumber;

    private Set<UserRole> authorities;

    public UserServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUniqueCitizenNumber() {
        return uniqueCitizenNumber;
    }

    public void setUniqueCitizenNumber(String uniqueCitizenNumber) {
        this.uniqueCitizenNumber = uniqueCitizenNumber;
    }

    public Set<UserRole> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserRole> authorities) {
        this.authorities = authorities;
    }

    public String extractAuthority() {
        Optional<UserRole> userRoleCandidate = this.getAuthorities()
                .stream()
                .findFirst();

        if (userRoleCandidate.isEmpty()) {
            return null;
        }

        return userRoleCandidate.get()
                .getAuthority();
    }
}
