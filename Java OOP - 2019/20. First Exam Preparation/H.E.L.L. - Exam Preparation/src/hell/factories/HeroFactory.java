package hell.factories;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.interfaces.Hero;

import java.util.List;

public class HeroFactory {
    public Hero produce(List<String> args) {
        String name = args.get(0);
        String type = args.get(1);

        Hero hero;
        switch (type) {
            case "Barbarian":
                hero = new Barbarian(name);
                break;
            case "Assassin":
                hero = new Assassin(name);
                break;
                default:
                    hero = new Wizard(name);
                    break;
        }
        return hero;
    }
}
