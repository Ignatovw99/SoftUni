package lordOfTheRings.models.food;

public abstract class Food {
    private int happinessPoints;

    protected Food(int happinessPoints) {
        this.happinessPoints = happinessPoints;
    }

    public int getHappinessPoints() {
        return this.happinessPoints;
    }
}
