package gamestore.services.api;

import gamestore.domain.dtos.binding.RegisterUserDto;
import gamestore.domain.dtos.view.GameTitleViewDto;
import gamestore.domain.dtos.view.LoggedInUserDto;
import gamestore.domain.entities.Game;

import java.util.Set;

public interface UserService {

    boolean checkUserExistenceByEmail(String email);

    String register(RegisterUserDto registerUserDto);

    String login(String email, String password);

    String logout();

    void deleteRelationOfAllUsersWithGivenGame(Game game);

    Set<GameTitleViewDto> getAllOwnedGamesByCurrentlyLoggedIn();

    String addGameToShoppingCart(String gameTitle, GameService gameService);

    String removeGameFromShoppingCart(String gameTitle, GameService gameService);

    String buyAllGamesFromShoppingCart();
}
