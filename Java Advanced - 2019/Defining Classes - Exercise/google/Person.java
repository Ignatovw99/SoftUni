package google;

import java.util.ArrayList;

public class Person {
    private String name;
    private Company company;
    private Car car;
    private ArrayList<Pokemon> pokemon;
    private ArrayList<Parent> parents;
    private ArrayList<Child> children;

    public Person(String name) {
        this.name = name;
        this.pokemon = new ArrayList<>();
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public void setCompany(String companyName, String department, double salary) {
        this.company = new Company(companyName, department, salary);
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemon.add(pokemon);
    }

    public void addParent(Parent parent) {
        this.parents.add(parent);
    }

    public void addChild(Child child) {
        this.children.add(child);
    }

    public void setCar(String model, int speed) {
        this.car = new Car(model, speed);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(this.name).append(System.getProperty("line.separator"))
                .append("Company:").append(System.getProperty("line.separator"));
        if (this.company != null) {
            output.append(this.company.toString()).append(System.getProperty("line.separator"));
        }
        output.append("Car:").append(System.getProperty("line.separator"));
        if (this.car != null) {
            output.append(this.car.toString()).append(System.getProperty("line.separator"));
        }
        output.append("Pokemon:").append(System.getProperty("line.separator"));
        for (Pokemon pokemon : this.pokemon) {
            output.append(pokemon.toString()).append(System.getProperty("line.separator"));
        }
        output.append("Parents:").append(System.getProperty("line.separator"));
        for (Parent parent : this.parents) {
            output.append(parent.toString()).append(System.getProperty("line.separator"));
        }
        output.append("Children:").append(System.getProperty("line.separator"));
        for (Child child : this.children) {
            output.append(child.toString()).append(System.getProperty("line.separator"));
        }
        return output.toString();
    }
}