import org.junit.Before;
import org.junit.Test;
import p04_BubbleSortTest.Bubble;

import static org.junit.Assert.assertArrayEquals;

public class BubbleSortTests {
    private final int[] EMPTY_ARRAY = new int[0];
    private final int[] SORTED_ARRAY = { -5, 0, 1, 3, 7, 9, 14, 17, 18, 21, 195 };
    private final int[] STIRRED_ARRAY = { 21, 195, 17, 18, 9, 14, 3, 7, -5, 1, 0 };

    private int[] copiedArray;

    private int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, copy.length);
        return copy;
    }

    @Test
    public void shouldWorkCorrectlyWithNoElements() {
        this.copiedArray = this.copyArray(EMPTY_ARRAY);
        Bubble.sort(EMPTY_ARRAY);
        assertArrayEquals(EMPTY_ARRAY, this.copiedArray);
    }

    @Test
    public void shouldNotSortAlreadySortedArray() {
        this.copiedArray = this.copyArray(SORTED_ARRAY);
        Bubble.sort(SORTED_ARRAY);
        assertArrayEquals(SORTED_ARRAY, copiedArray);
    }

    @Test
    public void shouldSortStirredArrayCorrectly() {
        Bubble.sort(STIRRED_ARRAY);
        assertArrayEquals(SORTED_ARRAY, STIRRED_ARRAY);
    }
}
