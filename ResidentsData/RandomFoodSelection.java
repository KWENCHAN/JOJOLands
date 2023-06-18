package ResidentsData;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import pearljam.PearlJam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import pearljam.FoodItem;

public class RandomFoodSelection {

    private static Random random = new Random();
    private static boolean freqExceed1 = false;
    private static List<String> JosephHistory = new ArrayList<>();
    private static PearlJam JotaroCurrentRes;
    private static List<String> JotaroFoodHistory = new ArrayList<>();
    private static double JosukeExpense = 0;
    private static List<String> GiornoFoodHistory = new ArrayList<>();
    private static PearlJam JolyneLastRes;
    private static PearlJam JotaroNextRes;
    private static Map<String, Integer> sales = new HashMap<>();
    private static Map<String, Double> priceMap = new HashMap<>();
    private static String csvFile = "C:\\Users\\chank\\OneDrive\\Documents\\UM\\SEM 2\\WIA1002 DATA STRUCTURE\\TestJojo\\src\\pearljam\\orderList.csv";

    public static void selectFood(List<Resident> residentList, List<PearlJam> restaurantList) {
        for (Resident resident : residentList) {
            switch (resident.getName()) {
                case "Jonathan Joestar":
                    selectFoodForJonathanJoestar(resident, restaurantList);
                    break;
                case "Joseph Joestar":
                    selectFoodForJosephJoestar(resident, restaurantList);
                    break;
                case "Jotaro Kujo":
                    selectFoodForJotaroKujo(resident, restaurantList);
                    break;
                case "Josuke Higashikata":
                    selectFoodForJosukeHigashikata(resident, restaurantList);
                    break;
                case "Giorno Giovanna":
                    selectFoodForGiornoGiovanna(resident, restaurantList);
                    break;
                case "Jolyne Cujoh":
                    selectFoodForJolyneCujoh(resident, restaurantList);
                    break;
                default:
                    getRandomFoodItem(resident, restaurantList);
                    break;
            }
        }
    }

    public static Map<String, String> getFood_Res(List<PearlJam> restaurantList) {
        Map<String, String> food_res = new HashMap<>();
        for (PearlJam restaurant : restaurantList) {
            for (String food : restaurant.getMenu().keySet()) {
                food_res.put(food, restaurant.getName());
            }
        }
        return food_res;
    }

    public static Map<String, Double> getFood_$(List<PearlJam> restaurantList) {
        Map<String, Double> food_$ = new HashMap<>();
        for (PearlJam restaurant : restaurantList) {
            for (String food : restaurant.getMenu().keySet()) {
                food_$.put(food, restaurant.getPrice(food));
            }
        }
        return food_$;
    }

    public static void getRandomFoodItem(Resident resident, List<PearlJam> restaurantList) {
        // Select a random food item from the menu
        PearlJam selected_res = restaurantList.get(random.nextInt(restaurantList.size()));
        List<String> menu = new ArrayList<>(selected_res.getMenu().keySet());
        String selectedfood = menu.get(random.nextInt(menu.size()));
        double price = selected_res.getPrice(selectedfood);
        FoodItem foodItem = new FoodItem(selected_res.getName(), selectedfood, 1, price);
        resident.addOrderHistory(foodItem);
        selected_res.addResidentToWaitingList(resident);
        addSales(selectedfood, price);
    }

    /*
    ensuring that he doesn’t eat any food too frequently or infrequently. 
    Specifically, the difference in frequency between the foods he eats most and least should not exceed 1
     */
    public static void selectFoodForJonathanJoestar(Resident resident, List<PearlJam> restaurantList) {
        List<String> foodHistory = new ArrayList<>();
        for (FoodItem foodItem : resident.getOrderHistory()) {
            foodHistory.add(foodItem.getFood());
        }
        Map<String, String> food_res = getFood_Res(restaurantList);
        Map<String, Double> food_$ = getFood_$(restaurantList);
        if (foodHistory.isEmpty()) {
            getRandomFoodItem(resident, restaurantList);
            return;
        }
        int maxFrequency = Collections.frequency(foodHistory, getMostFrequentFood(foodHistory));
        int minFrequency = Collections.frequency(foodHistory, getLeastFrequentFood(foodHistory));

        if (maxFrequency - minFrequency > 1) {
            // If the difference in frequency exceeds 1, select the least frequent food
            String leastfood = getLeastFrequentFood(foodHistory);
            String restaurant = food_res.get(leastfood);
            double price = food_$.get(leastfood);
            FoodItem foodItem = new FoodItem(restaurant, leastfood, 1, price);
            resident.addOrderHistory(foodItem);
            PearlJam resObj = getRestaurantObj(restaurant, restaurantList);
            resObj.addResidentToWaitingList(resident);
            addSales(leastfood, price);
            freqExceed1 = true;
        } else {
            if (freqExceed1) {
                Map<String, String> historyMap = new HashMap<>();
                List<String> restauranthistory = new ArrayList<>();
                for (FoodItem foodItem : resident.getOrderHistory()) {
                    restauranthistory.add(foodItem.getRestaurant());
                }
                for (int i = 0; i < foodHistory.size(); i++) {
                    historyMap.put(foodHistory.get(i), restauranthistory.get(i));
                }
                List<String> food = new ArrayList<>(historyMap.keySet());
                String selected = selectRandomFoodFromList(food);
                String restaurant = food_res.get(selected);
                double price = food_$.get(selected);
                FoodItem foodItem = new FoodItem(restaurant, selected, 1, price);
                resident.addOrderHistory(foodItem);
                PearlJam resObj = getRestaurantObj(restaurant, restaurantList);
                resObj.addResidentToWaitingList(resident);
                addSales(selected, price);
            } else {
                getRandomFoodItem(resident, restaurantList);
            }
        }
    }

    private static PearlJam getRestaurantObj(String resName, List<PearlJam> resList) {
        for (PearlJam res : resList) {
            if (res.getName().equals(resName)) {
                return res;
            }
        }
        return null;
    }

    private static String getMostFrequentFood(List<String> foodHistory) {
        HashMap<String, Integer> count = new HashMap<>();
        for (String food : foodHistory) {
            if (count.containsKey(food)) {
                int freq = count.get(food);
                freq++;
                count.put(food, freq);
            } else {
                count.put(food, 1);
            }
        }

        String maxfood = foodHistory.get(0);
        int maxfreq = count.get(foodHistory.get(0));

        for (String food : count.keySet()) {
            if (count.get(food) > maxfreq) {
                maxfreq = count.get(food);
                maxfood = food;
            }
        }

        return maxfood;
    }

    private static String getLeastFrequentFood(List<String> foodHistory) {

        HashMap<String, Integer> count = new HashMap<>();
        for (String food : foodHistory) {
            if (count.containsKey(food)) {
                int freq = count.get(food);
                freq++;
                count.put(food, freq);
            } else {
                count.put(food, 1);
            }
        }

        int minfreq = Collections.min(count.values());

        ArrayList<String> leastfood = new ArrayList<>();
        for (String food : count.keySet()) {
            if (count.get(food) == minfreq) {
                leastfood.add(food);
            }
        }

        return leastfood.get(random.nextInt(leastfood.size()));
    }

    //won’t eat the same food twice until he’s tried everything currently available in JOJOLand’s.
    public static void selectFoodForJosephJoestar(Resident resident, List<PearlJam> restaurantList) {
        Map<String, String> food_res = getFood_Res(restaurantList);
        Map<String, Double> food_$ = getFood_$(restaurantList);

        if (JosephHistory.containsAll(food_res.keySet())) {
            // The resident has tried all the available foods
            // Reset the food history and start again from the beginning of the menu
            JosephHistory.clear();
        }

        // Select a random food from the menu excluding the foods already tried
        List<String> availableFoods = new ArrayList<>(food_res.keySet());
        availableFoods.removeAll(JosephHistory);
        String selected = selectRandomFoodFromList(availableFoods);
        String restaurant = food_res.get(selected);
        double price = food_$.get(selected);
        FoodItem foodItem = new FoodItem(restaurant, selected, 1, price);
        resident.addOrderHistory(foodItem);
        PearlJam resObj = getRestaurantObj(restaurant, restaurantList);
        resObj.addResidentToWaitingList(resident);
        addSales(selected, price);
        JosephHistory.add(selected);
    }

    private static String selectRandomFoodFromList(List<String> foodList) {
        return foodList.get(random.nextInt(foodList.size()));
    }

    //he will try every dish at one restaurant before moving on to the next.
    public static void selectFoodForJotaroKujo(Resident resident, List<PearlJam> restaurantList) {
        if (JotaroCurrentRes == null) {
            JotaroCurrentRes = restaurantList.get(random.nextInt(restaurantList.size()));
            List<PearlJam> resList = new ArrayList<>(restaurantList);
            resList.remove(JotaroCurrentRes);
            JotaroNextRes = resList.get(random.nextInt(resList.size()));
        }

        PearlJam currentRes = JotaroCurrentRes;
        List<String> foodMenu = new ArrayList<>(currentRes.getMenu().keySet());
        foodMenu.removeAll(JotaroFoodHistory);
        String selected = selectRandomFoodFromList(foodMenu);
        double price = currentRes.getPrice(selected);
        FoodItem foodItem = new FoodItem(currentRes.getName(), selected, 1, price);
        resident.addOrderHistory(foodItem);
        currentRes.addResidentToWaitingList(resident);
        addSales(selected, price);
        /*
         ***************************
         */
        JotaroFoodHistory.add(selected);

        if (JotaroFoodHistory.containsAll(foodMenu)) {
            JotaroFoodHistory.clear();
            List<PearlJam> availableRes = new ArrayList<>(restaurantList);
            JotaroCurrentRes = JotaroNextRes;
            availableRes.remove(JotaroCurrentRes);
            JotaroNextRes = availableRes.get(random.nextInt(availableRes.size()));
        }
    }

    //tight weekly budget of $100
    //If he must go over budget, he’ll borrow the least amount possible from others
    public static void selectFoodForJosukeHigashikata(Resident resident, List<PearlJam> restaurantList) {
        Map<String, String> food_res = getFood_Res(restaurantList);
        Map<String, Double> food_$ = new HashMap<>();
        for (PearlJam restaurant : restaurantList) {
            food_$.putAll(restaurant.getMenu());
        }

        if (resident.getOrderHistory().size() % 7 == 0) {
            JosukeExpense = 0;
        }

        String selected = "";
        double price = 0;
        if (JosukeExpense < 100) {
            List<String> foodMenu = new ArrayList<>(food_res.keySet());
            do {
                //all food price+expenses exceed 100, select cheapest food
                if (foodMenu.size() <= 0) {
                    selected = getCheapestFood(food_$);
                    break;
                }
                selected = foodMenu.get(random.nextInt(foodMenu.size()));
                price = food_$.get(selected);
                if (JosukeExpense + price > 100) {
                    foodMenu.remove(selected);
                }
            } while (JosukeExpense + price > 100);
        } else {
            //find the cheapest food
            selected = getCheapestFood(food_$);
        }
        price = food_$.get(selected);
        JosukeExpense += price;
        String restaurant = food_res.get(selected);
        FoodItem foodItem = new FoodItem(restaurant, selected, 1, price);
        resident.addOrderHistory(foodItem);
        PearlJam resObj = getRestaurantObj(restaurant, restaurantList);
        resObj.addResidentToWaitingList(resident);
        addSales(selected, price);
    }

    public static String getCheapestFood(Map<String, Double> food_$) {
        double min = Collections.min(food_$.values());
        List<String> cheapestfood = new ArrayList<>();
        for (String food : food_$.keySet()) {
            if (food_$.get(food) <= min) {
                cheapestfood.add(food);
            }
        }
        return cheapestfood.get(random.nextInt(cheapestfood.size()));
    }

    //visits Trattoria Trussardi twice a week. 
    //He always orders a different dish than his last visit, except when there’s only one option available.
    public static void selectFoodForGiornoGiovanna(Resident resident, List<PearlJam> restaurantList) {
        Map<String, String> food_res = getFood_Res(restaurantList);
        Map<String, Double> food_$ = getFood_$(restaurantList);
        PearlJam trattoria = null;
        for (PearlJam restaurant : restaurantList) {
            if (restaurant.getName().equals("Trattoria Trussardi")) {
                trattoria = restaurant;
            }
        }

        List<String> foodHistory = new ArrayList<>();
        for (FoodItem foodItem : resident.getOrderHistory()) {
            foodHistory.add(foodItem.getFood());
        }

        if (foodHistory.size() % 7 == 0) {
            GiornoFoodHistory.clear();
        }

        String selected;
        String restaurant;
        //if until weekend have'nt visit Trattoria Trussardi
        if (foodHistory.isEmpty()) {
            List<String> foodMenu = new ArrayList<>(food_res.keySet());
            selected = selectRandomFoodFromList(foodMenu);
            restaurant = food_res.get(selected);
        } else if (foodHistory.size() % 7 == 5 && GiornoFoodHistory.isEmpty()) {
            List<String> TTMenu = new ArrayList<>(trattoria.getMenu().keySet());
            selected = selectRandomFoodFromList(TTMenu);
            restaurant = trattoria.getName();
        } else if (foodHistory.size() % 7 == 6 && GiornoFoodHistory.size() < 2) {
            List<String> TTMenu = new ArrayList<>(trattoria.getMenu().keySet());
            //if menu options more than 1
            if (TTMenu.size() > 1) {
                TTMenu.remove(GiornoFoodHistory.get(GiornoFoodHistory.size() - 1));
            }
            selected = selectRandomFoodFromList(TTMenu);
            restaurant = trattoria.getName();
        } else {
            List<String> foodMenu = new ArrayList<>(food_res.keySet());
            foodMenu.remove(GiornoFoodHistory.isEmpty() ? "" : GiornoFoodHistory.get(GiornoFoodHistory.size() - 1));
            selected = selectRandomFoodFromList(foodMenu);
            restaurant = food_res.get(selected);
        }
        if (restaurant.equals("Trattoria Trussardi")) {
            GiornoFoodHistory.add(selected);
        }
        double price = food_$.get(selected);
        FoodItem foodItem = new FoodItem(restaurant, selected, 1, price);
        resident.addOrderHistory(foodItem);
        PearlJam resObj = getRestaurantObj(restaurant, restaurantList);
        resObj.addResidentToWaitingList(resident);
        addSales(selected, price);
    }

    //avoid dining at the same restaurant twice in a row. 
    //She and Jotaro Kujo, always dine together at the same restaurant every Saturday.
    public static void selectFoodForJolyneCujoh(Resident resident, List<PearlJam> restaurantList) {
        Map<String, Double> food_$ = getFood_$(restaurantList);
        String selected;
        PearlJam restaurant;
        if (resident.getOrderHistory().isEmpty()) {
            restaurant = restaurantList.get(random.nextInt(restaurantList.size()));
            JolyneLastRes = restaurant;
            List<String> foodMenu = new ArrayList<>(restaurant.getMenu().keySet());
            selected = selectRandomFoodFromList(foodMenu);
            double price = food_$.get(selected);
            FoodItem foodItem = new FoodItem(restaurant.getName(), selected, 1, price);
            resident.addOrderHistory(foodItem);
            restaurant.addResidentToWaitingList(resident);
            addSales(selected, price);
            return;
        }

        List<PearlJam> resList = new ArrayList<>(restaurantList);

        //Friday
        if (resident.getOrderHistory().size() % 7 == 5) {
            //to prevent Friday same with Saturday
            resList.remove(JotaroCurrentRes);
            resList.remove(JotaroNextRes);
        }
        resList.remove(JolyneLastRes);
        if (resident.getOrderHistory().size() % 7 == 6) {
            restaurant = JotaroCurrentRes;
        } else {
            restaurant = resList.get(random.nextInt(resList.size()));
        }
        JolyneLastRes = restaurant;
        List<String> foodMenu = new ArrayList<>(restaurant.getMenu().keySet());
        selected = selectRandomFoodFromList(foodMenu);
        double price = food_$.get(selected);
        FoodItem foodItem = new FoodItem(restaurant.getName(), selected, 1, price);
        resident.addOrderHistory(foodItem);
        restaurant.addResidentToWaitingList(resident);
        addSales(selected, price);
    }

    public static void addSales(String food, Double price) {
        if (sales.containsKey(food)) {
            int freq = sales.get(food);
            freq++;
            sales.put(food, freq);
        } else {
            sales.put(food, 1);
        }
        priceMap.put(food, price);
    }

    public static Map<String, Integer> getSales() {
        return sales;
    }

    public static void printSalesCSV(int numOfDay, List<PearlJam> restaurantList) {
        try {
            Map<String, String> food_res = getFood_Res(restaurantList);
            FileWriter fw = new FileWriter(csvFile, true);
            Scanner in = new Scanner(new FileInputStream(csvFile));
            if (!in.hasNextLine()) {
                String header = "Restaurant,Day,Food,Quantity,Price\n";
                fw.write(header);
            }
            for (String food : sales.keySet()) {
                String content = food_res.get(food) + "," + numOfDay + "," + food + "," + sales.get(food) + "," + priceMap.get(food) + "\n";
                fw.write(content);
            }
            fw.close();
            in.close();
            sales.clear();
            priceMap.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearCSV() {
        try {
            FileWriter fileWriter = new FileWriter(csvFile, false);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the file.");
        }
    }

}
