import java.util.Scanner;

public class Party_Profit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int partySize = Integer.parseInt(scanner.nextLine());
        int days = Integer.parseInt(scanner.nextLine());

        int totalCoins = 0;

        for (int currentDay = 1; currentDay <= days; currentDay++) {
            if (currentDay % 15 == 0){
                partySize += 5;
            }
            if (currentDay % 10 == 0){
                partySize -= 2;
            }
            totalCoins += 50;
            totalCoins -= 2 * partySize;
            if (currentDay % 3 == 0){
                totalCoins -= 3 * partySize;
            }
            if (currentDay % 5 == 0){
                if (currentDay % 3 == 0){
                    totalCoins -= 2 * partySize;
                }
                totalCoins += 20 * partySize;
            }
        }
        int coinsPerOneCompanion = totalCoins / partySize;
        System.out.println(String.format("%d companions received %d coins each.", partySize, coinsPerOneCompanion));
    }
}
