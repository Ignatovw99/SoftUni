import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Change_List {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("end")){
            String[] tokens = command.split("\\s+");
            int element = Integer.parseInt(tokens[1]);

            switch (tokens[0]){
                case "Delete":
                    delete(list, element);
                    break;
                case "Insert":
                    int position = Integer.parseInt(tokens[2]);
                    insert(list, element, position);
                    break;
            }

            command = scanner.nextLine();
        }
        list.forEach(e -> System.out.print(e + " "));
    }

    private static void insert(List<Integer>list, int element, int position) {
        if (position < list.size()){
            list.add(position, element);
        } else {
            list.add(element);
        }
    }

    private static void delete(List<Integer> list, int element) {
        for (int i = list.size() - 1; i >= 0; i--){
            if (list.get(i).equals(element)){
                list.remove(Integer.valueOf(list.get(i)));
            }
        }
    }
}
