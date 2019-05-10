import org.junit.Test;
import p03_IteratorTest.ListIterator;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

public class ListIteratorTests {
    private static final String[] NULL_VALUE_ARRAY = null;
    private static final String[] INITIAL_THREE_ELEMENTS = {"first", "second", "third"};
    private static final String[] INITIAL_TWO_ELEMENTS = {"first", "second"};
    private static final String[] INITIAL_NO_ELEMENTS_ARRAY = new String[0];
    private static final String INITIAL_ONE_ELEMENT = "FIRST";

    @Test(expected = OperationNotSupportedException.class)
    public void constructingListIteratorShouldThrowExceptionIfNullIsPassed() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(NULL_VALUE_ARRAY);
    }

    @Test
    public void constructingListIteratorShouldSetItsElementsCorrectly() throws OperationNotSupportedException,
            NoSuchFieldException, IllegalAccessException {
        ListIterator listIterator = new ListIterator(INITIAL_THREE_ELEMENTS);
        Field elements = ListIterator.class.getDeclaredField("elements");
        elements.setAccessible(true);
        List<String> listIteratorElements = (List<String>) elements.get(listIterator);
        assertArrayEquals(INITIAL_THREE_ELEMENTS, listIteratorElements.toArray());
    }

    @Test
    public void hasNextShouldReturnFalseIfCurrentElementIsTheLastOne() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(INITIAL_ONE_ELEMENT);
        assertFalse(listIterator.hasNext());
    }

    @Test
    public void hasNextShouldReturnTrueWhenNextElementIsPresent() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(INITIAL_TWO_ELEMENTS);
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void moveMethodShouldReturnTrueIfInternalIndexIsMovedCorrectly() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(INITIAL_THREE_ELEMENTS);
        assertTrue(listIterator.move());
    }

    @Test
    public void moveShouldReturnFalseIfInternalIndexIsOnTheLastElement() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(INITIAL_TWO_ELEMENTS);
        listIterator.move();
        assertFalse(listIterator.move());
    }

    @Test(expected = IllegalStateException.class)
    public void printShouldThrowExceptionIfNoElementsArePresentInTheList() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(INITIAL_NO_ELEMENTS_ARRAY);
        listIterator.print();
    }

    @Test
    public void printShouldGetTheCorrectElementOnInternalIndex() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(INITIAL_THREE_ELEMENTS);
        listIterator.move();
        String expectedElement = INITIAL_THREE_ELEMENTS[1];
        String actualElement = listIterator.print();
        assertEquals(expectedElement, actualElement);
    }
}
