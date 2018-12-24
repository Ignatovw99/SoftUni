import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> wagonsByPassengers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int wagonCapacity = Integer.parseInt(scanner.nextLine());

        String cmd = scanner.nextLine();

        while (!cmd.equalsIgnoreCase("end")){
            String[] tokens = cmd.split("\\s+");
            switch (tokens[0]){
                case "Add":
                    int passengers = Integer.parseInt(tokens[1]);
                    if (passengers <= wagonCapacity){
                        wagonsByPassengers.add(passengers);
                    }
                    break;
                    default:
                        passengers = Integer.parseInt(tokens[0]);
                        for (int i = 0; i < wagonsByPassengers.size(); i++) {
                            if (passengers + wagonsByPassengers.get(i) <= wagonCapacity){
                                wagonsByPassengers.set(i, passengers + wagonsByPassengers.get(i));
                                break;
                            }
                        }
                        break;
            }
            cmd = scanner.nextLine();
        }

        wagonsByPassengers
                .forEach(w -> System.out.print(w + " "));
    }
}
