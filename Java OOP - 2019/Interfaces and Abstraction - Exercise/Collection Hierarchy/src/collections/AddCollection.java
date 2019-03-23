package collections;

import interfaces.Addable;

public class AddCollection extends Collection implements Addable {
    @Override
    public int add(String item) {
        return this.addLast(item);
    }
}
