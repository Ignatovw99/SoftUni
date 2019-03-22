public class Citizen extends BaseResident {
    private String name;
    private int age;

    public Citizen(String name, int age, String id) {
        super(id);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}
