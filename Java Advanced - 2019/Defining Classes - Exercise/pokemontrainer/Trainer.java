package pokemontrainer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Trainer {
    private String name;
    private int badges;
    private ArrayList<Pokemon> pokemon;

    public Trainer(String name) {
        this.pokemon = new ArrayList<>();
        this.badges = 0;
        this.name = name;
    }

    public ArrayList<Pokemon> getPokemon() {
        return this.pokemon;
    }

    public int getBadges() {
        return this.badges;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemon.add(pokemon);
    }

    public static boolean isTrainerExist(LinkedHashMap<String, Trainer> trainers, String trainerName) {
        return trainers.containsKey(trainerName);
    }

    public boolean hasValidPokemon(String element) {
        int countPokemonWithThisElement = 0;

        for (Pokemon pokemon : this.pokemon) {
            if (pokemon.getElement().equals(element)) {
                countPokemonWithThisElement++;
            }
        }

        return countPokemonWithThisElement >= 1;
    }

    public void incrementBadges() {
        this.badges++;
    }

    public void takeDamageToAllPokemon() {
        ArrayList<Integer> indexesOfDeadPokemon = new ArrayList<>();

        for (Pokemon pokemon : this.pokemon) {
            pokemon.takeDamage();
            if (pokemon.isDead()) {
                indexesOfDeadPokemon.add(this.pokemon.indexOf(pokemon));
            }
        }
        removeAllDeadPokemon(indexesOfDeadPokemon);
    }

    private void removeAllDeadPokemon(ArrayList<Integer> indexes) {
        for (int i = this.pokemon.size() - 1; i >= 0; i--) {
            if (indexes.contains(i)) {
                this.pokemon.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", this.name, this.badges, this.pokemon.size());
    }
}
