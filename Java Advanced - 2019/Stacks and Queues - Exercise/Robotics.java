import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Robotics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] robots = scanner.nextLine().split(";");

        String[] robotsNames = new String[robots.length];
        int[] workingTime = new int[robots.length];
        int[] processingTime = new int[robots.length];

        for (int i = 0; i < robots.length; i++) {
            robotsNames[i] = robots[i].split("-")[0];
            workingTime[i] = Integer.parseInt(robots[i].split("-")[1]);
        }

        String[] startingTime = scanner.nextLine().split(":");

        int hours = Integer.parseInt(startingTime[0]) * 3600;
        int minutes = Integer.parseInt(startingTime[1]) * 60;
        int seconds = Integer.parseInt(startingTime[2]);

        int timeInSeconds = hours + minutes + seconds;
        int time = 0;

        ArrayDeque<String> products = new ArrayDeque<>();

        String inputProduct = scanner.nextLine();

        while (!inputProduct.equals("End")) {
            products.offer(inputProduct);
            inputProduct = scanner.nextLine();
        }

        while (!products.isEmpty()) {
            time++;
            String productToProcess = products.poll();

            boolean isProductAssigned = false;

            for (int i = 0; i < robots.length; i++) {
                if (processingTime[i] == 0 && !isProductAssigned) {
                    processingTime[i] = workingTime[i];
                    int currentHour = ((timeInSeconds + time) / 3600) % 24;
                    int currentMinutes = ((timeInSeconds + time) % 3600) / 60;
                    int currentSeconds = (timeInSeconds + time) % 60;

                    DecimalFormat format = new DecimalFormat("00");

                    System.out.println(String.format("%s - %s [%s:%s:%s]",
                            robotsNames[i], productToProcess, format.format(currentHour), format.format(currentMinutes), format.format(currentSeconds)));

                    isProductAssigned = true;
                }
                if (processingTime[i] > 0) {
                    processingTime[i]--;
                }
            }

            if (!isProductAssigned) {
                products.offer(productToProcess);
            }
        }
    }
}
