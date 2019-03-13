package greedytimes;

public class Cash extends Item{
    public Cash(String type, int quantity) {
        super(type, quantity);
    }

    public static boolean isValid(String cashType) {
        boolean isValid = true;
        if (cashType.length() != 3) { isValid = false; }

        if (isValid) {
            for (int i = 0; i < cashType.length(); i++) {
                if (!Character.isLetter(cashType.charAt(i))) {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }
}
