package genericbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    //Here is the solution for all of the problems 1 - 6 Exercises from Generic - Exercise

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Box<String>> boxesOfStrings = new ArrayList<>();
        ArrayList<Box<Integer>> boxesOfIntegers = new ArrayList<>();
        ArrayList<Box<Double>> boxesOfDoubles = new ArrayList<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            Box box;
            if (tryParseDouble(line)) {
                box = new Box<Double>(Double.parseDouble(line));
                boxesOfDoubles.add(box);
            } else {
                box = new Box<String>(line);
                boxesOfStrings.add(box);
            }
        }

        int[] indexesToSwap = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        String element = scanner.nextLine();
        Box box;
        if (tryParseDouble(element)) {
            box = new Box<Double>(Double.parseDouble(element));
        } else {
            box = new Box<String>(element);
        }

        if (boxesOfDoubles.size() == 0) {
            swapElements(boxesOfStrings, indexesToSwap);
            for (Box<String> currentBox : boxesOfStrings) {
                System.out.println(currentBox);
            }
            System.out.println(countGreaterValues(boxesOfStrings, box));
        } else {
            swapElements(boxesOfIntegers, indexesToSwap);
            for (Box<Integer> currentBox : boxesOfIntegers) {
                System.out.println(currentBox);
            }

            System.out.println(countGreaterValues(boxesOfDoubles, box));
        }
    }

    private static boolean tryParseDouble(String line) {
        try {
            Double.parseDouble(line);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static <T extends Comparable<T>> int countGreaterValues(List<Box<T>> list, Box<T> box) {
        int counter = 0;
        for (Box<T> currentBox : list) {
            if (currentBox.compareTo(box) > 0) {
                counter++;
            }
        }
        return counter;
    }

    private static <T> void swapElements(ArrayList<T> boxesList, int[] indexesToSwap) {
        int firstIndex = indexesToSwap[0];
        int secondIndex = indexesToSwap[1];

        T temp = boxesList.get(secondIndex);
        boxesList.set(secondIndex, boxesList.get(firstIndex));
        boxesList.set(firstIndex, temp);
    }

    private static boolean tryParseInt(String line) {
        try {
            Integer.parseInt(line);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
