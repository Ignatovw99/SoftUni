package gamestore.commands.user;

import gamestore.commands.Executable;
import gamestore.domain.dtos.view.LoggedInUserDto;
import gamestore.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginUser implements Executable {

    private static final int EMAIL_INDEX = 0;
    private static final int PASSWORD_INDEX = 1;

    private UserService userService;

    @Autowired
    public LoginUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(String... arguments) {
        return this.userService.login(arguments[EMAIL_INDEX], arguments[PASSWORD_INDEX]);
    }
}
