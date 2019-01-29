import java.util.*;
import java.util.stream.Collectors;

public class Voina_Number_Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> firstPlayer = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Set<Integer> firstPlayerCards = new LinkedHashSet<>(firstPlayer);
        List<Integer> secondPlayer = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Set<Integer> secondPlayerCards = new LinkedHashSet<>(secondPlayer);

        for (int round = 0; round < 50; round++) {
            if (firstPlayerCards.isEmpty()) {
                System.out.println("Second player win!");
                return;
            }
            if (secondPlayerCards.isEmpty()) {
                System.out.println("First player win!");
                return;
            }
            int firstPlayerCard = firstPlayerCards.iterator().next();
            firstPlayerCards.remove(firstPlayerCard);

            int secondPlayerCard = secondPlayerCards.iterator().next();
            secondPlayerCards.remove(secondPlayerCard);

            if (firstPlayerCard > secondPlayerCard) {
                firstPlayerCards.add(firstPlayerCard);
                firstPlayerCards.add(secondPlayerCard);
            } else if (secondPlayerCard > firstPlayerCard) {
                secondPlayerCards.add(firstPlayerCard);
                secondPlayerCards.add(secondPlayerCard);
            } else {
                firstPlayerCards.add(firstPlayerCard);
                secondPlayerCards.add(secondPlayerCard);
            }
        }

        if (firstPlayerCards.size() < secondPlayerCards.size()) {
            System.out.println("Second player win!");
        } else if (secondPlayerCards.size() < firstPlayerCards.size()) {
            System.out.println("First player win!");
        } else {
            System.out.println("Draw!");
        }
    }
}
