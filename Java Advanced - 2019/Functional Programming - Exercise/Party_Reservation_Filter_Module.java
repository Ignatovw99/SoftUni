import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Party_Reservation_Filter_Module {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Predicate<String>> filtersList = new HashMap<>();

        List<String> guests = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        String line = scanner.nextLine();

        while (!"Print".equals(line)) {
            String[] tokens = line.split(";");
            String command = tokens[0];
            String filterType = tokens[1];
            String filterParameter = tokens[2];

            String filterName = String.format("%s %s", filterType, filterParameter);

            if (command.equals("Add filter")) {
                switch (filterType) {
                    case "Starts with":
                        Predicate<String> startsWithCertainLetter = name -> name.startsWith(filterParameter);
                        filtersList.put(filterName, startsWithCertainLetter);
                        break;
                    case "Ends with":
                        Predicate<String> endsWithCertainLetter = name -> name.endsWith(filterParameter);
                        filtersList.put(filterName, endsWithCertainLetter);
                        break;
                    case "Length":
                        Predicate<String> predicateForLength = name -> name.length() == Integer.parseInt(filterParameter);
                        filtersList.put(filterName, predicateForLength);
                        break;
                    case "Contains":
                        Predicate<String> containsCertainString = name -> name.contains(filterParameter);
                        filtersList.put(filterName, containsCertainString);
                        break;
                }
            } else if (command.equals("Remove filter")) {
                filtersList.remove(filterName);
            }

            line = scanner.nextLine();
        }

        for (String guest : guests) {
            boolean isValid = true;

            for (Predicate<String> predicate : filtersList.values()) {
                if (predicate.test(guest)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                System.out.print(guest + " ");
            }
        }
    }
}
