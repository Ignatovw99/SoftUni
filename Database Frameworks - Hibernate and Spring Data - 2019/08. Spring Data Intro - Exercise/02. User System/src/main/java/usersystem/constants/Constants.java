package usersystem.constants;

import java.time.LocalDate;

public final class Constants {

    public static final String DELIMITER = " ";

    public static final String USERNAME_INVALID_LENGTH = "The length of username should be between 4 and 30 symbols.";

    public static final String AGE_BELOW_LOWER_BOUND = "Age can not be less than 1.";

    public static final String AGE_ABOVE_UPPER_BOUND = "Age can not be more than 120.";

    public static final String EMAIL_FORMAT_NOT_VALID = "Email format is not valid.";

    public static final String EMAIL_CAN_NOT_BE_EMPTY_FIELD = "The email field can not be null.";

    public static final String EMAIL_CONSTRAINTS_NOT_FULFILLED = "The email format is not correct.";

    public static final String PASSWORD_CAN_NOT_BE_EMPTY_FIELD = "Password can not be null.";

    public static final String PASSWORD_SHOULD_CONTAIN_LOWERCASE = "Password should contain at least one lowercase letter.";

    public static final String PASSWORD_SHOULD_CONTAIN_UPPERCASE = "Password should contain at least one uppercase letter.";

    public static final String PASSWORD_SHOULD_CONTAIN_DIGIT = "Password should contain at least one digit.";

    public static final String PASSWORD_SHOULD_CONTAIN_SPECIAL_SYMBOL =
            "Password should contain at least one of the following special symbols: !@#$%^&*()_+<>?";

    public static final String PASSWORD_CONSTRAINTS_NOT_FULFILLED = "The password format is not correct.";

    public static final String EMPTY_TOWNS_TABLE = "There are no towns in database";

    public static final String EMPTY_USERS_TABLE = "There are no users in database";

    public static final String EMPTY_PICTURES_TABLE = "There are no pictures in database";

    public static final String EMPTY_ALBUMS_TABLE = "There are no albums in database";

    public static final String EMAIL_PROVIDER = "yahoo.co.uk";

    public static final String NO_USERS_WITH_SUCH_EMAIL_PROVIDER = "No users found with email domain ";

    public static final LocalDate DATE = LocalDate.of(2019, 7, 3);

    public static final String SUCCESSFUL_ONE_USER_REMOVAL = " user has been deleted";

    public static final String SUCCESSFUL_MANY_USER_REMOVAL = " users have been deleted";

    public static final String NO_USERS_DELETED = "No users have been deleted";
}
