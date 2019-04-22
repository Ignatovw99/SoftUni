import collections.AddCollection;
import collections.AddRemoveCollection;
import collections.MyListImpl;
import operators.Adder;
import operators.Remover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myList = new MyListImpl();

        String[] inputElements = reader.readLine().split("\\s+");

        Adder.insertItemsInto(addCollection, inputElements);
        Adder.insertItemsInto(addRemoveCollection, inputElements);
        Adder.insertItemsInto(myList, inputElements);

        int itemsToRemoveFromCollections = Integer.parseInt(reader.readLine());

        Remover.deleteItemsFrom(addRemoveCollection, itemsToRemoveFromCollections);
        Remover.deleteItemsFrom(myList, itemsToRemoveFromCollections);
    }
}
