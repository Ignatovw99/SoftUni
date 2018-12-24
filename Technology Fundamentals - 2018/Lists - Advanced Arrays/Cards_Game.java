import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Cards_Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> firstPlayer = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> secondPlayer = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        while (firstPlayer.size() > 0 && secondPlayer.size() > 0){
            int firstPlayerIndex = 0;
            int secondPlayerIndex = 0;

            int firstPlayerCard = firstPlayer.get(firstPlayerIndex);
            int secondPlayerCard = secondPlayer.get(secondPlayerIndex);

            firstPlayer.remove(firstPlayerIndex);
            secondPlayer.remove(secondPlayerIndex);

            if (firstPlayerCard > secondPlayerCard){
                firstPlayer.add(firstPlayerCard);
                firstPlayer.add(secondPlayerCard);
            } else if (secondPlayerCard > firstPlayerCard){
                secondPlayer.add(secondPlayerCard);
                secondPlayer.add(firstPlayerCard);
            }
        }

        if (firstPlayer.size() > 0){
            System.out.println("First player wins! Sum: " + firstPlayer.stream().reduce((e1, e2) -> e1 + e2).orElse(0));
        } else {
            System.out.println("Second player wins! Sum: " + secondPlayer.stream().reduce((e1, e2) -> e1 + e2).orElse(0));
        }
    }
}
