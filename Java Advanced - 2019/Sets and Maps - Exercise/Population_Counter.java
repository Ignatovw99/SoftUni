import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Population_Counter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Map<String, Long>> citiesAndPopulationByCountry = new LinkedHashMap<>();
        Map<String, Long> populationByCountry = new LinkedHashMap<>();
        String input = scanner.nextLine();

        while (!"report".equals(input)) {
            String[] tokens = input.split("\\|");
            String city = tokens[0];
            String country = tokens[1];
            long population = Long.parseLong(tokens[2]);

            if (!citiesAndPopulationByCountry.containsKey(country)) {
                citiesAndPopulationByCountry.put(country, new LinkedHashMap<>(){{put(city, population);}});
                populationByCountry.put(country, population);
            } else {
                if (!citiesAndPopulationByCountry.get(country).containsKey(city)) {
                    citiesAndPopulationByCountry.get(country).put(city, population);
                } else {
                    citiesAndPopulationByCountry.get(country).put(city, citiesAndPopulationByCountry.get(country).get(city) + population);
                }
                populationByCountry.put(country, populationByCountry.get(country) + population);
            }

            input = scanner.nextLine();
        }

        populationByCountry.entrySet().stream()
                .sorted((country1, country2) -> {
                    int populationCompare = country2.getValue().compareTo(country1.getValue());
                    return populationCompare;
                })
                .forEach(entry -> {
                    String country = entry.getKey();
                    long totalPopulation = entry.getValue();
                    System.out.println(String.format("%s (total population: %d)", country, totalPopulation));
                    citiesAndPopulationByCountry.get(country).entrySet().stream()
                            .sorted((city1, city2) -> {
                                return city2.getValue().compareTo(city1.getValue());
                            })
                            .forEach(cityEntry -> {
                                String city = cityEntry.getKey();
                                long cityPopulation = cityEntry.getValue();
                                System.out.println(String.format("=>%s: %d", city, cityPopulation));
                            });
                });
    }
}
