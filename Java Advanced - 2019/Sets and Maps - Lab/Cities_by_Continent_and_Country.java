import java.util.*;

public class Cities_by_Continent_and_Country {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Map<String, List<String>>> citiesByContinentsAndCountries = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String continent = tokens[0];
            String country = tokens[1];
            String town = tokens[2];

            if (!citiesByContinentsAndCountries.containsKey(continent)) {
                citiesByContinentsAndCountries.put(continent, new LinkedHashMap<>());
                citiesByContinentsAndCountries.get(continent).put(country, new ArrayList<>());
                citiesByContinentsAndCountries.get(continent).get(country).add(town);
            } else {
                if (!citiesByContinentsAndCountries.get(continent).containsKey(country)) {
                    citiesByContinentsAndCountries.get(continent).put(country, new ArrayList<>());
                    citiesByContinentsAndCountries.get(continent).get(country).add(town);
                } else {
                    citiesByContinentsAndCountries.get(continent).get(country).add(town);
                }
            }
        }

        for (Map.Entry<String, Map<String, List<String>>> entry : citiesByContinentsAndCountries.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Map.Entry<String, List<String>> townsByCountry : citiesByContinentsAndCountries.get(entry.getKey()).entrySet()) {
                System.out.print(String.format("  %s -> ", townsByCountry.getKey()));
                for (int i = 0; i < townsByCountry.getValue().size(); i++) {
                    String town = townsByCountry.getValue().get(i);
                    if (i == townsByCountry.getValue().size() - 1) {
                        System.out.println(town);
                    } else {
                        System.out.print(town + ", ");
                    }
                }
            }
        }
    }
}
