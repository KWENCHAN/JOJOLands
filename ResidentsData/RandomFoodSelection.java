
import JojoLand.Restaurant;
import pearljam.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
package ResidentsData;

public class RandomFoodSelection {

    TrattoriaTrussardi trattoria = new TrattoriaTrussardi();
    SavageGarden savageGarden = new SavageGarden();
    Liberrio liberrio = new Liberrio("Liberrio");
    Libeccio libeccio = new Libeccio();
    JadeGarden jadeGarden = new JadeGarden();
    CafeDeuxMagots cafeDeuxMagots = new CafeDeuxMagots();

    // Retrieve menus from the respective restaurant classes
    Map<String, Double> trattoriaMenu = trattoria.getMenu();
    Map<String, Double> savageGardenMenu = savageGarden.getMenu();
    Map<String, Double> liberrioMenu = liberrio.getMenu();
    Map<String, Double> libeccioMenu = libeccio.getMenu();
    Map<String, Double> jadeGardenMenu = jadeGarden.getMenu();
    Map<String, Double> cafeDeuxMagotsMenu = cafeDeuxMagots.getMenu();


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
        Menu menu = randomRestaurant.getMenu();

        // Select a random food item from the menu
        List<MenuItem> foodItems = menu.getFoodItems();
        MenuItem randomFoodItem = foodItems.get(random.nextInt(foodItems.size()));

        return randomFoodItem.getName();
    }
}