package heroRepository;

import java.util.LinkedHashMap;
import java.util.Map;

public class HeroRepository {
    private Map<String, Hero> data;

    public HeroRepository() {
        this.data = new LinkedHashMap<>();
    }

    public void add(Hero entity) {
        this.data.put(entity.getName(), entity);
    }

    public void remove(String name) {
        this.data.remove(name);
    }

    public Hero getHeroWithHighestStrength() {
        return this.data.entrySet().stream()
                .sorted((firstHero, secondHero) -> {
                    int firstStrength = firstHero.getValue().getItem().getStrength();
                    int secondStrength = secondHero.getValue().getItem().getStrength();
                    return Integer.compare(secondStrength, firstStrength);
                })
                .findFirst()
                .get()
                .getValue();
    }

    public Hero getHeroWithHighestAgility() {
        return this.data.entrySet().stream()
                .sorted((firstHero, secondHero) -> {
                    int secondAgility = secondHero.getValue().getItem().getAgility();
                    int firstAgility = firstHero.getValue().getItem().getAgility();
                    return Integer.compare(secondAgility, firstAgility);
                })
                .findFirst()
                .get().getValue();
    }

    public Hero getHeroWithHighestIntelligence() {
        return this.data.entrySet().stream()
                .sorted((firstHero, secondHero) -> {
                    int firstIntelligence = firstHero.getValue().getItem().getIntelligence();
                    int secondIntelligence = secondHero.getValue().getItem().getIntelligence();
                    return Integer.compare(secondIntelligence, firstIntelligence);
                })
                .findFirst()
                .get().getValue();
    }

    public int getCount() {
        return this.data.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Hero> entry : data.entrySet()) {
            sb.append(entry.getValue().toString()).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
