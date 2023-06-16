/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResidentsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import pearljam.CafeDeuxMagots;
import pearljam.JadeGarden;
import pearljam.Libeccio;
import pearljam.PearlJam;
import pearljam.SavageGarden;
import pearljam.TrattoriaTrussardi;

/**
 *
 * @author chank
 */
public class HazimRFS {

    private List<PearlJam> restaurants;
    private Random random;

    public HazimRFS(List<PearlJam> restaurants) {
        this.restaurants = restaurants;
        random = new Random();
    }

    public String getRandomFoodItem(String residentName) {
        PearlJam selectedRestaurant;
        switch (residentName) {
            case "Jonathan Joestar":
                selectedRestaurant = getRestaurantForJonathan();
                break;
            case "Joseph Joestar":
                selectedRestaurant = getRestaurantForJoseph();
                break;
            case "Jotaro Kujo":
                selectedRestaurant = getRestaurantForJotaro();
                break;
            case "Josuke Higashikata":
                selectedRestaurant = getRestaurantForJosuke();
                break;
            case "Giorno Giovanna":
                selectedRestaurant = getRestaurantForGiorno();
                break;
            case "Jolyne Cujoh":
                selectedRestaurant = getRestaurantForJolyne();
                break;
            default:
                selectedRestaurant = getRandomRestaurant();
                break;
        }

        if (selectedRestaurant != null) {
            Map<String, Double> menu = selectedRestaurant.getMenu();
            List<String> foodItems = new ArrayList<>(menu.keySet());
            return foodItems.get(random.nextInt(foodItems.size()));
        }

        return null;
    }

    private PearlJam getRestaurantForJonathan() {
        double minFrequency = Double.MAX_VALUE;
        double maxFrequency = Double.MIN_VALUE;
        PearlJam selectedRestaurant = null;

        for (PearlJam restaurant : restaurants) {
            Map<String, Double> menu = restaurant.getMenu();
            int menuSize = menu.size();
            if (menuSize > 0) {
                double frequency = (double) menuSize / 7.0;
                if (frequency < minFrequency || frequency > maxFrequency) {
                    minFrequency = Math.min(minFrequency, frequency);
                    maxFrequency = Math.max(maxFrequency, frequency);
                    selectedRestaurant = restaurant;
                }
            }
        }

        return selectedRestaurant;
    }

    private PearlJam getRestaurantForJoseph() {
        for (PearlJam restaurant : restaurants) {
            Map<String, Double> menu = restaurant.getMenu();
            if (menu.size() > 0) {
                return restaurant;
            }
        }

        return null;
    }

    private PearlJam getRestaurantForJotaro() {
        for (PearlJam restaurant : restaurants) {
            Map<String, Double> menu = restaurant.getMenu();
            if (!menu.isEmpty()) {
                return restaurant;
            }
        }

        return null;
    }

    private PearlJam getRestaurantForJosuke() {
        double maxPrice = Double.MIN_VALUE;
        PearlJam selectedRestaurant = null;

        for (PearlJam restaurant : restaurants) {
            double totalPrice = calculateTotalPrice(restaurant.getMenu());
            if (totalPrice > maxPrice) {
                maxPrice = totalPrice;
                selectedRestaurant = restaurant;
            }
        }

        return selectedRestaurant;
    }

    private PearlJam getRestaurantForGiorno() {
        for (PearlJam restaurant : restaurants) {
            if (restaurant.getName().equals("Trattoria Trussardi")) {
                Map<String, Double> menu = restaurant.getMenu();
                if (menu.size() > 1) {
                    return restaurant;
                }
            }
        }

        return null;
    }

    private PearlJam getRestaurantForJolyne() {
        // Assuming Jotaro Kujo's restaurant is the first one in the list
        PearlJam jotaroRestaurant = restaurants.get(0);
        if (jotaroRestaurant != null) {
            return jotaroRestaurant;
        }

        return null;
    }

    private double calculateTotalPrice(Map<String, Double> menu) {
        double totalPrice = 0.0;
        for (Double price : menu.values()) {
            totalPrice += price;
        }
        return totalPrice;
    }

    private PearlJam getRandomRestaurant() {
        int index = random.nextInt(restaurants.size());
        return restaurants.get(index);
    }

    public static void main(String[] args) {
        PearlJam trattoria = new TrattoriaTrussardi();
        PearlJam savageGarden = new SavageGarden();
        PearlJam libeccio = new Libeccio();
        PearlJam jadeGarden = new JadeGarden();
        PearlJam cafeDeuxMagots = new CafeDeuxMagots();

        List<PearlJam> restaurants = new ArrayList<>();
        restaurants.add(trattoria);
        restaurants.add(savageGarden);
        restaurants.add(libeccio);
        restaurants.add(jadeGarden);
        restaurants.add(cafeDeuxMagots);

        HazimRFS randomFoodSelection = new HazimRFS(restaurants);

        // Example usage for each resident
        String residentName = "Jonathan Joestar";
        
        String foodItem = randomFoodSelection.getRandomFoodItem(residentName);
        System.out.println(residentName + " selected food item: " + foodItem);

//        residentName = "Joseph Joestar";
//        foodItem = randomFoodSelection.getRandomFoodItem(residentName);
//        System.out.println(residentName + " selected food item: " + foodItem);
//
//        residentName = "Jotaro Kujo";
//        foodItem = randomFoodSelection.getRandomFoodItem(residentName);
//        System.out.println(residentName + " selected food item: " + foodItem);
//
//        residentName = "Josuke Higashikata";
//        foodItem = randomFoodSelection.getRandomFoodItem(residentName);
//        System.out.println(residentName + " selected food item: " + foodItem);
//
//        residentName = "Giorno Giovanna";
//        foodItem = randomFoodSelection.getRandomFoodItem(residentName);
//        System.out.println(residentName + " selected food item: " + foodItem);
//
//        residentName = "Jolyne Cujoh";
//        foodItem = randomFoodSelection.getRandomFoodItem(residentName);
//        System.out.println(residentName + " selected food item: " + foodItem);
//
//        residentName = "Random Resident";
//        foodItem = randomFoodSelection.getRandomFoodItem(residentName);
//        System.out.println(residentName + " selected food item: " + foodItem);
    }
}
