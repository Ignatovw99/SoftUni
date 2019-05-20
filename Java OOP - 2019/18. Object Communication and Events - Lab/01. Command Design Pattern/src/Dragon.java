public class Dragon implements Target {
    private String id;
    private int healthPoints;
    private int reward;

    public Dragon(String id, int healthPoints, int reward) {
        this.id = id;
        this.healthPoints = healthPoints;
        this.reward = reward;
    }

    @Override
    public int getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public int getReward() {
        return this.reward;
    }
}
