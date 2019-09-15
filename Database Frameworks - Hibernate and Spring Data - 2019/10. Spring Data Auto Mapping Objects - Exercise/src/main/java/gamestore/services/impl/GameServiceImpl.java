package gamestore.services.impl;

import gamestore.constants.CommandMessages;
import gamestore.domain.LoggedInUser;
import gamestore.domain.dtos.view.GameDetailsViewDto;
import gamestore.domain.dtos.view.GameTitleAndPriceViewDto;
import gamestore.domain.dtos.binding.AddGameDto;
import gamestore.domain.dtos.binding.EditGameDto;
import gamestore.domain.entities.Game;
import gamestore.repositories.GameRepository;
import gamestore.services.api.GameService;
import gamestore.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    private ModelMapper modelMapper;

    private Validator validator;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean checkGameExistenceByTitle(String title) {
        return this.gameRepository.existsByTitle(title);
    }

    @Override
    public String addGame(AddGameDto addGameDto) {
        if (!LoggedInUser.hasCurrentlyLoggedInUser()) {
            return CommandMessages.NO_LOGGED_IN_USER;
        }

        if (!LoggedInUser.isAdmin()) {
            return CommandMessages.BASIC_USER_CAN_NOT_MODIFY_GAME;
        }

        if (this.checkGameExistenceByTitle(addGameDto.getTitle())) {
            return String.format(CommandMessages.GAME_WITH_SUCH_TITLE_ALREADY_EXISTS, addGameDto.getTitle());
        }

        if (this.gameRepository.existsByTrailer(addGameDto.getTrailer())) {
            return String.format(CommandMessages.GAME_WITH_SUCH_TRAILER_ALREADY_EXISTS, addGameDto.getTrailer());
        }

        StringBuilder result = new StringBuilder();
        Set<ConstraintViolation<AddGameDto>> violations = this.validator.validate(addGameDto);

        if (violations.isEmpty()) {
            Game game = this.modelMapper.map(addGameDto, Game.class);
            this.gameRepository.saveAndFlush(game);
            result.append(String.format(CommandMessages.SUCCESSFULLY_ADDED_GAME, game.getTitle(), LoggedInUser.getFullName()))
                    .append(System.lineSeparator());
        } else {
            violations
                    .forEach(violation ->
                            result.append(violation.getMessage()).append(System.lineSeparator())
                    );
        }

        return result.toString().trim();
    }

    @Override
    public EditGameDto getGameToEditById(Long id) {
        Game game = this.gameRepository.findById(id).orElse(null);
        if (game == null) {
            return null;
        }

        return this.modelMapper.map(game, EditGameDto.class);
    }

    @Override
    public String updateGame(EditGameDto editGameDto) {
        if (!LoggedInUser.hasCurrentlyLoggedInUser()) {
            return CommandMessages.NO_LOGGED_IN_USER;
        }

        if (!LoggedInUser.isAdmin()) {
            return CommandMessages.BASIC_USER_CAN_NOT_MODIFY_GAME;
        }

        StringBuilder result = new StringBuilder();
        Set<ConstraintViolation<EditGameDto>> violations = this.validator.validate(editGameDto);

        if (violations.isEmpty()) {
            Game game = this.modelMapper.map(editGameDto, Game.class);
            this.gameRepository.saveAndFlush(game);
            result.append(String.format(CommandMessages.SUCCESSFULLY_EDITED_GAME, game.getTitle(), LoggedInUser.getFullName()))
                    .append(System.lineSeparator());
        } else {
            violations
                    .forEach(violation ->
                            result.append(violation.getMessage()).append(System.lineSeparator())
                    );
        }

        return result.toString().trim();
    }

    @Override
    public String deleteGameById(long id, UserService userService) {
        if (!LoggedInUser.hasCurrentlyLoggedInUser()) {
            return CommandMessages.NO_LOGGED_IN_USER;
        }

        if (!LoggedInUser.isAdmin()) {
            return CommandMessages.BASIC_USER_CAN_NOT_MODIFY_GAME;
        }

        Game game = this.gameRepository.findById(id)
                .orElse(null);

        if (game == null) {
            return String.format(CommandMessages.NO_SUCH_GAME_FOUND, id);
        }

        userService.deleteRelationOfAllUsersWithGivenGame(game);

        this.gameRepository.deleteById(id);
        return String.format(CommandMessages.SUCCESSFUL_GAME_REMOVAL, id);
    }

    @Override
    public List<GameTitleAndPriceViewDto> getAllGamesTitleAndPrice() {
        return this.gameRepository.findAll()
                .stream()
                .map(game -> this.modelMapper.map(game, GameTitleAndPriceViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GameDetailsViewDto getGameDetailsByTitle(String title) {
        Game game = this.gameRepository.findByTitle(title);
        if (game == null) {
            return null;
        }
        return this.modelMapper.map(game, GameDetailsViewDto.class);
    }

    @Override
    public Game getGameByTitle(String title) {
        return this.gameRepository.findByTitle(title);
    }
}