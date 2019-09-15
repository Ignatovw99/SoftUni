package gamestore.commands.user;

import gamestore.commands.Executable;
import gamestore.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutUser implements Executable {

    private UserService userService;

    @Autowired
    public LogoutUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(String... arguments) {
        return this.userService.logout();
    }
}
