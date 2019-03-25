package p02_OpenClosedPrinciple.p03_ShoppingCart;

public abstract class Order {
    protected final Cart cart;

    protected Order(Cart cart) {
        this.cart = cart;
    }

    public void addItem(OrderItem orderItem) {
        this.cart.add(orderItem);
    }

    public String chekout() {
        return "Total amount to pay: " + this.cart.getTotalAmount();
    }
}
