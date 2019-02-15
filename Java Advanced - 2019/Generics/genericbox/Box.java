package genericbox;

public class Box<T extends Comparable<T>> {
    private T value;
    private Class clazz;

    public Box(T value) {
        this.value = value;
        this.clazz = value.getClass();
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.clazz.getName(), this.value);
    }

    public int compareTo(Box<T> other) {
        return this.value.compareTo(other.value);
    }
}
