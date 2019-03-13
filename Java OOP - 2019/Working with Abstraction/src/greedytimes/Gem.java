package greedytimes;

public class Gem extends Item{
    private static String GEM_STRING = "gem";

    public Gem(String type, int quantity) {
        super(type, quantity);
    }

    public static boolean isValid(String gemType) {
        boolean isValid = true;
        if (gemType.length() <= 3) { isValid = false; }

        if (isValid) {
            String endOfTheWord = gemType.substring(gemType.length() - 3);
            isValid = endOfTheWord.equalsIgnoreCase(GEM_STRING);
        }
        return isValid;
    }
}
