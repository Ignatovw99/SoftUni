package models;

import interfaces.Private;

import java.util.Comparator;

public class PrivateImpl extends SoldierImpl implements Private {
    private static Comparator<Private> DEFAULT_COMPARATOR = new Comparator<Private>() {
        @Override
        public int compare(Private first, Private second) {
            return second.getId() - first.getId();
        }
    };

    private double salary;

    public PrivateImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    protected static Comparator<Private> getDefaultComparator() {
        return PrivateImpl.DEFAULT_COMPARATOR;
    }

    @Override
    public String toString() {
        return String.format("%s Salary: %.2f",
                super.toString(),
                this.getSalary());
    }
}
