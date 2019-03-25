package p02_OpenClosedPrinciple.p03_ShoppingCart;

public class Main {
    public static void main(String[] args) {
        OnlineOrder order = new OnlineOrder(new Cart());
        order.addItem(new OrderItem("WEIGHTASd", 14));
        order.addItem(new OrderItem("EACHasd", 2));
        System.out.println(order.chekout());
    }
}
