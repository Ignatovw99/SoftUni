package gamestore.commands.game;

import gamestore.commands.Executable;
import gamestore.services.api.GameService;
import gamestore.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteGame implements Executable {

    private static final int ID_INDEX = 0;

    private GameService gameService;

    private UserService userService;

    @Autowired
    public DeleteGame(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    public String execute(String... arguments) {
        long id = Long.parseLong(arguments[ID_INDEX]);
        return this.gameService.deleteGameById(id, userService);
    }
}
