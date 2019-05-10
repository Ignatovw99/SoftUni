import org.junit.Before;
import org.junit.Test;
import p02_ExtendedDatabase.Database;
import p02_ExtendedDatabase.Person;

import javax.naming.OperationNotSupportedException;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class ExtendedDatabaseTests {
    private static final Person[] INITIAL_ELEMENTS = {new Person(1, "Alex"),
            new Person(2, "Jorgy")};
    private static final Person[] OVERRANGED_ELEMENTS = new Person[17];
    private static final int ID = 1;
    private static final int EXPECTED_ELEMENTS_LENGTH_AFTER_REMOVING = ExtendedDatabaseTests.INITIAL_ELEMENTS.length - 1;
    private static final String SEARCH_CORRECT_USERNAME = "Alex";
    private static final String SEARCH_INCORRECT_USERNAME = "John";

    private Database database;

    @Before
    public void createDatabase() throws OperationNotSupportedException {
        this.database = new Database(ExtendedDatabaseTests.INITIAL_ELEMENTS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void constructorShouldThrowExceptionWithoutElements() throws OperationNotSupportedException {
        Database db = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void constructorShouldThrowExceptionWithMoreThanSixteenPeople() throws OperationNotSupportedException {
        Database db = new Database(ExtendedDatabaseTests.OVERRANGED_ELEMENTS);
    }

    @Test
    public void constructingDatabaseShouldSetElementsCountCorrectly() {
        assertEquals(ExtendedDatabaseTests.INITIAL_ELEMENTS.length, this.database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByIdShouldThrowExceptionIfIdIsNotPresent() throws OperationNotSupportedException {
        this.database.findById(6);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByIdShouldThrowExceptionIfManyPeopleHaveSameId() throws OperationNotSupportedException {
        Database database = new Database(new Person(1, "John"), new Person(1, "Peter"));
        database.findById(ExtendedDatabaseTests.ID);
    }

    @Test
    public void findByIdShouldReturnCorrectResult() throws OperationNotSupportedException {
        Person expectedPerson = this.database.getElements()[0];
        Person actualPerson = this.database.findById(ExtendedDatabaseTests.ID);
        assertEquals(expectedPerson, actualPerson);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addShouldThrowExceptionWhenNullIsEntered() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void removeShouldDecreaseElementsCount() throws OperationNotSupportedException, NoSuchFieldException, IllegalAccessException {
        this.database.remove();
        Field elementsCount = Database.class.getDeclaredField("elementsCount");
        elementsCount.setAccessible(true);
        int actualElementsCount = elementsCount.getInt(this.database);

        assertEquals(ExtendedDatabaseTests.EXPECTED_ELEMENTS_LENGTH_AFTER_REMOVING, actualElementsCount);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removeShouldThrowExceptionOnEmptyDatabase() throws OperationNotSupportedException {
        Database db = new Database(new Person(1,"Georgi"));
        db.remove();
        db.remove();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByUsernameShouldThrowExceptionIfNoUserIsPresentWithGivenName() throws OperationNotSupportedException {
        this.database.findByUsername(ExtendedDatabaseTests.SEARCH_INCORRECT_USERNAME);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByUsernameShouldThrowExceptionIfMultipleUsersArePresentWithGivenName() throws OperationNotSupportedException {
        Database db = new Database(new Person(ExtendedDatabaseTests.ID, "Ivan"),
                new Person(3, "Ivan"));
        db.findByUsername("Ivan");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByUsernameShouldThrowExceptionWithNullParam() throws OperationNotSupportedException {
        this.database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findByUsernameCannotFindPersonWithGivenUsername() throws OperationNotSupportedException {
        this.database.findByUsername("Somebody");
    }

    @Test
    public void findByUsernameShouldReturnCorrectResult() throws OperationNotSupportedException {
        Person expectedPerson = this.database.getElements()[0];
        Person actualPerson = this.database.findByUsername(ExtendedDatabaseTests.SEARCH_CORRECT_USERNAME);
        assertEquals(expectedPerson, actualPerson);
    }
}
