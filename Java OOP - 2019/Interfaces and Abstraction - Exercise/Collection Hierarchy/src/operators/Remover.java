package operators;

import interfaces.AddRemovable;

public class Remover {
    public static void deleteItemsFrom(AddRemovable collection, int itemsToRemove) {
        for (int i = 0; i < itemsToRemove; i++) {
            System.out.print(collection.remove());
            if (i < itemsToRemove - 1) {
                System.out.print(" ");
            } else {
                System.out.println();
            }
        }
    }
}
