public class Main {
    public static void main(String[] args) {
        Attacker warrior = new Warrior("Warrior", 10);
        Target dragon = new Dragon("Dragon", 5, 10);

        Command targetCommand = new TargetCommand(warrior, dragon);
        Command attackCommand = new AttackCommand(warrior);

        targetCommand.execute();
        attackCommand.execute();

        System.out.println(warrior.getXp());
    }
}
