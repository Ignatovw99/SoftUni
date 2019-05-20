public class Warrior implements Attacker {
    private String id;
    private int damage;
    private Target target;
    private int xp;

    public Warrior(String id, int damage) {
        this.id = id;
        this.damage = damage;
        this.xp = 0;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public Target getTarget() {
        return this.target;
    }

    @Override
    public void setTarget(Target target) {
        this.target = target;
    }

    @Override
    public int getXp() {
        return this.xp;
    }

    @Override
    public void gainXp(int xp) {
        this.xp += xp;
    }
}
