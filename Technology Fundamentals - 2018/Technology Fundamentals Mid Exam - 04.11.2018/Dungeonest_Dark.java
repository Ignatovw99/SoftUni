import java.util.Scanner;

public class Dungeonest_Dark {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int health = 100;
        int coins = 0;

        String[] dungeonsRooms = scanner.nextLine().split("\\|");

        for (int currentRoom = 0; currentRoom < dungeonsRooms.length; currentRoom++){
            String[] room = dungeonsRooms[currentRoom].split("\\s+");
            String item = room[0];
            int number = Integer.parseInt(room[1]);

            switch (item){
                case "potion":
                    int energyCanGet = 100 - health;
                    if (number > energyCanGet){
                        number = energyCanGet;
                    }
                    health += number;
                    System.out.println(String.format("You healed for %d hp.", number));
                    System.out.println(String.format("Current health: %d hp.", health));
                    break;
                case "chest":
                    coins += number;
                    System.out.println(String.format("You found %d coins.", number));
                    break;
                    default:
                        health -= number;
                        if (health > 0){
                            System.out.println(String.format("You slayed %s.", item));
                        } else {
                            System.out.println("You died! Killed by " + item + ".");
                            System.out.println("Best room: " + (currentRoom + 1));
                            return;
                        }
                        break;
            }
        }
        System.out.println("You've made it!");
        System.out.println("Coins: " + coins);
        System.out.println("Health: " + health);
    }
}
