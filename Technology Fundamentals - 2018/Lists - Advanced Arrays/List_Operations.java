import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class List_Operations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String cmd = scanner.nextLine();

        while (!cmd.equalsIgnoreCase("End")){
            String[] tokens = cmd.split("\\s+");
            String modificationType = tokens[0];

            switch (modificationType){
                case "Add":
                    int number = Integer.parseInt(tokens[1]);
                    list.add(number);
                    break;
                case "Remove":
                    int index = Integer.parseInt(tokens[1]);
                    if (isIndexInside(index, list)){
                        list.remove(index);
                    } else {
                        printInvalidIndex();
                    }
                    break;
                case "Shift":
                    int count = Integer.parseInt(tokens[2]);
                    switch (tokens[1]){
                        case "left":
                            leftShift(list, count);
                            break;
                        case "right":
                            rightShift(list, count);
                            break;
                    }
                    break;
                case "Insert":
                    number = Integer.parseInt(tokens[1]);
                    index = Integer.parseInt(tokens[2]);
                    if (isIndexInside(index, list)){
                        list.add(index, number);
                    } else {
                        printInvalidIndex();
                    }
                     break;
            }
            cmd = scanner.nextLine();
        }
        list.forEach(e -> System.out.print(e + " "));
    }

    private static void leftShift(List<Integer> list, int count) {
        int[] shifted = new int[list.size()];

        for (int i = 0; i < shifted.length; i++){
            int index = (i + count) % list.size();
            shifted[i] = list.get(index);
        }

        for (int i = 0; i < list.size(); i++){
            list.set(i, shifted[i]);
        }
    }

    private static void rightShift(List<Integer> list, int count) {
        int[] shifted = new int[list.size()];

        for (int i = 0; i < shifted.length; i++){
            int index = (i + count) % list.size();
            shifted[index] = list.get(i);
        }

        for (int i = 0; i < list.size(); i++){
            list.set(i, shifted[i]);
        }
    }

    private static void printInvalidIndex() {
        System.out.println("Invalid index");
    }

    private static boolean isIndexInside(int index, List<Integer> list) {
        return index >= 0 && index < list.size();
    }
}
