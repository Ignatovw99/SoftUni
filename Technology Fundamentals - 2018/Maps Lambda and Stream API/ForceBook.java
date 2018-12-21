import java.util.*;

public class ForceBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String , List<String>> sides = new TreeMap<>();

        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("Lumpawaroo")){
            String[] tokens;
            String forceSide;
            String forceUser;
            boolean isUserExisting = false;

            if (command.contains("|")){
                tokens = command.split(" /| ");
                forceSide = tokens[0];
                forceUser = tokens[2];

                isUserExisting = checkUserExistence(sides, forceSide, forceUser);

                if (!isUserExisting){
                    addUserToSide(sides, forceSide, forceUser);
                }
            } else {
                tokens = command.split(" -> ");
                forceUser = tokens[0];
                forceSide = tokens[1];

                isUserExisting = checkUserExistence(sides, forceSide, forceUser);
                if (isUserExisting){
                    changeSide(sides, forceSide, forceUser);
                } else {
                    addUserToSide(sides, forceSide, forceUser);
                }
                System.out.println(String.format("%s joins the %s side!", forceUser, forceSide));
            }
            command = scanner.nextLine();
        }

        sides.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 0 ? true : false)
                .sorted((side1, side2) -> Integer.compare(side2.getValue().size(), side1.getValue().size()))
                .forEach(entry -> {
                    System.out.println(String.format("Side: %s, Members: %d", entry.getKey(), entry.getValue().size()));
                    entry.getValue()
                            .stream()
                            .sorted(String::compareTo)
                            .forEach(user -> System.out.println("! " + user));
                });
    }

    private static void changeSide(Map<String, List<String>> sides, String forceSide, String forceUser) {
        for (Map.Entry<String, List<String>> side: sides.entrySet()) {
            if (side.getValue().contains(forceUser)){
                sides.get(side.getKey()).remove(forceUser);
            }
        }

        addUserToSide(sides, forceSide, forceUser);
    }

    public static boolean checkUserExistence(Map<String, List<String>> map, String side, String user){
        for (Map.Entry<String, List<String>> currentSide: map.entrySet()) {
            if (currentSide.getValue().contains(user)){
                return true;
            }
        }
        return false;
    }

    public static void addUserToSide(Map<String, List<String>> map, String side, String user){
        if (!map.containsKey(side)){
            map.put(side, new ArrayList<>());
        }
        if (!map.get(side).contains(user)){
            map.get(side).add(user);
        }
    }
}
