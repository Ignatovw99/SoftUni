import java.util.*;

public class Company_Users {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> companiesByEmployees = new TreeMap<>();

        String cmd = scanner.nextLine();

        while(!cmd.equalsIgnoreCase("End")){
            String[] tokens = cmd.split(" -> ");
            String company = tokens[0];
            String employeeId = tokens[1];

            if (!companiesByEmployees.containsKey(company)){
                companiesByEmployees.put(company, new ArrayList<>());
            }

            if (!companiesByEmployees.get(company).contains(employeeId)){
                companiesByEmployees.get(company).add(employeeId);
            }
            cmd = scanner.nextLine();
        }

        companiesByEmployees.entrySet().stream()
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    entry.getValue()
                            .forEach(employee -> System.out.println("-- " + employee));
                });
    }
}
