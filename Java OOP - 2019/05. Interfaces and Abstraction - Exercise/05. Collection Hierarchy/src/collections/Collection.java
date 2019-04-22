package collections;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {
    private int maxSize = 100;
    private List<String> items;

    protected Collection() {
        this.items = new ArrayList<>();
    }

    protected List<String> getItems() {
        return this.items;
    }

    protected int addFirst(String item) {
        this.getItems().add(0, item);
        return 0;
    }

    protected int addLast(String item) {
        this.getItems().add(item);
        return this.getItems().size() - 1;
    }

    protected String removeFirst() {
        String removedElement = this.getItems().get(0);
        this.getItems().remove(0);
        return removedElement;
    }

    protected String removeLast() {
        String removedElement = this.getItems().get(this.getItems().size() - 1);
        this.getItems().remove(this.getItems().size() - 1);
        return removedElement;
    }
}
