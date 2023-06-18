/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MoodyBlues {

    public static void action(PearlJam restaurant, String csvFile, boolean MilagroMan) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Restaurant: " + restaurant.getName() + (MilagroMan ? "(Milagro Man Mode)" : ""));
            System.out.println("Sales Information");
            System.out.println("[1] View Sales");
            System.out.println("[2] View Aggregated Information");
            System.out.println("    [A] Minimum Sales");
            System.out.println("    [B] Maximum Sales");
            System.out.println("    [C] Top k Highest Sales");
            System.out.println("    [D] Total and Average Sales");
            System.out.println("[3] Exit");

            System.out.print("\nSelect: ");
            String choice = input.nextLine();
            if (choice == "" || choice.matches("\\s+")) {
                System.out.println("Invalid input. Please reselect.");
                continue;
            }
            try {
                int startDay = 0;
                int endDay = 0;
                int k = 0;
                switch (choice) {
                    case "1":
                        System.out.print("Enter Day: ");
                        int day = input.nextInt();
                        input.nextLine();
                        showSalesInformation(restaurant, day, csvFile, MilagroMan);
                        break;
                    case "2A":
                        System.out.print("Enter Start Day: ");
                        startDay = input.nextInt();
                        System.out.print("Enter End Day: ");
                        endDay = input.nextInt();
                        input.nextLine();
                        System.out.println("======================================================================");
                        showMinimumSalesForFood(restaurant, startDay, endDay, csvFile, MilagroMan);
                        break;
                    case "2B":
                        System.out.print("Enter Start Day: ");
                        startDay = input.nextInt();
                        System.out.print("Enter End Day: ");
                        endDay = input.nextInt();
                        input.nextLine();
                        System.out.println("======================================================================");
                        showMaximumSalesForFood(restaurant, startDay, endDay, csvFile, MilagroMan);
                        break;
                    case "2C":
                        System.out.print("Enter Start Day: ");
                        startDay = input.nextInt();
                        System.out.print("Enter End Day: ");
                        endDay = input.nextInt();
                        System.out.print("Enter top K sales: ");
                        k = input.nextInt();
                        System.out.println("======================================================================");
                        input.nextLine();
                        showTopKHighestSales(restaurant, startDay, endDay, k, csvFile, MilagroMan);
                        break;
                    case "2D":
                        System.out.print("Enter Start Day: ");
                        startDay = input.nextInt();
                        System.out.print("Enter End Day: ");
                        endDay = input.nextInt();
                        input.nextLine();
                        System.out.println("======================================================================");
                        showTotalAndAverageSales(restaurant, startDay, endDay, csvFile, MilagroMan);
                        break;
                    case "3":
                        System.out.println("=".repeat(70));
                        return;
                    default:
                        System.out.println("Option " + choice + " is not available. Please reselect.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static void showSalesInformation(PearlJam restaurant, int day, String csv, boolean MilagroMan) {
        List<FoodItem> sales = new ArrayList<>();
        double totalPrice = 0.0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                String csvRestaurant = values[0];
                int csvDay = Integer.parseInt(values[1]);
                if (csvRestaurant.equals(restaurant.getName()) && csvDay == day) {
                    String food = values[2];
                    int quantity = Integer.parseInt(values[3]);
                    double price = Double.parseDouble(values[4]);
                    FoodItem foodItem = new FoodItem(restaurant.getName(), food, quantity, price);
                    sales.add(foodItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Restaurant: " + restaurant.getName() + (MilagroMan ? "(Milagro Man Mode)" : ""));
        System.out.println("Day " + day + " Sales");
        System.out.println("+-------------------------------------+-------------+---------------+");
        System.out.format("| %-35s | %-11s | %-13s |\n", "Food", "Quantity", "Total Price");
        System.out.println("+-------------------------------------+-------------+---------------+");

        if (sales.isEmpty()) {
            System.out.println("| No sales data available             |             |               |");
        } else {
            for (FoodItem foodItem : sales) {
                double price = foodItem.getPrice();
                double totalIndividualPrice = price * foodItem.getQuantity();
                totalPrice += totalIndividualPrice;
                System.out.format("| %-35s | %11d | $%12.2f |\n", foodItem.getFood(), foodItem.getQuantity(),
                        totalIndividualPrice);
            }
        }

        System.out.println("+-------------------------------------+-------------+---------------+");
        System.out.format("| %49s | $%12.2f |\n", "Total Sales", totalPrice);
        System.out.println("+-------------------------------------+-------------+---------------+");
    }

    public static void showTotalAndAverageSales(PearlJam restaurant, int startDay, int endDay, String csv,
            boolean MilagroMan) {

        int numberOfDay = endDay - startDay + 1;
        Map<String, Integer> totalQuantityMap = new HashMap<>();
        Map<String, Double> totalPriceMap = new HashMap<>();
        double totalRevenue = 0.0;

        for (int day = startDay; day <= endDay; day++) {
            List<FoodItem> sales = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(csv))) {
                String line;

                reader.readLine();

                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");

                    String csvRestaurant = values[0];
                    int csvDay = Integer.parseInt(values[1]);

                    if (csvRestaurant.equals(restaurant.getName()) && csvDay == day) {
                        String food = values[2];
                        int quantity = Integer.parseInt(values[3]);
                        double price = Double.parseDouble(values[4]);
                        FoodItem foodItem = new FoodItem(restaurant.getName(), food, quantity, price);
                        sales.add(foodItem);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (FoodItem foodItem : sales) {
                String food = foodItem.getFood();
                int quantity = foodItem.getQuantity();
                double price = foodItem.getPrice();
                double totalPrice = price * quantity;

                totalQuantityMap.put(food, totalQuantityMap.getOrDefault(food, 0) + quantity);
                totalPriceMap.put(food, totalPriceMap.getOrDefault(food, 0.0) + totalPrice);
                totalRevenue += totalPrice;
            }
        }
        System.out.println("Restaurant: " + restaurant.getName() + (MilagroMan ? "(Milagro Man Mode)" : ""));
        System.out.println("Total and Average Sales (Day " + startDay + " - " + endDay + ")");
        System.out.println("+-------------------------------------+-------------+---------------+");
        System.out.format("| %-35s | %-11s | %-13s |\n", "Food", "Quantity", "Total Price");
        System.out.println("+-------------------------------------+-------------+---------------+");
        if (totalQuantityMap.isEmpty()) {
            System.out.println("| No sales data available             |             |               |");
        } else {
            for (Map.Entry<String, Integer> entry : totalQuantityMap.entrySet()) {
                String food = entry.getKey();
                int quantity = entry.getValue();
                double totalPrice = totalPriceMap.get(food);
                System.out.format("| %-35s | %11d | $%12.2f |\n", food, quantity, totalPrice);
            }
        }
        System.out.println("+-------------------------------------+-------------+---------------+");
        System.out.format("| %49s | $%12.2f |\n", "Total Sales", totalRevenue);
        System.out.format("| %49s | $%12.2f |\n", "Average Sales", totalRevenue / numberOfDay);
        System.out.println("+-------------------------------------+-------------+---------------+");
    }

    public static void showTopKHighestSales(PearlJam restaurant, int startDay, int endDay, int k, String csv,
            boolean MilagroMan) {

        Map<String, Double> foodSalesMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String csvRestaurant = values[0];
                int csvDay = Integer.parseInt(values[1]);

                if (csvRestaurant.equals(restaurant.getName()) && csvDay >= startDay && csvDay <= endDay) {
                    String food = values[2];
                    int quantity = Integer.parseInt(values[3]);
                    double price = Double.parseDouble(values[4]);
                    double totalSales = price * quantity;

                    foodSalesMap.put(food, foodSalesMap.getOrDefault(food, 0.0) + totalSales);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Restaurant: " + restaurant.getName() + (MilagroMan ? "(Milagro Man Mode)" : ""));
        System.out.println("Top " + k + " Highest Sales(Day " + startDay + " - " + endDay + ")");
        System.out.println("+-------------------------------------+-------------+---------------+");
        System.out.format("| %-35s | %-11s | %-13s |\n", "Food", "Quantity", "Total Sales");
        System.out.println("+-------------------------------------+-------------+---------------+");

        if (foodSalesMap.isEmpty()) {
            System.out.println("| No sales data available             |             |               |");
        } else {
            List<Map.Entry<String, Double>> sortedSalesList = new ArrayList<>(foodSalesMap.entrySet());
            sortedSalesList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            int count = 0;
            for (Map.Entry<String, Double> entry : sortedSalesList) {
                if (count >= k) {
                    break;
                }

                String food = entry.getKey();
                double totalSales = entry.getValue();
                int quantity = (int) (totalSales / restaurant.getPrice(food));

                System.out.format("| %-35s | %11d | $%12.2f |\n", food, quantity, totalSales);
                count++;
            }
        }

        System.out.println("+-------------------------------------+-------------+---------------+");
    }

    public static void showMinimumSalesForFood(PearlJam restaurant, int startDay, int endDay, String csv,
            boolean MilagroMan) {
        showAggregateInformation(restaurant, startDay, endDay, csv, "Minimum Sales", MilagroMan);
    }

    public static void showMaximumSalesForFood(PearlJam restaurant, int startDay, int endDay, String csv,
            boolean MilagroMan) {
        showAggregateInformation(restaurant, startDay, endDay, csv, "Maximum Sales", MilagroMan);
    }

    public static void showAggregateInformation(PearlJam restaurant, int startDay, int endDay, String csv, String title,
            boolean MilagroMan) {

        Map<String, Double> foodSalesMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String csvRestaurant = values[0];
                int csvDay = Integer.parseInt(values[1]);

                if (csvRestaurant.equals(restaurant.getName()) && csvDay >= startDay && csvDay <= endDay) {
                    String food = values[2];
                    int quantity = Integer.parseInt(values[3]);
                    double price = Double.parseDouble(values[4]);
                    double totalSales = price * quantity;

                    foodSalesMap.put(food, foodSalesMap.getOrDefault(food, 0.0) + totalSales);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Restaurant: " + restaurant.getName() + (MilagroMan ? "(Milagro Man Mode)" : ""));
        System.out.println(title + " (Day " + startDay + " - " + endDay + ")");
        System.out.println("+-------------------------------------+-------------+---------------+");
        System.out.format("| %-35s | %-11s | %-13s |\n", "Food", "Quantity", "Total Sales");
        System.out.println("+-------------------------------------+-------------+---------------+");

        if (foodSalesMap.isEmpty()) {
            System.out.println("| No sales data available             |             |               |");
        } else {
            double desiredSales = title.startsWith("Minimum") ? Collections.min(foodSalesMap.values())
                    : Collections.max(foodSalesMap.values());

            for (Map.Entry<String, Double> entry : foodSalesMap.entrySet()) {
                String food = entry.getKey();
                double totalSales = entry.getValue();

                if (totalSales == desiredSales) {
                    int quantity = (int) (totalSales / restaurant.getPrice(food));
                    System.out.format("| %-35s | %11d | $%12.2f |\n", food, quantity, totalSales);
                }
            }
        }
        System.out.println("+-------------------------------------+-------------+---------------+");
    }
}
