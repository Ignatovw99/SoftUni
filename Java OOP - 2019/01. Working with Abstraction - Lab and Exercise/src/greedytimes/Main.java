package greedytimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] safeContent = scanner.nextLine().split("\\s+");

        Treasure treasure = new Treasure();
        treasure.filterItems(safeContent);

        Bag bag = new Bag(bagCapacity);
        bag.fillTheBag(treasure);
        System.out.print(bag.toString());
    }
}