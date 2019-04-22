package greedytimes;

public class Gold extends Item{
    private static String GOLD_STRING = "Gold";

    public Gold(String type, int quantity) {
        super(type, quantity);
    }

    public static boolean isValid(String cashType) {
        return cashType.equals(GOLD_STRING);
    }
}
