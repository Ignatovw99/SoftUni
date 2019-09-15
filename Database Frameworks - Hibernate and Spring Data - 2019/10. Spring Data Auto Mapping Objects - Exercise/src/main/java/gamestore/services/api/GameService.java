package gamestore.services.api;

import gamestore.domain.dtos.view.GameDetailsViewDto;
import gamestore.domain.dtos.view.GameTitleAndPriceViewDto;
import gamestore.domain.dtos.binding.AddGameDto;
import gamestore.domain.dtos.binding.EditGameDto;
import gamestore.domain.entities.Game;

import java.util.List;

public interface GameService {

    boolean checkGameExistenceByTitle(String title);

    String addGame(AddGameDto addGameDto);

    EditGameDto getGameToEditById(Long id);

    String updateGame(EditGameDto editGameDto);

    String deleteGameById(long id, UserService userService);

    List<GameTitleAndPriceViewDto> getAllGamesTitleAndPrice();

    GameDetailsViewDto getGameDetailsByTitle(String title);

    Game getGameByTitle(String game);
}
