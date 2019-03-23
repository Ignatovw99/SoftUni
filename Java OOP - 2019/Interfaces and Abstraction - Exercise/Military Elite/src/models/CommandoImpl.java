package models;

import enumerations.Corp;
import interfaces.Commando;
import interfaces.Mission;

import java.util.ArrayList;
import java.util.List;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private List<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corp corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new ArrayList<>();
    }

    @Override
    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(System.lineSeparator()).append("Missions:");

        this.missions.forEach(mission -> {
            builder.append(System.lineSeparator());
            builder.append("  ").append(mission.toString());
        });

        return builder.toString();
    }
}
