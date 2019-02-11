package google;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<String, Person> people = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] lineArguments = scanner.nextLine().split("\\s+");
        String command = lineArguments[0];

        while (!"End".equals(command)) {
            addPersonInformation(lineArguments, command);

            lineArguments = scanner.nextLine().split("\\s+");
            command = lineArguments[0];
        }

        String output = scanner.nextLine();
        Person personToPrint = people.get(output);
        System.out.println(personToPrint);
    }

    private static void addPersonInformation(String[] arguments, String command) {
        String name = command;
        command = arguments[1];

        if (!people.containsKey(name)) {
            people.put(name, new Person(name));
        }

        switch (command) {
            case "company":
                addCompany(name, arguments[2], arguments[3], Double.parseDouble(arguments[4]));
                break;
            case "pokemon":
                addPokemon(name, arguments[2], arguments[3]);
                break;
            case "parents":
                addParent(name, arguments[2], arguments[3]);
                break;
            case "children":
                addChild(name, arguments[2], arguments[3]);
                break;
            case "car":
                addCar(name, arguments[2], Integer.parseInt(arguments[3]));
        }

    }

    private static void addCar(String name, String carModel, int carSpeed) {
        people.get(name).setCar(carModel, carSpeed);
    }

    private static void addChild(String name, String childName, String childBirthday) {
        Child child = new Child(childName, childBirthday);
        people.get(name).addChild(child);
    }

    private static void addParent(String name, String parentName, String parentBirthday) {
        Parent parent = new Parent(parentName, parentBirthday);
        //If it does not work. Here is the problem
        people.get(name).addParent(parent);
    }

    private static void addPokemon(String name, String pokemonName, String pokemonType) {
        people.get(name).addPokemon(new Pokemon(pokemonName, pokemonType));
    }

    private static void addCompany(String name, String companyName, String department, double salary) {
        people.get(name).setCompany(companyName, department, salary);
    }
}
