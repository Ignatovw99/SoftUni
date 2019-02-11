package opinionpoll;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int peopleCount = Integer.parseInt(scanner.nextLine());

        ArrayList<Person> people = new ArrayList<>();

        for (int i = 0; i < peopleCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);

            people.add(new Person(name, age));
        }

        people.stream()
                .sorted(Comparator.comparing(Person::getName))
                .filter(person -> person.getAge() > 30)
                .forEach(person -> System.out.println(person.getInfo()));
    }
}
