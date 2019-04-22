package greedytimes;

public abstract class Item {
    private String type;
    private int quantity;

    public Item(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return String.format("##%s - %d", this.type, this.quantity);
    }
}
