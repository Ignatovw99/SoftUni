package randomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList<T> extends ArrayList<T> {
    private Random random;

    public RandomArrayList() {
        super();
        this.random = new Random();
    }

    public T getRandomElement() {
        if (super.isEmpty()) {
            throw new IllegalArgumentException("List is empty!");
        }

        int index = this.random.nextInt(super.size());

        return super.remove(index);
    }
}
