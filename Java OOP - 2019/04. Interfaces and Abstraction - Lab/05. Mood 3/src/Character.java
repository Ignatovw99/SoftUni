public abstract class Character<T> implements GameObject<T> {
    private String username;
    private String characterType;
    private int level;
    private Number specialPoints;
    private T hashedPassword;

    protected Character(String username, String characterType, int level, Number specialPoints) {
        this.username = username;
        this.characterType = characterType;
        this.specialPoints = specialPoints;
        this.level = level;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getCharacterType() {
        return this.characterType;
    }

    @Override
    public Number getSpecialPoints() {
        return this.specialPoints;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public T getHashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public void setHashedPassword(T password) {
        this.hashedPassword = password;
    }

    public abstract String getSpecialPointsByLevel();

    @Override
    public String toString() {
        return String.format("\"\"%s\"\" | \"%s\" -> %s%n%s",
                this.getUsername(),
                this.getHashedPassword(),
                this.getCharacterType(),
                this.getSpecialPointsByLevel());
    }
}
