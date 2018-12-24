import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Anonymous_Threat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> data = Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .collect(Collectors.toList());

        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("3:1")){
            String[] tokens = command.split("\\s+");
            switch (tokens[0]){
                case "merge":
                    int startIndex = setIndex(Integer.parseInt(tokens[1]), data);
                    int endIndex = setIndex(Integer.parseInt(tokens[2]), data);

                    merge(startIndex, endIndex, data);
                    break;
                case "divide":
                    int index = Integer.parseInt(tokens[1]);
                    int partitions = Integer.parseInt(tokens[2]);

                    if (partitions > 0){
                        divide(index, partitions, data);
                    }
            }
            command = scanner.nextLine();
        }
        data.
                forEach(e -> System.out.print(e + " "));
    }

    private static void divide(int index, int partitions, List<String> data) {
        List<String> dividedStrings = new ArrayList<>();
        String stringToDivide = data.get(index);
        int partLength = stringToDivide.length() / partitions;

        for (int i = 0; i < partitions; i++){
            if (i == partitions - 1){
                dividedStrings.add(stringToDivide);
            } else {
                String dividedPart = stringToDivide.substring(0, partLength);
                dividedStrings.add(dividedPart);
                stringToDivide = stringToDivide.substring(partLength);
            }
        }

        data.remove(data.get(index));
        data.addAll(index, dividedStrings);
    }

    private static void merge(int startIndex, int endIndex, List<String> data) {
        List<String> elementsToMerge = new ArrayList<>();
        StringBuilder merged = new StringBuilder();

        for (int i = startIndex; i <= endIndex; i++) {
            elementsToMerge.add(data.get(i));
            merged.append(data.get(i));
        }

        data.removeAll(elementsToMerge);
        data.add(startIndex, merged.toString());
    }

    private static int setIndex(int index, List<String> data) {
        if (index >= data.size()){
            return data.size() - 1;
        } else if (index < 0){
            return index = 0;
        }

        return index;
    }
}
