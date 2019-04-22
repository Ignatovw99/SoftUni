public class Demon extends Character<Integer> {
    private static final String CHARACTER_TYPE = "Demon";

    public Demon(String username,  int level, Double specialPoints) {
        super(username, Demon.CHARACTER_TYPE, level, specialPoints);
    }

    @Override
    public Integer getHashedPassword() {
        return (this.getUsername().length() + 2) * 217;
    }

    @Override
    public String getSpecialPointsByLevel() {
        return String.format(
                "%.1f",
                this.getSpecialPoints().doubleValue() * this.getLevel());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
