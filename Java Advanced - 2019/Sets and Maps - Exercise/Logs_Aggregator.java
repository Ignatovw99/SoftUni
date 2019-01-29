import java.util.*;
import java.util.stream.Collectors;

public class Logs_Aggregator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> durationByUsers = new TreeMap<>();
        Map<String, Set<String>> ipsByUsers = new TreeMap<>();
        int countLogLines = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < countLogLines; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String ip = tokens[0];
            String user = tokens[1];
            int duration = Integer.parseInt(tokens[2]);

            if (!ipsByUsers.containsKey(user)) {
                ipsByUsers.put(user, new TreeSet<>(){{add(ip);}});
                durationByUsers.put(user, duration);
            } else {
                ipsByUsers.get(user).add(ip);
                durationByUsers.put(user, durationByUsers.get(user) + duration);
            }
        }

        durationByUsers.entrySet().stream()
                .forEach(entry -> {
                    String user = entry.getKey();
                    int totalDuration = entry.getValue();
                    List<String> userIps = ipsByUsers.get(user).stream().collect(Collectors.toList());
                    System.out.println(String.format("%s: %d ", user, totalDuration) + userIps);
                });
    }
}
