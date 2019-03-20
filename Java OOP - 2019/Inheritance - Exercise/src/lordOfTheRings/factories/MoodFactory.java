package lordOfTheRings.factories;

import lordOfTheRings.models.moods.*;

public class MoodFactory {
    public static Mood getMood(Integer happinessPoint) {
        if (happinessPoint < -5) {
            return new Angry();
        } else if (happinessPoint < 0) {
            return new Sad();
        } else if (happinessPoint <= 15) {
            return new Happy();
        } else {
            return new JavaScript();
        }
    }
}
