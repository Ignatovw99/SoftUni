import java.util.*;
import java.util.stream.Collectors;

public class Courses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> coursesByParticipants = new LinkedHashMap<>();

        String cmd = scanner.nextLine();

        while (!cmd.equalsIgnoreCase("end")){
            String[] tokens = cmd.split(" : ");
            String course = tokens[0];
            String student = tokens[1];

            if (!coursesByParticipants.containsKey(course)){
                coursesByParticipants.put(course, new ArrayList<>());
            }
            if (!coursesByParticipants.get(course).contains(student)){
                coursesByParticipants.get(course).add(student);
            }
            cmd = scanner.nextLine();
        }

        coursesByParticipants.entrySet()
                .stream()
                .sorted((course1, course2) -> {
                    return course2.getValue().size() - course1.getValue().size();
                })
                .forEach(entry -> {
                    System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue().size()));
                    List<String> students = entry.getValue().stream().sorted(String::compareTo).collect(Collectors.toList());
                    for (String student : students) {
                        System.out.println("-- " + student);
                    }
                });

    }
}
