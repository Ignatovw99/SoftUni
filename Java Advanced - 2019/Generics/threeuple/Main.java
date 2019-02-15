package threeuple;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] firstLineArguments = scanner.nextLine().split("\\s+");
        String[] secondLineArguments = scanner.nextLine().split("\\s+");
        String[] thirdLineArguments = scanner.nextLine().split("\\s+");

        Threeuple<String, String, String> threeupleOne = new Threeuple<>(
                firstLineArguments[0] + " " +firstLineArguments[1],
                firstLineArguments[2],
                firstLineArguments[3]
        );
        Threeuple<String, Integer, Boolean> threeupleTwo = new Threeuple<>(
                secondLineArguments[0],
                Integer.parseInt(secondLineArguments[1]),
                secondLineArguments[2].equals("drunk")
        );
        Threeuple<String, Double, String> threeupleThree = new Threeuple<>(
                thirdLineArguments[0],
                Double.parseDouble(thirdLineArguments[1]),
                thirdLineArguments[2]
        );

        System.out.println(threeupleOne);
        System.out.println(threeupleTwo);
        System.out.println(threeupleThree);
    }
}
