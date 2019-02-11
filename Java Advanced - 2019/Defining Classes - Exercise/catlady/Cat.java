package catlady;

public abstract class Cat {
    protected String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String breed = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1);
        return String.format("%s %s", breed, this.name);
    }
}
