import java.util.*;

public class ClubParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity = Integer.parseInt(scanner.nextLine());

        LinkedHashMap<Character, ArrayList<Integer>> halls = new LinkedHashMap<>();  //Open halls with reservations
        ArrayDeque<String> inputLine = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(" "))
                .forEach(inputLine::push);

        while (!inputLine.isEmpty()) {
            String currentElement = inputLine.pop();
            if (isNumber(currentElement)) {
                addPeopleToHall(halls, Integer.parseInt(currentElement), capacity);
            } else {
                halls.put(currentElement.charAt(0), new ArrayList<>());
            }
        }
    }

    private static void addPeopleToHall(LinkedHashMap<Character, ArrayList<Integer>> halls, int peopleInReservation, int capacity) {
        ArrayList<Character> overflowedHalls = new ArrayList<>(); //This list stores overflowed halls, which you have to remove

        for (Map.Entry<Character, ArrayList<Integer>> entryHall : halls.entrySet()) {
            ArrayList<Integer> peopleInHall = entryHall.getValue();
            if (isHallFree(peopleInHall, peopleInReservation, capacity)) {
                entryHall.getValue().add(peopleInReservation); // add certain reservation
                break;
            } else {
                printHall(entryHall);
                overflowedHalls.add(entryHall.getKey());
            }
        }

        for (Character removedHall : overflowedHalls) {
            halls.remove(removedHall);
        }
    }

    private static void printHall(Map.Entry<Character, ArrayList<Integer>> entryHall) {
        System.out.printf("%c -> ", entryHall.getKey());
        for (int i = 0; i < entryHall.getValue().size(); i++) {
            System.out.print(entryHall.getValue().get(i));
            if (i < entryHall.getValue().size() - 1) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }
    }

    private static boolean isHallFree(ArrayList<Integer> peopleInHall, int peopleInReservation, int capacity) {
        int currentHallCapacity = peopleInHall.stream().reduce(0, (first, second) -> first + second);
        return currentHallCapacity + peopleInReservation <= capacity;
    }

    private static boolean isNumber(String currentElement) {
        try {
            Integer.parseInt(currentElement);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
