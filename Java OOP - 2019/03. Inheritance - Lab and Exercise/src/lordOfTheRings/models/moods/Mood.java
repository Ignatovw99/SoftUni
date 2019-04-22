package lordOfTheRings.models.moods;

public abstract class Mood {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
