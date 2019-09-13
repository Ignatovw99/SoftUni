package usersystem.constants;

import java.util.regex.Pattern;

public final class PasswordPatterns {

    public static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");

    public static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");

    public static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");

    public static final Pattern SPECIAL_SYMBOL_PATTERN = Pattern.compile("[!@#$%^&*()_+<>?]");
}
