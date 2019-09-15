package gamestore.constants;

public final class CommandMessages {

    public static final String USER_WITH_SUCH_EMAIL_IS_ALREADY_REGISTERED = "User with such email address exists already.\n\r" +
            "Please try with another email.";

    public static final String SUCCESSFUL_REGISTRATION_USER = "User with full name %s was registered successfully.";

    public static final String INVALID_EMAIL_OR_PASSWORD = "Invalid email or password. You can not log in.";

    public static final String USER_LOGGED_IN_SUCCESSFULLY = "Successfully logged in user %s.";

    public static final String NO_LOGGED_IN_USER_TO_LOGOUT = "Cannot log out. No user was logged in.";

    public static final String USER_LOGGED_OUT_SUCCESSFULLY = "User %s successfully logged out.";

    public static final String CURRENTLY_LOGGED_IN_USER = "User with email %s is currently logged in.";

    public static final String NO_LOGGED_IN_USER = "Currently there is not a logged in user.";

    public static final String BASIC_USER_CAN_NOT_MODIFY_GAME = "The basic user is not allowed to modify games.";

    public static final String GAME_WITH_SUCH_TITLE_ALREADY_EXISTS = "The game %s already exists.";

    public static final String SUCCESSFULLY_ADDED_GAME = "Game %s was added successfully by %s.";

    public static final String NO_SUCH_GAME_FOUND = "There is not a game with id %d in database.";

    public static final String SUCCESSFULLY_EDITED_GAME = "Game %s was updated successfully by %s";

    public static final String SUCCESSFUL_GAME_REMOVAL = "Game with id %d was removed successfully.";

    public static final String GAME_WITH_SUCH_TITLE_DOES_NOT_EXIST = "Game with %s title does not exist.";

    public static final String CAN_NOT_ADD_GAME_TO_SHOPPING_CART = "Game %s can not be added to shopping cart of user %s, because he already has it.";

    public static final String GAME_ADDED_TO_SHOPPING_CART_SUCCESSFULLY = "Game %s was added to shopping cart successfully.";

    public static final String GAME_IS_ALREADY_ADDED_TO_SHOPPING_CART = "Game %s was already added to shopping cart.";

    public static final String NO_SUCH_GAME_IN_SHOPPING_CART = "Game with title %s is not presented in the shopping cart.";

    public static final String GAME_WAS_REMOVED_FROM_SHOPPING_CART_SUCCESSFULLY = "Game %s was removed from shopping cart successfully.";

    public static final String USER_HAS_NOT_ANY_GAMES = "The currently logged in user hasn't got any bought games.";

    public static final String SUCCESSFULLY_BOUGHT_GAMES = "User %s bought %d games.";

    public static final String INVALID_COMMAND = "Invalid command";

    public static final String INVALID_ARGUMENTS_COUNT = "Invalid arguments count.";

    public static final String GAME_WITH_SUCH_TRAILER_ALREADY_EXISTS = "A game with %s trailer already exists.";

    private CommandMessages(){
    }
}
