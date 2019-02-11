package familytree;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> peopleWithWholeInformation = new ArrayList<>();
        ArrayList<String> familyTies = new ArrayList<>();

        String searchPersonInformation = scanner.nextLine();
        String command = scanner.nextLine();

        while (!"End".equals(command)) {
            if (command.contains("-")) {
                familyTies.add(command);
            } else {
                String name = command.substring(0, command.lastIndexOf(" "));
                String birthday = command.substring(command.lastIndexOf(" ") + 1);
                peopleWithWholeInformation.add(new Person(name, birthday));
            }
            command = scanner.nextLine();
        }

        FamilyTree familyTree = new FamilyTree(peopleWithWholeInformation, familyTies);

        for (String familyTie : familyTies) {
            String[] tokens = familyTie.split(" - ");
            String firstToken = tokens[0];
            String secondToken = tokens[1];

            Person parent = Person.getPerson(peopleWithWholeInformation, firstToken);
            Person child = Person.getPerson(peopleWithWholeInformation, secondToken);
            familyTree.setFamilyTie(parent, child);
        }

        Person person = familyTree.getPerson(searchPersonInformation);
        System.out.println(person);
    }
}
