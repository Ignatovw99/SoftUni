package gamestore.commands.user;

import gamestore.commands.Executable;
import gamestore.domain.dtos.binding.RegisterUserDto;
import gamestore.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUser implements Executable {

    private static final int EMAIL_INDEX = 0;
    private static final int PASSWORD_INDEX = 1;
    private static final int CONFIRM_PASSWORD_INDEX = 2;
    private static final int FULL_NAME_INDEX = 3;

    private final UserService userService;

    @Autowired
    public RegisterUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(String... arguments) {
        RegisterUserDto registerUserDto = new RegisterUserDto(
                arguments[EMAIL_INDEX],
                arguments[PASSWORD_INDEX],
                arguments[CONFIRM_PASSWORD_INDEX],
                arguments[FULL_NAME_INDEX]
        );

        return this.userService.register(registerUserDto);
    }
}
