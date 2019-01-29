import java.util.*;

public class Hands_of_Cards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Set<String>> playersDecks = new LinkedHashMap<>();
        Map<String, Integer> specialCharactersValue =Map.of(
                "J", 11,
                "Q", 12,
                "K", 13,
                "A", 14,
                "S", 4,
                "H", 3,
                "D", 2,
                "C", 1,
                "1", 10
        );
        String input = scanner.nextLine();

        while (!"JOKER".equals(input)) {
            String[] tokens = input.split(":");
            String player = tokens[0];
            String[] deck = tokens[1].trim().split(", ");

            if (!playersDecks.containsKey(player)) {
                playersDecks.put(player, new LinkedHashSet<>());
                addCardsToDeck(playersDecks, player, deck);
            } else {
                addCardsToDeck(playersDecks, player, deck);
            }
            input = scanner.nextLine();
        }

        playersDecks.entrySet().forEach(entry -> {
            String player = entry.getKey();
            int deckPower = calculateDeckPower(entry.getValue(), specialCharactersValue);

            System.out.println(String.format("%s: %d", player, deckPower));
        });
    }

    private static int calculateDeckPower(Set<String> cards, Map<String, Integer> specialChars) {
        int power = 0;

        for (String card : cards) {
            char firstChar = card.charAt(0);
            char secondChar = card.charAt(card.length() - 1);
            int currentPower;

            if (specialChars.containsKey(String.valueOf(firstChar))) {
                currentPower = specialChars.get(String.valueOf(firstChar));
            } else {
                currentPower = Integer.parseInt(String.valueOf(firstChar));
            }

            currentPower *= specialChars.get(String.valueOf(secondChar));

            power += currentPower;
        }
        return power;
    }

    private static void addCardsToDeck(Map<String, Set<String>> playersDecks, String player, String[] deck) {
        for (int i = 0; i < deck.length; i++) {
            playersDecks.get(player).add(deck[i]);
        }
    }
}
