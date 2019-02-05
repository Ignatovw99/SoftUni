import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Inferno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> gems = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        LinkedHashMap<String, UnaryOperator<ArrayList<Integer>>> operators = new LinkedHashMap<>();

        String line = scanner.nextLine();

        while (!"Forge".equals(line)) {
            String[] tokens = line.split(";");
            String command = tokens[0];
            String filterType = tokens[1];
            int filterParameter = Integer.parseInt(tokens[2]);

            String operatorName = String.format("%s %s", filterType, filterParameter);

            if (command.equals("Exclude")) {
                UnaryOperator<ArrayList<Integer>> operator = null;

                ArrayList<Integer> indexesToDelete = new ArrayList<>();

                switch (filterType) {
                    case "Sum Left":
                        operator = arr -> {
                            for (int i = 0; i < arr.size(); i++) {
                                int left;
                                int current = arr.get(i);
                                if (i == 0) {
                                    left = 0;
                                } else {
                                    left = arr.get(i - 1);
                                }

                                if (left + current == filterParameter) {
                                    indexesToDelete.add(i);
                                }
                            }
                            return indexesToDelete;
                        };
                        break;
                    case "Sum Right":
                        operator = arr -> {
                            for (int i = 0; i < arr.size(); i++) {
                                int right;
                                int current = arr.get(i);
                                if (i + 1 == arr.size()) {
                                    right = 0;
                                } else {
                                    right = arr.get(i + 1);
                                }

                                if (current + right == filterParameter) {
                                    indexesToDelete.add(i);
                                }
                            }
                            return indexesToDelete;
                        };
                        break;
                    case "Sum Left Right":
                        operator = arr -> {
                            for (int i = 0; i < arr.size(); i++) {
                                int right;
                                int left;
                                int current = arr.get(i);

                                if (i != 0) {
                                    left = arr.get(i - 1);
                                } else {
                                    left = 0;
                                }

                                if (i == arr.size() - 1) {
                                    right = 0;
                                } else {
                                    right = arr.get(i + 1);
                                }

                                if (left + current + right == filterParameter) {
                                    indexesToDelete.add(i);
                                }
                            }
                            return indexesToDelete;
                        };
                        break;
                }

                operators.put(operatorName, operator);
            } else if (command.equals("Reverse")) {
                operators.remove(operatorName);
            }

            line = scanner.nextLine();
        }

        ArrayList<String> operatorsToApply = new ArrayList<>(operators.keySet());
        Collections.reverse(operatorsToApply);

        operatorsToApply
                .forEach(operator -> {
                    ArrayList<Integer> indexesToDelete = operators.get(operator).apply(gems);
                    Collections.reverse(indexesToDelete);

                    indexesToDelete
                            .forEach(index -> gems.remove(index.intValue()));
                });

        gems.forEach(x -> System.out.print(x + " "));
    }
}
