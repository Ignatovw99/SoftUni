package rawdata;

public class Cargo {
    private int weight;
    private String type;

    public Cargo(int weight, String type) {
        this.weight = weight;
        this.type = type;
    }

    public Cargo(Cargo cargo) {
        this.weight = cargo.weight;
        this.type = cargo.type;
    }

    public String getType() {
        return this.type;
    }
}
