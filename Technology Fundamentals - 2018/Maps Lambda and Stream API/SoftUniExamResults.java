import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniExamResults {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> contest = new HashMap<>();
        Map<String, Integer> languageSubmissions = new HashMap<>();

        String cmd = scanner.nextLine();

        while (!cmd.equalsIgnoreCase("exam finished")){
            String[] tokens = cmd.split("-");
            String username = tokens[0];
            if (tokens[1].equals("banned")){
                if (contest.containsKey(username)){
                    contest.remove(username);
                }
            } else {
                String language = tokens[1];
                int points = Integer.parseInt(tokens[2]);

                if (!contest.containsKey(username)){
                    contest.put(username, points);
                } else {
                    if (contest.get(username) < points){
                        contest.put(username, points);
                    }
                }

                if (!languageSubmissions.containsKey(language)){
                    languageSubmissions.put(language, 0);
                }
                languageSubmissions.put(language, languageSubmissions.get(language) + 1);
            }
            cmd = scanner.nextLine();
        }
        System.out.println("Results:");
        contest.entrySet().stream()
                .sorted((user1, user2) -> {
                    int pointsCompare = user2.getValue().compareTo(user1.getValue());
                    if (pointsCompare == 0){
                        return user1.getKey().compareTo(user2.getKey());
                    }
                    return pointsCompare;
                })
                .forEach(entry -> System.out.println(String.format("%s | %d", entry.getKey(), entry.getValue())));

        System.out.println("Submissions:");

        languageSubmissions.entrySet().stream().sorted((s1, s2) -> s2.getValue().compareTo(s1.getValue()))
                .forEach(entry -> System.out.println(String.format("%s - %d", entry.getKey(), entry.getValue())));
    }
}
