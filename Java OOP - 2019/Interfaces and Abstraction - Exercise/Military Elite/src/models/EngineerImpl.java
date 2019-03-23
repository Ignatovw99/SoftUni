package models;

import enumerations.Corp;
import interfaces.Engineer;
import interfaces.Repair;

import java.util.ArrayList;
import java.util.List;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {
    private List<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, Corp corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new ArrayList<>();
    }

    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(System.lineSeparator()).append("Repairs:");

        this.repairs.forEach(repair -> {
            builder.append(System.lineSeparator());
            builder.append("  ").append(repair.toString());
        });

        return builder.toString();
    }
}
