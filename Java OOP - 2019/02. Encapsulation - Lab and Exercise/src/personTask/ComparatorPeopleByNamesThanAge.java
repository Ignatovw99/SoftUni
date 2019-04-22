package personTask;

import java.util.Comparator;

public class ComparatorPeopleByNamesThanAge implements Comparator<Person> {
    @Override
    public int compare(Person first, Person second) {
        int compareResult = first.getFirstName().compareTo(second.getFirstName());

        if (compareResult == 0) {
            return Integer.compare(first.getAge(), second.getAge());
        }

        return compareResult;
    }
}
