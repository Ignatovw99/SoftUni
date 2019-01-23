import java.util.ArrayDeque;
import java.util.Scanner;

public class Printer_Queue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        ArrayDeque<String> printerQueue = new ArrayDeque<>();

        while (!line.equals("print")) {
            if (line.equals("cancel")) {
                if (printerQueue.isEmpty()) {
                    System.out.println("Printer is on standby");
                } else {
                    String removedFile = printerQueue.poll();
                    System.out.println("Canceled " + removedFile);
                }
            } else {
                printerQueue.offer(line);
            }

            line = scanner.nextLine();
        }

        while (!printerQueue.isEmpty()) {
            System.out.println(printerQueue.pollFirst());
        }
    }
}
