package gamestore.constants;

public final class ValidationStatements {

    public static final String EMAIL_CAN_NOT_BE_EMPTY = "The email address is not optional.";

    public static final String EMAIL_CAN_NOT_BE_NULL = "The email address can not be null.";

    public static final String EMAIL_INVALID_VALUE = "The input email does not match the requirements";

    public static final String PASSWORD_CAN_NOT_BE_EMPTY = "The password is not optional.";

    public static final String INVALID_PASSWORD_LENGTH = "Password should be at least %d symbols.";

    public static final String PASSWORD_SHOULD_CONTAIN_UPPERCASE = "Password should contain at least one uppercase letter.";

    public static final String PASSWORD_SHOULD_CONTAIN_LOWERCASE = "Password should contain at least one lowercase letter.";

    public static final String PASSWORD_SHOULD_CONTAIN_DIGIT = "Password should contain at least one digit.";

    public static final String GAME_TITLE_CAN_NOT_BE_EMPTY = "Game should have a title.";

    public static final String GAME_TITLE_INVALID_LENGTH = "Game title should contain between %d and %d symbols.";

    public static final String GAME_TITLE_SHOULD_HAVE_CAPITAL_FIRST_LETTER = "First letter of game title should be capital.";

    public static final String GAME_PRICE_CAN_NOT_BE_NEGATIVE = "Game price should be positive number.";

    public static final String GAME_PRICE_CAN_NOT_BE_ZERO = "Game price can not be zero.";

    public static final String GAME_PRICE_SHOULD_BE_WITH_GIVEN_PRECISION = "Game price should have precision value at least %d after the decimal point.";

    public static final String GAME_SIZE_CAN_NOT_BE_EMPTY = "Game size can not be null.";

    public static final String GAME_SIZE_CAN_NOT_BE_NEGATIVE = "Game size should be positive number.";

    public static final String GAME_SIZE_SHOULD_BE_WITH_GIVEN_PRECISION = "Game size should have precision value at least %d after decimal point.";

    public static final String GAME_TRAILER_CAN_NOT_BE_NULL = "Game trailer is mandatory.";

    public static final String GAME_TRAILER_DOES_NOT_HAVE_REQUIRED_LENGTH = "Game trailer should have %d symbols.";

    public static final String GAME_TRAILER_DOES_NOT_MATCH_PATTERN = "Game trailer doesn't match the given pattern.";

    public static final String GAME_THUMBNAIL_URL_CAN_NOT_BE_NULL = "Game thumbnail URL can not be empty.";

    public static final String GAME_THUMBNAIL_URL_IS_INVALID = "Game thumbnail URL does not match the given pattern.";

    public static final String GAME_DESCRIPTION_CAN_NOT_BE_NULL = "Game description can not be empty.";

    public static final String GAME_DESCRIPTION_IS_INVALID = "Game description does not match required length of %d symbols.";

    public static final String CONFIRM_PASSWORD_DOES_NOT_MATCH = "Confirm password does not match your password.";

    private ValidationStatements(){
    }
}
