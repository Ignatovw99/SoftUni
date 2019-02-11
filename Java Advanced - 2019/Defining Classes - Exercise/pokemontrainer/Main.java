package pokemontrainer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<String, Trainer> trainers = new LinkedHashMap<>();

        String[] lineArguments = scanner.nextLine().split("\\s+");
        String command = lineArguments[0];

        while (!"Tournament".equals(command)) {
            String trainerName = lineArguments[0];
            String pokemonName = lineArguments[1];
            String pokemonElement = lineArguments[2];
            int pokemonHealth = Integer.parseInt(lineArguments[3]);

            if (!Trainer.isTrainerExist(trainers, trainerName)) {
                trainers.put(trainerName, new Trainer(trainerName));
            }

            Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
            trainers.get(trainerName).addPokemon(pokemon);

            lineArguments = scanner.nextLine().split("\\s+");
            command = lineArguments[0];
        }

        command = scanner.nextLine();

        while (!"End".equals(command)) {
            String element = command;
            for (Map.Entry<String, Trainer> trainerEntry : trainers.entrySet()) {
                Trainer trainer = trainerEntry.getValue();
                if (trainer.hasValidPokemon(element)) {
                    trainer.incrementBadges();
                } else {
                    trainer.takeDamageToAllPokemon();
                }
            }
            command = scanner.nextLine();
        }

        trainers.entrySet().stream()
                .sorted((first, second) -> {
                    Trainer firstTrainer = first.getValue();
                    Trainer secondTrainer = second.getValue();

                    return Integer.compare(secondTrainer.getBadges(), firstTrainer.getBadges());
                })
                .forEach(trainerEntry -> System.out.println(trainerEntry.getValue()));
    }
}
