package gamestore.commands.view;

import gamestore.commands.Executable;
import gamestore.constants.CommandMessages;
import gamestore.domain.dtos.view.GameDetailsViewDto;
import gamestore.services.api.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetailsGame implements Executable {

    private static final int TITLE_INDEX = 0;

    private GameService gameService;

    @Autowired
    public DetailsGame(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public String execute(String... arguments) {
        String title = arguments[TITLE_INDEX];
        GameDetailsViewDto gameDetails = this.gameService.getGameDetailsByTitle(title);
        if (gameDetails == null) {
            return String.format(CommandMessages.GAME_WITH_SUCH_TITLE_DOES_NOT_EXIST, title);
        }
        return gameDetails.toString();
    }
}
