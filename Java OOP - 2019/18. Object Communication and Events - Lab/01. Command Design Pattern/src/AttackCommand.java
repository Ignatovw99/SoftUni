public class AttackCommand implements Command {
    private Attacker attacker;

    public AttackCommand(Attacker attacker) {
        this.attacker = attacker;
    }

    @Override
    public void execute() {
        if (this.attacker.getTarget() == null) {
            throw new IllegalArgumentException("Attacker has no target set!");
        }

        Target target = this.attacker.getTarget();
        int targetNewHealthPoints = target.getHealthPoints() - this.attacker.getDamage();
        target.setHealthPoints(targetNewHealthPoints);

        if (target.getHealthPoints() <= 0) {
            this.attacker.gainXp(target.getReward());
        }
    }
}
