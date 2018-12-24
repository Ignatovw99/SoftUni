import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bomb_Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> sequence = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int bombNumber = tokens[0];
        int power = tokens[1];

        int indexOfBombNumber = sequence.indexOf(bombNumber);

        while (indexOfBombNumber != -1){
            int startDetonation = indexOfBombNumber - power;
            int endDetonation = indexOfBombNumber + power;
            if (startDetonation < 0){
                startDetonation = 0;
            }
            if (endDetonation >= sequence.size()){
                endDetonation = sequence.size() - 1;
            }

            for (int i = endDetonation; i >= startDetonation; i--) {
                sequence.remove(i);
            }
            indexOfBombNumber = sequence.indexOf(bombNumber);
        }

        System.out.println(sequence.stream().reduce((x, y) -> x + y).orElse(0));
    }
}
