package shoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        Validator.validateName(name);
        this.name = name;
    }

    private String getName() {
        return this.name;
    }

    private void setMoney(double money) {
        Validator.validateMoney(money);
        this.money = money;
    }

    private double getMoney() {
        return this.money;
    }

    private int getBagSize() {
        return this.products.size();
    }

    public void buyProduct(Product product) {
        boolean enoughMoney = Validator.checkMoneyAvailability(this.getMoney(), product.getCost());

        if (!enoughMoney) {
            throw new IllegalArgumentException(String.format("%s can't afford %s",
                    this.getName(), product.getName()));
        }
        this.setMoney(this.getMoney() - product.getCost());
        this.products.add(product);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getName()).append(" - ");

        if (this.getBagSize() > 0) {
            for (Product product : this.products) {
                builder.append(product.getName()).append(", ");
            }
            builder = new StringBuilder(builder.substring(0, builder.lastIndexOf(", ")));
        } else {
            builder.append("Nothing bought");
        }

        return builder.toString();
    }
}
