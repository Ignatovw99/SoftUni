package greedytimes;

import java.util.ArrayList;

public class Treasure {
    private ArrayList<Item> validItems;

    public Treasure() {
        this.validItems = new ArrayList<>();
    }

    public ArrayList<Item> getValidItems() {
        return this.validItems;
    }

    public void filterItems(String[] safeContent) {
        for (int indexOfContent = 0; indexOfContent < safeContent.length; indexOfContent += 2) {
            String itemType = safeContent[indexOfContent];
            int itemQuantity = Integer.parseInt(safeContent[indexOfContent + 1]);
            if (Cash.isValid(itemType)) {
                this.validItems.add(new Cash(itemType, itemQuantity));
            } else if (Gem.isValid(itemType)) {
                this.validItems.add(new Gem(itemType, itemQuantity));
            } else if(Gold.isValid(itemType)) {
                this.validItems.add(new Gold(itemType, itemQuantity));
            }
        }
    }
}
