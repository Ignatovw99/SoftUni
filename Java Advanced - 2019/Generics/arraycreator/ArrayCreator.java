package arraycreator;

import java.lang.reflect.Array;

public class ArrayCreator {
    public static <T> T[] create(int length, T item) {
        T[] result = (T[]) Array.newInstance(item.getClass(), length);

        fillArray(result, item);
        return result;
    }

    public static <T> T[] create(Class<T> clazz, int length, T item) {
        T[] result = (T[]) Array.newInstance(clazz, length);

        fillArray(result, item);
        return result;
    }

    private static <T> void fillArray(T[] array, T element) {
        for (int i = 0; i < array.length; i++) {
            array[i] = element;
        }
    }
}
