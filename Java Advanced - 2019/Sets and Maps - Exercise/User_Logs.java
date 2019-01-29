import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class User_Logs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Map<String, Integer>> userLogs = new TreeMap<>();
        String input = scanner.nextLine();

        while (!"end".equals(input)) {
            String username = input.substring(input.lastIndexOf("=") + 1);
            String ip = input.substring(input.indexOf("=") + 1, input.indexOf(" "));

            if (!userLogs.containsKey(username)) {
                userLogs.put(username, new LinkedHashMap<>(){{
                    put(ip, 1);
                }});
            } else {
                if (!userLogs.get(username).containsKey(ip)) {
                    userLogs.get(username).put(ip, 1);
                } else {
                    userLogs.get(username).put(ip, userLogs.get(username).get(ip) + 1);
                }
            }
            input = scanner.nextLine();
        }

        for (String username : userLogs.keySet()) {
            System.out.println(username + ":");
            int ipSize = userLogs.get(username).size() - 1;
            for (String ip : userLogs.get(username).keySet()) {
                if (ipSize != 0) {
                    System.out.print(String.format("%s => %d, ", ip, userLogs.get(username).get(ip)));
                } else {
                    System.out.println(String.format("%s => %d.", ip, userLogs.get(username).get(ip)));
                }
                ipSize--;
            }
        }
    }
}
