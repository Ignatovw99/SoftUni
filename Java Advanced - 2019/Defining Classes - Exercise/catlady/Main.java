package catlady;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cat> cats = new ArrayList<>();

        String line = scanner.nextLine();

        while (!"End".equals(line)) {
            String[] tokens = line.split("\\s+");
            String breed = tokens[0];
            String name = tokens[1];
            double param = Double.parseDouble(tokens[2]);

            Cat cat = null;
            switch (breed) {
                case "Siamese":
                    cat = new Siamese(name, param);
                    break;
                case "Cymric":
                    cat = new Cymric(name, param);
                    break;
                case "StreetExtraordinaire":
                    cat = new StreetExtraordinaire(name, param);
                    break;
            }
            cats.add(cat);
            line = scanner.nextLine();
        }

        String catName = scanner.nextLine();

        Cat catToPrint = cats.stream().filter(cat -> cat.name.equals(catName)).findFirst().get();

        if (catToPrint != null) {
            System.out.println(catToPrint);
        }
    }
}
