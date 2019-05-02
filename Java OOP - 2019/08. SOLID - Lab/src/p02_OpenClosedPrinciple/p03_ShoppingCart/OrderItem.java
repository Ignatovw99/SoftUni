package p02_OpenClosedPrinciple.p03_ShoppingCart;

public class OrderItem {
    private String stockKeepingUnit;
    private int quantity;

    public OrderItem(String stockKeepingUnit, int quantity) {
        this.stockKeepingUnit = stockKeepingUnit;
        this.quantity = quantity;
    }

    public String getStockKeepingUnit() {
        return this.stockKeepingUnit;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
