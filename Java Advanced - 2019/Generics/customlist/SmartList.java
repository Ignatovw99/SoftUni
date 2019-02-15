package customlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class SmartList<T extends Comparable<T>> implements Iterable<T>{
    private List<T> data;

    public SmartList() {
        this.data = new ArrayList<>();
    }

    public void add(T element) {
        this.data.add(element);
    }

    public T remove(int index) {
        return this.data.remove(index);
    }

    public boolean contains(T element) {
        return this.data.contains(element);
    }

    public void swap(int firstIndex, int secondIndex) {
        T temp = this.data.get(firstIndex);
        this.data.set(firstIndex, this.data.get(secondIndex));
        this.data.set(secondIndex, temp);
    }

    public int countGreaterThan(T element) {
        return this.data.stream().filter(x -> x.compareTo(element) > 0).toArray().length;
    }

    public T getMax() {
        T startValue = this.data.get(0);
        return this.data.stream().reduce(startValue, (acc, next) -> acc.compareTo(next) > 0 ? acc : next);
    }

    public T getMin() {
        return this.data.stream().min(T::compareTo).get();
    }

    public int size() {
        return this.data.size();
    }

    public T get(int index) {
        return this.data.get(index);
    }

    private final class SmartListIterator implements Iterator<T> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return this.index < data.size();
        }

        @Override
        public T next() {
            return data.get(index++);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SmartListIterator();
    }
}
