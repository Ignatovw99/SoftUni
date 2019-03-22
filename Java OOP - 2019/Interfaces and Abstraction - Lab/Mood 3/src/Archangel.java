public class Archangel extends Character<String> {
    private static final String CHARACTER_TYPE = "Archangel";

    public Archangel(String username, int level, Integer specialPoints) {
        super(username, Archangel.CHARACTER_TYPE, level, specialPoints);
    }

    @Override
    public String getHashedPassword() {
        StringBuilder builder = new StringBuilder(this.getUsername() + "\"");
        builder.reverse().append("\"").append(this.getUsername().length() * 21);
        return builder.toString();
    }

    @Override
    public String getSpecialPointsByLevel() {
        return String.format(
                "%d",
                this.getSpecialPoints().intValue() * this.getLevel());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
