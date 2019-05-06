package comparators;

import models.Race;
import models.boat.Boat;

import java.util.Comparator;

public class BoatSpeedComparator implements Comparator<Boat> {
    private Race race;

    public BoatSpeedComparator(Race race) {
        this.race = race;
    }

    @Override
    public int compare(Boat first, Boat second) {
        double firstSpeed = first.calculateSpeed(this.race);
        double secondSpeed = second.calculateSpeed(this.race);
        if (firstSpeed < 0 && secondSpeed < 0) {
            return 0;
        }
        return Double.compare(secondSpeed, firstSpeed);
    }
}
