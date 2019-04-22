package models;

import enumerations.Corp;
import interfaces.SpecialisedSoldier;

public class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    private Corp corps;

    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, Corp corps) {
        super(id, firstName, lastName, salary);
        this.corps = corps;
    }

    @Override
    public String toString() {
        return String.format("%s" + System.lineSeparator() + "Corps: %s",
                super.toString(),
                this.corps.toString());
    }
}
