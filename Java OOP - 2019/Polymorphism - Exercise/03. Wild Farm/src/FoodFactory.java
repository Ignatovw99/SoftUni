import foodmodels.Food;
import foodmodels.Meat;
import foodmodels.Vegetable;

public class FoodFactory {
    public static Food getFood(String foodType, int quantity) {
        switch (foodType) {
            case "Vegetable":
                return new Vegetable(quantity);
            default:
                return new Meat(quantity);
        }
    }
}
