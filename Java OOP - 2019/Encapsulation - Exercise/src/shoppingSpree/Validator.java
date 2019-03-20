package shoppingSpree;

public class Validator {
    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public static void validateMoney(double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    public static boolean checkMoneyAvailability(double money, double cost) {
        return (money - cost) >= 0;
    }
}
