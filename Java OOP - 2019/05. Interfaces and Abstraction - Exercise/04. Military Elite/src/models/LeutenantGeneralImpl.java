package models;

import interfaces.LeutenantGeneral;
import interfaces.Private;

import java.util.TreeSet;

public class LeutenantGeneralImpl extends PrivateImpl implements LeutenantGeneral {
    private TreeSet<Private> privates;

    public LeutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new TreeSet<>(getDefaultComparator());
    }

    @Override
    public void addPrivate(Private priv) {
        this.privates.add(priv);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(System.lineSeparator()).append("Privates:");

        for (Private priv : this.privates) {
            builder.append(System.lineSeparator());
            builder.append("  ").append(priv.toString());
        }

        return builder.toString();
    }
}
