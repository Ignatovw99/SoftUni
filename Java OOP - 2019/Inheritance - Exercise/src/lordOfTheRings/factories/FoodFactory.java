package lordOfTheRings.factories;

import lordOfTheRings.models.food.*;

public class FoodFactory {
    public static Food getFood(String name) {
        String food = name.toLowerCase();
        switch (food) {
            case "cram":
                return new Cram(2);
            case "apple":
                return new Apple(1);
            case "lembas":
                return new Lembas(3);
            case "melon":
                return new Melon(1);
            case "honeycake":
                return new HoneyCake(5);
            case "mushrooms":
                return new Mushrooms(-10);
            default:
                return new UnknownFood(-1);
        }
    }
}
