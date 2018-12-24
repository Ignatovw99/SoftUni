import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Pokemon_Dont_Go {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> pokemons = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> removedPokemons = new ArrayList<>();

        while (pokemons.size() > 0){
            int index = Integer.parseInt(scanner.nextLine());
            int removedPokemon = 0;

            if (index >= 0 && index < pokemons.size()){
                removedPokemon = pokemons.remove(index);
                capturePokemon(pokemons, removedPokemon);
            } else if (index < 0){
                removedPokemon = pokemons.get(0);
                pokemons.set(0, pokemons.get(pokemons.size() - 1));
                capturePokemon(pokemons, removedPokemon);
            } else if (index >= pokemons.size()){
                removedPokemon = pokemons.get(pokemons.size() - 1);
                pokemons.set(pokemons.size() - 1, pokemons.get(0));
                capturePokemon(pokemons, removedPokemon);
            }

            removedPokemons.add(removedPokemon);
        }
        System.out.println(removedPokemons.stream().reduce((first, second) -> first + second).orElse(0));
    }

    private static void capturePokemon(List<Integer> pokemons, int removedElement) {
        for (int i = 0; i < pokemons.size(); i++){
            int currentPokemon = pokemons.get(i);
            if (currentPokemon <= removedElement){
                pokemons.set(i, currentPokemon + removedElement);
            } else {
                pokemons.set(i, currentPokemon - removedElement);
            }
        }

    }
}
