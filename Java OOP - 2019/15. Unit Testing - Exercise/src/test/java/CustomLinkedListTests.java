import org.junit.Before;
import org.junit.Test;
import p05_CustomLinkedList.CustomLinkedList;

import static org.junit.Assert.assertEquals;

public class CustomLinkedListTests {
    private static int INITIAL_COUNT = 0;
    private static String ELEMENT = "test";
    private static int EXPECTED_COUNT_VALUE_BY_ADDING = 2;

    private CustomLinkedList<String> customLinkedList;

    @Before
    public void createCustomLinkedList() {
        this.customLinkedList = new CustomLinkedList<>();
    }

    @Test
    public void emptyCustomLinkedListShouldReturnZeroCount() {
        assertEquals(INITIAL_COUNT, this.customLinkedList.getCount());
    }

    @Test
    public void addShouldSetFieldCountToCountOfAddedElements() {
        this.customLinkedList.add(ELEMENT);
        this.customLinkedList.add(ELEMENT);
        assertEquals(EXPECTED_COUNT_VALUE_BY_ADDING, this.customLinkedList.getCount());
    }
}
