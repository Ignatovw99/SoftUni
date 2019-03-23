package collections;

import interfaces.MyList;

public class MyListImpl extends Collection implements MyList {
    @Override
    public int add(String item) {
        return this.addFirst(item);
    }

    @Override
    public String remove() {
        return this.removeFirst();
    }

    @Override
    public int getUsed() {
        return this.getItems().size();
    }
}
