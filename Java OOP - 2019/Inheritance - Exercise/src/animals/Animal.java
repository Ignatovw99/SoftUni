package animals;

public class Animal {
    private String name;
    private int age;
    private String gender;

    protected Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    private void setName(String name) {
        this.validateInputParameter(name);
        this.name = name;
    }

    private void setAge(int age) {
        this.validateInputParameter(String.valueOf(age));
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    protected void setGender(String gender) {
        this.validateInputParameter(gender);
        this.gender = gender;
    }

    private void validateInputParameter(String param) {
        if (param.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    protected String produceSound() {
        return "Not implemented!";
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %s%n%s",
                this.getClass().getSimpleName(),
                this.name,
                this.age,
                this.gender,
                this.produceSound());
    }
}
