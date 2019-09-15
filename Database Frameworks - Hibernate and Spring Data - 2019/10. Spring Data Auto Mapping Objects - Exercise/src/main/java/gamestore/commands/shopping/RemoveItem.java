package gamestore.commands.shopping;

import gamestore.commands.Executable;
import gamestore.services.api.GameService;
import gamestore.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveItem implements Executable {

    private static final int GAME_TITLE_INDEX = 0;

    private UserService userService;

    private GameService gameService;

    @Autowired
    public RemoveItem(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public String execute(String... arguments) {
        String gameTitle = arguments[GAME_TITLE_INDEX];
        return this.userService.removeGameFromShoppingCart(gameTitle, this.gameService);
    }
}
