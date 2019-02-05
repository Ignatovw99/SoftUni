import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Predicate_Party {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> guests = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayList::new));
        Map<String, Integer> guestsOccurrences = initializeMap(guests);

        String line = scanner.nextLine();

        while (!"Party!".equals(line)) {
            String[] tokens = line.split("\\s+");
            String command = tokens[0];
            String criteria = tokens[1];
            String parameter = tokens[2];

            Predicate<String> predicate = null;

            switch (criteria) {
                case "StartsWith":
                    predicate = name -> name.startsWith(parameter);
                    break;
                case "EndsWith":
                    predicate = name -> name.endsWith(parameter);
                    break;
                case "Length":
                    predicate = name -> name.length() == Integer.parseInt(parameter);
                    break;
            }

            Predicate<String> finalPredicate = predicate;

            if (command.equals("Double")) {
                guestsOccurrences
                        .forEach((name, value) -> {
                            if (finalPredicate != null && finalPredicate.test(name)) {
                                guestsOccurrences.put(name, guestsOccurrences.get(name) * 2);
                            }
                        });
            } else if (command.equals("Remove")) {
                ArrayList<String> guestsToRemove = new ArrayList<>();
                guestsOccurrences
                        .forEach((name, value) -> {
                            if (finalPredicate != null && finalPredicate.test(name)) {
                                guestsToRemove.add(name);
                            }
                        });

                for (String currentGuest : guestsToRemove) {
                    guestsOccurrences.remove(currentGuest);
                    guests.remove(currentGuest);
                }
            }
            line = scanner.nextLine();
        }

        if (guestsOccurrences.size() != 0) {
            Collections.sort(guests);
            StringBuilder allGuest = new StringBuilder();

            for (String guest : guests) {
                int count = guestsOccurrences.get(guest);
                while (count > 0) {
                    allGuest.append(String.format("%s, ", guest));
                    count--;
                }
            }

            allGuest = new StringBuilder(allGuest.substring(0, allGuest.lastIndexOf(",")));

            System.out.println(String.format("%s are going to the party!", allGuest.toString()));
        } else {
            System.out.println("Nobody is going to the party!");
        }
    }

    private static Map<String, Integer> initializeMap(ArrayList<String> guests) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < guests.size(); i++) {
            if (!map.containsKey(guests.get(i))) {
                map.put(guests.get(i), 1);
            } else {
                map.put(guests.get(i), map.get(guests.get(i)) + 1);
            }
        }
        return map;
    }
}
