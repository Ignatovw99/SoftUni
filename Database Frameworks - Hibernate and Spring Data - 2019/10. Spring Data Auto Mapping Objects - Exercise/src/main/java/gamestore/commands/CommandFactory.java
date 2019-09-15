package gamestore.commands;

import gamestore.commands.game.AddGame;
import gamestore.commands.game.DeleteGame;
import gamestore.commands.game.EditGame;
import gamestore.commands.shopping.AddItem;
import gamestore.commands.shopping.BuyItem;
import gamestore.commands.shopping.RemoveItem;
import gamestore.commands.user.LoginUser;
import gamestore.commands.user.LogoutUser;
import gamestore.commands.user.RegisterUser;
import gamestore.commands.view.AllGames;
import gamestore.commands.view.DetailsGame;
import gamestore.commands.view.OwnedGames;
import gamestore.constants.CommandTypes;
import gamestore.services.api.GameService;
import gamestore.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    private final UserService userService;

    private final GameService gameService;

    @Autowired
    private CommandFactory(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }


    public Executable createCommand(String commandType) {
        switch (commandType) {
            case CommandTypes.REGISTER_USER:
                return new RegisterUser(this.userService);
            case CommandTypes.LOGIN_USER:
                return new LoginUser(this.userService);
            case CommandTypes.LOGOUT_USER:
                return new LogoutUser(this.userService);
            case CommandTypes.ADD_GAME:
                return new AddGame(this.gameService);
            case CommandTypes.EDIT_GAME:
                return new EditGame(this.gameService);
            case CommandTypes.DELETE_GAME:
                return new DeleteGame(this.gameService, this.userService);
            case CommandTypes.ALL_GAMES:
                return new AllGames(this.gameService);
            case CommandTypes.DETAILS_GAME:
                return new DetailsGame(this.gameService);
            case CommandTypes.OWNED_GAMES:
                return new OwnedGames(this.userService);
            case CommandTypes.ADD_ITEM:
                return new AddItem(this.userService, this.gameService);
            case CommandTypes.REMOVE_ITEM:
                return new RemoveItem(this.userService, this.gameService);
            case CommandTypes.BUY_ITEM:
                return new BuyItem(this.userService);
        }
        return null;
    }
}
