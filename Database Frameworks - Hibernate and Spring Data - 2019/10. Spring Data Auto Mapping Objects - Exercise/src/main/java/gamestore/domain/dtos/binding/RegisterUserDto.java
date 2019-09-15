package gamestore.domain.dtos.binding;

import gamestore.domain.validators.email.Email;
import gamestore.domain.validators.password.Password;
import gamestore.domain.validators.passwordmatcher.PasswordMatcher;

@PasswordMatcher
public class RegisterUserDto {

    private String email;

    private String password;

    private String confirmPassword;

    private String fullName;

    public RegisterUserDto() {
    }

    public RegisterUserDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Password
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
