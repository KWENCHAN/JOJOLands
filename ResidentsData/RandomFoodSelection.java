package ResidentsData;

import pearljam.PearlJam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomFoodSelection {

    private List<PearlJam> restaurants;
    private Random random;

    public RandomFoodSelection(List<PearlJam> restaurants) {
        this.restaurants = restaurants;
        random = new Random();
    }

    public String getRandomFoodItem() {
        // Select a random restaurant
        PearlJam randomRestaurant = restaurants.get(random.nextInt(restaurants.size()));

        // Get the menu of the selected restaurant
        Map<String, Double> menu = randomRestaurant.getMenu();

        // Select a random food item from the menu
        List<String> foodItems = new ArrayList<>(menu.keySet());
        String randomFoodItem = foodItems.get(random.nextInt(foodItems.size()));

        return randomFoodItem;
    }

    public void performActions() {
        // Example usage
        PearlJam trattoria = new PearlJam("Trattoria Trussardi");
        PearlJam savageGarden = new PearlJam("Savage Garden");
        PearlJam liberrio = new PearlJam("Liberrio");
        PearlJam libeccio = new PearlJam("Libeccio");
        PearlJam jadeGarden = new PearlJam("Jade Garden");
        PearlJam cafeDeuxMagots = new PearlJam("Cafe Deux Magots");

        List<PearlJam> restaurants = new ArrayList<>();
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
