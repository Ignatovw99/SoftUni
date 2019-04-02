package application;

public class Validator {
    public static void validateStringValue(String value, int length) {
        if (value.length() < length) {
            throw new IllegalArgumentException(String.format(Constants.IncorrectModelLengthMessage, length));
        }
    }

    public static void validateNumericalValue(int value, String parameter) {
        if (value <= 0) {
            throw new IllegalArgumentException(String.format(Constants.IncorrectPropertyValueMessage, parameter));
        }
    }
}
