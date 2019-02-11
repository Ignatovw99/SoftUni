package familytree;

import java.util.ArrayList;

public class Person {
    private String name;
    private String birthday;
    private ArrayList<Person> parents;
    private ArrayList<Person> children;

    public Person(String argument) {
        if (argument.contains("/")) {
            this.birthday = argument;
        } else {
            this.name = argument;
        }
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    public Person(String name, String birthday) {
        this(name);
        this.birthday = birthday;
    }

    public String getName() {
        return this.name;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public static Person getPerson(ArrayList<Person> peopleWithWholeInformation, String token) {
        for (Person person : peopleWithWholeInformation) {
            if (person.getName().equals(token) || person.getBirthday().equals(token)) {
                return person;
            }
        }
        return null;
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    public void addParent(Person parent) {
        this.parents.add(parent);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.name).append(" ").append(this.birthday).append(System.getProperty("line.separator"))
                .append("Parents:").append(System.getProperty("line.separator"));
        for (Person parent : this.parents) {
            stringBuilder.append(parent.name).append(" ").append(parent.birthday).append(System.getProperty("line.separator"));
        }
        stringBuilder.append("Children:").append(System.getProperty("line.separator"));
        for (Person child : this.children) {
            stringBuilder.append(child.name).append(" ").append(child.birthday).append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
