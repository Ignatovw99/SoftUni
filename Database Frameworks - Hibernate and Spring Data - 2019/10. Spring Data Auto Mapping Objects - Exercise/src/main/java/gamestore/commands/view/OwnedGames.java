package gamestore.commands.view;

import gamestore.commands.Executable;
import gamestore.constants.CommandMessages;
import gamestore.domain.LoggedInUser;
import gamestore.domain.dtos.view.GameTitleViewDto;
import gamestore.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OwnedGames implements Executable {

    private UserService userService;

    @Autowired
    public OwnedGames(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(String... arguments) {
        Set<GameTitleViewDto> ownedGames = this.userService.getAllOwnedGamesByCurrentlyLoggedIn();
        if (!LoggedInUser.hasCurrentlyLoggedInUser()) {
            return CommandMessages.NO_LOGGED_IN_USER;
        }
        if (ownedGames.isEmpty()) {
            return CommandMessages.USER_HAS_NOT_ANY_GAMES;
        }
        StringBuilder builder = new StringBuilder();

        ownedGames.forEach(gameTitleViewDto ->
                builder.append(gameTitleViewDto.getTitle()).append(System.lineSeparator()));

        return builder.substring(0, builder.lastIndexOf(System.lineSeparator()));
    }
}
