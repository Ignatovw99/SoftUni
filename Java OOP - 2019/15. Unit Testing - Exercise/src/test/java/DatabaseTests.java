import org.junit.Assert;
import org.junit.Test;
import p01_Database.Database;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;

public class DatabaseTests {
    private static final Integer[] INITIAL_ELEMENTS = new Integer[] {1, 2, 3,};
    private static final int EXPECTED_ELEMENTS_COUNT_AFTER_REMOVING = DatabaseTests.INITIAL_ELEMENTS.length - 1;
    private static final int SAMPLE_ELEMENT = 12;

    @Test(expected = OperationNotSupportedException.class)
    public void constructorShouldThrowExceptionWithLessThanOneElement() throws OperationNotSupportedException {
        Database db = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void constructorShouldThrowExceptionWithMoreThanSixteenElements() throws OperationNotSupportedException {
        Database db = new Database(new Integer[17]);
    }

    @Test
    public void constructingDatabaseShouldSetElementsCountCorrectly() throws OperationNotSupportedException,
            NoSuchFieldException, IllegalAccessException {
        Database db = new Database(DatabaseTests.INITIAL_ELEMENTS);

        Field elementsCount = Database.class.getDeclaredField("elementsCount");
        elementsCount.setAccessible(true);
        int fieldCurrentValue = elementsCount.getInt(db);

        Assert.assertEquals(DatabaseTests.INITIAL_ELEMENTS.length, fieldCurrentValue);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addNullElementShouldThrowException() throws OperationNotSupportedException {
        Database db = new Database(DatabaseTests.INITIAL_ELEMENTS);
        db.add(null);
    }

    @Test
    public void addElementShouldIncreaseElementsCount() throws OperationNotSupportedException {
        Database db = new Database(DatabaseTests.INITIAL_ELEMENTS);
        db.add(DatabaseTests.SAMPLE_ELEMENT);
        Assert.assertEquals(Integer.valueOf(DatabaseTests.SAMPLE_ELEMENT), db.getElements()[DatabaseTests.INITIAL_ELEMENTS.length]);
    }

    @Test
    public void removeElementShouldRemoveOnlyLastElement() throws OperationNotSupportedException {
        Database db = new Database(DatabaseTests.INITIAL_ELEMENTS);
        db.remove();
        int actualLength = db.getElements().length;
        Assert.assertEquals(DatabaseTests.EXPECTED_ELEMENTS_COUNT_AFTER_REMOVING, actualLength);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removeElementFromEmptyDatabaseShouldThrowException() throws OperationNotSupportedException {
        Database db = new Database(DatabaseTests.SAMPLE_ELEMENT);
        db.remove();
        db.remove();
    }

    @Test
    public void getElementsShouldReturnNotNullableElementsAsArray() throws OperationNotSupportedException {
        Database db = new Database(DatabaseTests.INITIAL_ELEMENTS);
        Integer[] actualElements = db.getElements();
        Assert.assertArrayEquals(DatabaseTests.INITIAL_ELEMENTS, actualElements);
    }
}
