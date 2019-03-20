package personTask;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private void setName(String name) {
        if (name.trim().isEmpty() || name.length() < 3) {
            throw new IllegalArgumentException("Name's length should not be less than 3 symbols!");
        }
        this.name = name;
    }

    private String getName() {
        return this.name;
    }

    protected void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be positive!");
        }
        this.age = age;
    }

    private int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Age: " + this.getAge();
    }
}
