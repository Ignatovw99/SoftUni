package familytree;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class FamilyTree {
    private LinkedHashMap<String, Person> tree;

    public FamilyTree(ArrayList<Person> peopleWithWholeInformation, ArrayList<String> familyTies) {
        this.tree = new LinkedHashMap<>();
        for (String familyTie : familyTies) {
            String[] tokens = familyTie.split(" - ");
            String first = tokens[0];
            String second = tokens[1];

            Person firstPerson = Person.getPerson(peopleWithWholeInformation, first);
            if (firstPerson != null && !tree.containsKey(firstPerson.getName())) {
                tree.put(firstPerson.getName(), firstPerson);
            }
            Person secondPerson = Person.getPerson(peopleWithWholeInformation, second);
            if (secondPerson != null && !tree.containsKey(secondPerson.getName())) {
                tree.put(secondPerson.getName(), secondPerson);
            }
        }
    }

    public void setFamilyTie(Person parent, Person child) {
        parent.addChild(child);
        child.addParent(parent);
    }

    public Person getPerson(String argument) {
        if (argument.contains("/")) {
            for (String person : tree.keySet()) {
                if (tree.get(person).getBirthday().equals(argument)) {
                    return tree.get(person);
                }
            }
            return null;
        } else {
            return tree.get(argument);
        }
    }
}
