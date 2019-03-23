package operators;

import interfaces.Addable;

public class Adder {
    public static void insertItemsInto(Addable collection, String[] items) {
        for (int i = 0; i < items.length; i++) {
            System.out.print(collection.add(items[i]));
            if (i < items.length - 1) {
                System.out.print(" ");
            } else {
                System.out.println();
            }
        }
    }
}
