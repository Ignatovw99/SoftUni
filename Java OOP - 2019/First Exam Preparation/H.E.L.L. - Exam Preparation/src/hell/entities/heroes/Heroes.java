package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.entities.miscellaneous.ItemCollection;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class Heroes implements Hero {
    private String name;
    private int strength;
    private int agility;
    private int intelligence;
    private int hitPoints;
    private int damage;
    private Inventory heroInventory;
    private List<Item> items;

    Heroes(String name, int strength, int agility, int intelligence, int hitPoints, int damage) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.heroInventory = new HeroInventory();
        this.items = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + this.heroInventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + this.heroInventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + this.heroInventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + this.heroInventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + this.heroInventory.getTotalDamageBonus();
    }

    @Override
    public Collection<Item> getItems() {
        Field[] fields = this.heroInventory
                .getClass()
                .getDeclaredFields();

        Field field = null;

        for (Field currentField : fields) {
            if (currentField.isAnnotationPresent(ItemCollection.class)) {
                field = currentField;
                break;
            }
        }

        try {
            field.setAccessible(true);
            return ((Map<String ,Item>) field.get(this.heroInventory)).values();
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    @Override
    public void addItem(Item item) {
        this.heroInventory.addCommonItem(item);
        this.items.add(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.heroInventory.addRecipeItem(recipe);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hero: %s, Class: %s", this.getName(), this.getClass().getSimpleName())).append(System.lineSeparator())
                .append(String.format("HitPoints: %d, Damage: %d", this.getHitPoints(), this.getDamage())).append(System.lineSeparator())
                .append(String.format("Strength: %d", this.getStrength())).append(System.lineSeparator())
                .append("Agility: ").append(this.getAgility()).append(System.lineSeparator())
                .append("Intelligence: ").append(this.getIntelligence()).append(System.lineSeparator())
                .append(this.heroInventory.toString());
        return sb.toString();
    }
}
