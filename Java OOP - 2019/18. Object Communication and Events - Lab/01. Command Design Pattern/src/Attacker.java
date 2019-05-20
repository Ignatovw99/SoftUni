public interface Attacker {
    int getDamage();

    void setTarget(Target target);

    Target getTarget();

    void gainXp(int xp);

    int getXp();
}
