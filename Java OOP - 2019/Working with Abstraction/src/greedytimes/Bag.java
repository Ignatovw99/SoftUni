package greedytimes;

import java.util.ArrayList;
import java.util.Comparator;

public class Bag {
    private static Comparator<Item> DEFAULT_COMPARATOR = ((first, second) -> {
        int nameCompare = second.getType().compareTo(first.getType());
        if (nameCompare == 0) {
            return Long.compare(first.getQuantity(), second.getQuantity());
        }
        return nameCompare;
    });

    private long capacity;
    private long fullness;
    private ArrayList<Cash> cashItems;
    private ArrayList<Gem> gemItems;
    private Gold gold;

    private long goldAmount;
    private long gemAmount;
    private long cashAmount;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.fullness = 0;
        this.cashItems = new ArrayList<>();
        this.gemItems = new ArrayList<>();
    }

    public void fillTheBag(Treasure treasure) {
        ArrayList<Item> validItems = treasure.getValidItems();

        for (Item item : validItems) {
            if (this.canContainItem(item.getQuantity(), item.getClass().getSimpleName())) {
                if (item instanceof Cash) {
                    int indexOfItem = Bag.getItemByIndex(item, this.cashItems);
                    if (indexOfItem == -1) {
                        this.cashItems.add(new Cash(item.getType(), item.getQuantity()));
                    } else {
                        this.cashItems.get(indexOfItem).increaseQuantity(item.getQuantity());
                    }
                    this.cashAmount += item.getQuantity();
                } else if (item instanceof Gem) {
                    int indexOfItem = Bag.getItemByIndex(item, this.gemItems);
                    if (indexOfItem == -1) {
                        this.gemItems.add(new Gem(item.getType(), item.getQuantity()));
                    } else {
                        this.gemItems.get(indexOfItem).increaseQuantity(item.getQuantity());
                    }
                    this.gemAmount += item.getQuantity();
                } else if (item instanceof Gold) {
                    if (this.gold != null) {
                        this.gold.increaseQuantity(item.getQuantity());
                    } else {
                        this.gold = new Gold(item.getType(), item.getQuantity());
                    }
                    this.goldAmount += item.getQuantity();
                }
                this.fullness += item.getQuantity();
            }
        }
    }

    private static <T extends Item> int getItemByIndex(Item item, ArrayList<T> cashItems) {
        for (int i = 0; i < cashItems.size(); i++) {
            if (item.getType().equals(cashItems.get(i).getType())) {
                return i;
            }
        }
        return -1;
    }

    private boolean canContainItem(int quantity, String itemType) {
        boolean canItemBePushed = this.fullness + quantity <= this.capacity;
        boolean conditionForAdding = false;
        switch (itemType) {
            case "Cash":
                conditionForAdding = this.goldAmount >= this.gemAmount
                        && this.gemAmount >= this.cashAmount + quantity;
                break;
            case "Gem":
                conditionForAdding = this.goldAmount >= this.gemAmount + quantity
                        && this.gemAmount + quantity >= this.cashAmount;
                break;
            case "Gold":
                conditionForAdding = this.goldAmount + quantity >= this.gemAmount
                        && this.gemAmount >= this.cashAmount;
        }

        return canItemBePushed && conditionForAdding;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String goldInfo = this.getGoldInfo();
        if (goldInfo != null) {
            builder.append(goldInfo);
        }
        String gemInfo = this.getGemInfo();
        if (gemInfo != null) {
            builder.append(gemInfo);
        }
        String cashInfo = this.getCashInfo();
        if (cashInfo != null) {
            builder.append(cashInfo);
        }
        return builder.toString();
    }

    private String getCashInfo() {
        if (this.cashItems.size() == 0) {return null;}
        StringBuilder builder = new StringBuilder();
        builder.append("<Cash> $").append(String.format("%d%n", this.cashAmount));
        this.cashItems.sort(Bag.DEFAULT_COMPARATOR);
        for (Cash cashItem : this.cashItems) {
            builder.append(cashItem.toString()).append(System.lineSeparator());
        }
        return builder.toString();
    }

    private String getGemInfo() {
        if (this.gemItems.size() == 0) { return null; }
        StringBuilder builder = new StringBuilder();
        builder.append("<Gem> $").append(String.format("%d%n", this.gemAmount));
        this.gemItems.sort(Bag.DEFAULT_COMPARATOR);
        for (Gem item : this.gemItems) {
            builder.append(item.toString()).append(System.lineSeparator());
        }
        return builder.toString();
    }

    private String getGoldInfo() {
        if (this.gold == null) { return null; }
        StringBuilder builder = new StringBuilder();
        builder.append("<Gold> $").append(String.format("%d%n", this.goldAmount));
        builder.append(this.gold.toString()).append(System.lineSeparator());
        return builder.toString();
    }
}
