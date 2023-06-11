import JojoLand.Restaurant;
import pearljam.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomFoodSelection {

    private List<Restaurant> restaurants;
    private Random random;

    public RandomFoodSelection(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        random = new Random();
    }

    public String getRandomFoodItem() {
        // Select a random restaurant
        Restaurant randomRestaurant = restaurants.get(random.nextInt(restaurants.size()));

        // Get the menu of the selected restaurant
        Map<String, Double> menu = randomRestaurant.getMenu();

        // Select a random food item from the menu
        List<String> foodItems = new ArrayList<>(menu.keySet());
        String randomFoodItem = foodItems.get(random.nextInt(foodItems.size()));

        return randomFoodItem;
    }

    public void performActions() {
        // Example usage
        TrattoriaTrussardi trattoria = new TrattoriaTrussardi();
        SavageGarden savageGarden = new SavageGarden();
        Liberrio liberrio = new Liberrio("Liberrio");
        Libeccio libeccio = new Libeccio();
        JadeGarden jadeGarden = new JadeGarden();
        CafeDeuxMagots cafeDeuxMagots = new CafeDeuxMagots();

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(trattoria);
        restaurants.add(savageGarden);
        restaurants.add(liberrio);
        restaurants.add(libeccio);
        restaurants.add(jadeGarden);
        restaurants.add(cafeDeuxMagots);

        RandomFoodSelection randomFoodSelection = new RandomFoodSelection(restaurants);
        String randomFoodItem = randomFoodSelection.getRandomFoodItem();
        System.out.println("Randomly selected food item: " + randomFoodItem);
    }
}
