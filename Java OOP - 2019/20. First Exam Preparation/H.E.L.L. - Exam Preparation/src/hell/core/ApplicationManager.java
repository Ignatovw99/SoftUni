package hell.core;

import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.factories.HeroFactory;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Manager;
import hell.interfaces.Recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationManager implements Manager {
    private Map<String, Hero> heroes;
    private HeroFactory heroFactory;

    public ApplicationManager(HeroFactory heroFactory) {
        this.heroes = new HashMap<>();
        this.heroFactory = heroFactory;
    }

    @Override
    public String addHero(List<String> arguments) {
        Hero hero = this.heroFactory.produce(arguments);
        this.heroes.put(hero.getName(), hero);
        return String.format("Created %s - %s",
                hero.getClass().getSimpleName(),
                hero.getName());
    }

    @Override
    public String addItem(List<String> arguments) {
        String name = arguments.get(0);
        String heroName = arguments.get(1);
        int strengthBonus = Integer.parseInt(arguments.get(2));
        int agilityBonus = Integer.parseInt(arguments.get(3));
        int intelligenceBonus = Integer.parseInt(arguments.get(4));
        int hitPointsBonus = Integer.parseInt(arguments.get(5));
        int damageBonus = Integer.parseInt(arguments.get(6));

        Item item = new CommonItem(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
        this.heroes.get(heroName).addItem(item);

        return String.format("Added item - %s to Hero - %s", name, heroName);
    }

    @Override
    public String addRecipe(List<String> arguments) {
        String name = arguments.get(0);
        String heroName = arguments.get(1);
        int strengthBonus = Integer.parseInt(arguments.get(2));
        int agilityBonus = Integer.parseInt(arguments.get(3));
        int intelligenceBonus = Integer.parseInt(arguments.get(4));
        int hitPointsBonus = Integer.parseInt(arguments.get(5));
        int damageBonus = Integer.parseInt(arguments.get(6));
        List<String> requiredItems = arguments.stream().skip(7).collect(Collectors.toList());

        Recipe recipe = new RecipeItem(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus, requiredItems);
        this.heroes.get(heroName).addRecipe(recipe);

        return String.format("Added recipe - %s to Hero - %s", name, heroName);
    }

    @Override
    public String inspect(List<String> arguments) {
        return this.heroes.get(arguments.get(0)).toString();
    }

    @Override
    public String quit() {
        List<Hero> orderedHeroes = this.heroes.values().stream()
                .sorted((first, second) -> {
                    long firstSum = first.getStrength() + first.getAgility() + first.getIntelligence();
                    long secondSum = second.getStrength() + second.getAgility() + second.getIntelligence();

                    if (firstSum == secondSum) {
                        firstSum = first.getHitPoints() + first.getDamage();
                        secondSum = second.getHitPoints() + second.getDamage();
                        return Long.compare(secondSum, firstSum);
                    }
                    return Long.compare(secondSum, firstSum);
                })
                .collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        int position = 1;
        for (Hero hero : orderedHeroes) {
            builder.append(String.format("%d. %s: %s", position++, hero.getClass().getSimpleName(), hero.getName())).append(System.lineSeparator())
                    .append(String.format("###HitPoints: %d", hero.getHitPoints())).append(System.lineSeparator())
                    .append(String.format("###Damage: %d", hero.getDamage())).append(System.lineSeparator())
                    .append(String.format("###Strength: %d", hero.getStrength())).append(System.lineSeparator())
                    .append(String.format("###Agility: %d", hero.getAgility())).append(System.lineSeparator())
                    .append(String.format("###Intelligence: %d", hero.getIntelligence())).append(System.lineSeparator())
                    .append(this.getItemsOfHero(hero)).append(System.lineSeparator());
        }

        return builder.substring(0, builder.lastIndexOf(System.lineSeparator()));
    }

    private String getItemsOfHero(Hero hero) {
        StringBuilder builder = new StringBuilder("###Items: ");
        if (hero.getItems().isEmpty()) {
            builder.append("None");
        } else {
            builder.append(hero.getItems().stream().map(Item::getName).collect(Collectors.joining(", ")));
        }
        return builder.toString();
    }
}
