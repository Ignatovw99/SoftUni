package gamestore.constants;

public final class ValidationConstraints {

    public static final String EMAIL_VALIDATION_PATTERN = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.[a-z]{2,4}";

    public static final boolean EMAIL_ADDRESS_CAN_BE_NULL = false;

    public static final int PASSWORD_MIN_LENGTH = 6;

    public static final boolean PASSWORD_SHOULD_CONTAIN_AT_LEAST_ONE_UPPERCASE = true;

    public static final boolean PASSWORD_SHOULD_CONTAIN_AT_LEAST_ONE_LOWERCASE = true;

    public static final boolean PASSWORD_SHOULD_CONTAIN_AT_LEAST_ONE_DIGIT = true;

    public static final boolean PASSWORD_IS_MANDATORY = true;

    public static final int GAME_TITLE_MIN_LENGTH = 3;

    public static final int GAME_TITLE_MAX_LENGTH = 100;

    public static final boolean GAME_TITLE_FIRST_LETTER_IS_CAPITAL = true;

    public static final boolean GAME_TITLE_CAN_BE_NULL = false;

    public static final int GAME_PRICE_DEFAULT_PRECISION_AFTER_DECIMAL_POINT = 2;

    public static final boolean GAME_PRICE_CAN_BE_NULL = false;

    public static final int GAME_SIZE_DEFAULT_PRECISION_AFTER_DECIMAL_POINT = 1;

    public static final boolean GAME_SIZE_CAN_BE_NULL = false;

    public static final int GAME_TRAILER_DEFAULT_LENGTH = 11;

    public static final String GAME_TRAILER_DEFAULT_PATTERN = "[a-zA-Z0-9]+";

    public static final boolean GAME_TRAILER_CAN_BE_NULL = false;

    public static final String GAME_THUMBNAIL_URL_VALIDATION_PATTERN = "^(http(s)?:\\/\\/)\\S+$";

    public static final boolean GAME_THUMBNAIL_URL_CAN_BE_EMPTY = false;

    public static final int GAME_DESCRIPTION_DEFAULT_MIN_LENGTH = 20;

    public static final boolean GAME_DESCRIPTION_CAN_BE_NULL = false;

    private ValidationConstraints(){
    }
}
