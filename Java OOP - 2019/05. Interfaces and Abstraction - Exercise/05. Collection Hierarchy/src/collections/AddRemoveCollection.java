package collections;

import interfaces.AddRemovable;

public class AddRemoveCollection extends Collection implements AddRemovable {
    @Override
    public int add(String item) {
        return this.addFirst(item);
    }

    @Override
    public String remove() {
        return this.removeLast();
    }
}
