package gamestore.services.impl;

import gamestore.constants.CommandMessages;
import gamestore.domain.LoggedInUser;
import gamestore.domain.dtos.binding.RegisterUserDto;
import gamestore.domain.dtos.view.GameTitleViewDto;
import gamestore.domain.dtos.view.LoggedInUserDto;
import gamestore.domain.entities.Game;
import gamestore.domain.entities.User;
import gamestore.domain.entities.enums.Role;
import gamestore.repositories.UserRepository;
import gamestore.services.api.GameService;
import gamestore.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private Validator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean checkUserExistenceByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public String register(RegisterUserDto registerUserDto) {
        StringBuilder result = new StringBuilder();

        if (this.checkUserExistenceByEmail(registerUserDto.getEmail())) {
            return CommandMessages.USER_WITH_SUCH_EMAIL_IS_ALREADY_REGISTERED;
        }

        Set<ConstraintViolation<RegisterUserDto>> violations = this.validator.validate(registerUserDto);

        if (violations.isEmpty()) {
            User user = this.modelMapper.map(registerUserDto, User.class);

            if (this.userRepository.count() == 0) {
                user.setRole(Role.ADMIN);
            } else {
                user.setRole(Role.USER);
            }
            this.userRepository.saveAndFlush(user);
            result.append(
                    String.format(CommandMessages.SUCCESSFUL_REGISTRATION_USER, registerUserDto.getFullName()))
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
    public String login(String email, String password) {
        User user = this.userRepository.findByEmailAndPassword(email, password);

        if (user == null) {
            return CommandMessages.INVALID_EMAIL_OR_PASSWORD;
        }

        LoggedInUserDto loggedInUserDto = this.modelMapper.map(user, LoggedInUserDto.class);

        return LoggedInUser.login(loggedInUserDto);
    }

    @Override
    public String logout() {
        return LoggedInUser.logout();
    }

    @Override
    public void deleteRelationOfAllUsersWithGivenGame(Game game) {
        this.userRepository.findAllByGamesContaining(game)
                .forEach(user -> {
                    user.getShoppingCart().remove(game);
                    user.getGames().remove(game);
                    this.userRepository.saveAndFlush(user);
                });
    }

    @Override
    public Set<GameTitleViewDto> getAllOwnedGamesByCurrentlyLoggedIn() {
        if (!LoggedInUser.hasCurrentlyLoggedInUser()) {
            return new HashSet<>();
        }

        User user = this.userRepository.findById(LoggedInUser.getUserId())
                .orElse(null);

        if (user == null || user.getGames().isEmpty()) {
            return new HashSet<>();
        }

        return user.getGames()
                .stream()
                .map(game -> this.modelMapper.map(game, GameTitleViewDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public String addGameToShoppingCart(String gameTitle, GameService gameService) {
        if (!LoggedInUser.hasCurrentlyLoggedInUser()) {
            return CommandMessages.NO_LOGGED_IN_USER;
        }

        Game gameToAdd = gameService.getGameByTitle(gameTitle);

        if (gameToAdd == null) {
            return String.format(CommandMessages.GAME_WITH_SUCH_TITLE_DOES_NOT_EXIST, gameTitle);
        }

        User loggedInUser = this.userRepository.findById(LoggedInUser.getUserId())
                .orElse(null);

        boolean hasUserAlreadyThisGame = loggedInUser.getGames()
                .contains(gameToAdd);

        if (hasUserAlreadyThisGame) {
            return String.format(CommandMessages.CAN_NOT_ADD_GAME_TO_SHOPPING_CART, gameTitle, loggedInUser.getFullName());
        }

        boolean isGameAlreadyAddedToShoppingCart = loggedInUser.getShoppingCart()
                .contains(gameToAdd);

        if (isGameAlreadyAddedToShoppingCart) {
            return String.format(CommandMessages.GAME_IS_ALREADY_ADDED_TO_SHOPPING_CART, gameTitle);
        }

        loggedInUser.getShoppingCart().add(gameToAdd);
        this.userRepository.saveAndFlush(loggedInUser);
        return String.format(CommandMessages.GAME_ADDED_TO_SHOPPING_CART_SUCCESSFULLY, gameTitle);
    }

    @Override
    public String removeGameFromShoppingCart(String gameTitle, GameService gameService) {
        if (!LoggedInUser.hasCurrentlyLoggedInUser()) {
            return CommandMessages.NO_LOGGED_IN_USER;
        }

        Game gameToRemove = gameService.getGameByTitle(gameTitle);
        User loggedInUser = this.userRepository.findById(LoggedInUser.getUserId())
                .orElse(null);
        Set<Game> shoppingCart = loggedInUser.getShoppingCart();

        if (!shoppingCart.contains(gameToRemove)) {
            return String.format(CommandMessages.NO_SUCH_GAME_IN_SHOPPING_CART, gameTitle);
        }

        shoppingCart.remove(gameToRemove);
        this.userRepository.saveAndFlush(loggedInUser);
        return String.format(CommandMessages.GAME_WAS_REMOVED_FROM_SHOPPING_CART_SUCCESSFULLY, gameTitle);
    }

    @Override
    public String buyAllGamesFromShoppingCart() {
        if (!LoggedInUser.hasCurrentlyLoggedInUser()) {
            return CommandMessages.NO_LOGGED_IN_USER;
        }

        User loggedInUser = this.userRepository.findById(LoggedInUser.getUserId())
                .orElse(null);

        int boughtGamesCount = loggedInUser.getShoppingCart().size();

        loggedInUser.getGames()
                .addAll(loggedInUser.getShoppingCart());
        loggedInUser.getShoppingCart()
                .removeAll(loggedInUser.getShoppingCart());

        this.userRepository.saveAndFlush(loggedInUser);
        return String.format(CommandMessages.SUCCESSFULLY_BOUGHT_GAMES, loggedInUser.getFullName(), boughtGamesCount);
    }
}