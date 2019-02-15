package tuple;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();
        String thirdLine = scanner.nextLine();

        Tuple<String, String> tupleOne = new Tuple<>(
                firstLine.substring(0, firstLine.lastIndexOf(" ")),
                firstLine.substring(firstLine.lastIndexOf(" ") + 1)
        );

        Tuple<String, Integer> tupleTwo = new Tuple<>(
                secondLine.substring(0, secondLine.lastIndexOf(" ")),
                Integer.parseInt(secondLine.substring(secondLine.lastIndexOf(" ") + 1))
        );

        Tuple<Integer, Double> tupleThree = new Tuple<>(
                Integer.parseInt(thirdLine.substring(0, thirdLine.lastIndexOf(" "))),
                Double.parseDouble(thirdLine.substring(thirdLine.lastIndexOf(" ") + 1))
        );

        System.out.println(tupleOne);
        System.out.println(tupleTwo);
        System.out.println(tupleThree);
    }
}
