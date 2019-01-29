import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Serbian_Unleashed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Map<String, Integer>> concerts = new LinkedHashMap<>();
        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            //Validate input data
            if (!input.contains(" @")) {
                input = scanner.nextLine();
                continue;
            }
            String singer = input.substring(0, input.indexOf(" @"));
            int firstIndexDigitOccurrence = -1;
            for (int i = input.indexOf("@"); i < input.length(); i++) {
                if (Character.isDigit(input.charAt(i)) && input.charAt(i - 1) == ' ') {
                    firstIndexDigitOccurrence = i;
                    break;
                }
            }
            if (firstIndexDigitOccurrence == -1) {
                input = scanner.nextLine();
                continue;
            }
            String venue = input.substring(input.indexOf("@") + 1, firstIndexDigitOccurrence - 1).trim();
            String[] data = input.substring(firstIndexDigitOccurrence).split(" ");
            if (data.length != 2) {
                input = scanner.nextLine();
                continue;
            }

            int ticketPrice = Integer.parseInt(data[0]);
            int ticketsCount = Integer.parseInt(data[1]);

            if (!concerts.containsKey(venue)) {
                concerts.put(venue, new LinkedHashMap<>(){{put(singer, ticketPrice * ticketsCount);}});
            } else {
                if (!concerts.get(venue).containsKey(singer)) {
                    concerts.get(venue).put(singer, ticketPrice * ticketsCount);
                } else {
                    concerts.get(venue).put(singer, concerts.get(venue).get(singer) + ticketPrice * ticketsCount);
                }
            }

            input = scanner.nextLine();
        }

        concerts.entrySet()
                .forEach(entry -> {
                    String venue = entry.getKey();
                    Map<String, Integer> profitBySinger = entry.getValue();
                    System.out.println(venue);
                    profitBySinger.entrySet().stream()
                            .sorted((singer1, singer2) -> {
                                return singer2.getValue().compareTo(singer1.getValue());
                            })
                            .forEach(e -> {
                                String singer = e.getKey();
                                Integer profit = e.getValue();
                                System.out.println(String.format("#  %s -> %d", singer, profit));
                            });
                });
    }
}
