/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

import ResidentsData.Resident;
import java.util.Scanner;

public class PearlJam {

    protected String name;
    protected List<Resident> waitingList;
    protected List<Resident> orderProcessingList;
    protected final Map<String, Double> menu;
    private final Scanner sc = new Scanner(System.in);

    public PearlJam(String name) {
        this.name = name;
        this.waitingList = new ArrayList<>();
        this.orderProcessingList = new ArrayList<>();
        this.menu = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addResidentToWaitingList(Resident Resident) {
        waitingList.add(Resident);
    }

    public List<Resident> getWaitingList() {
        return waitingList;
    }

    public List<Resident> getOrderProcessingList() {
        return orderProcessingList;
    }

    public Map<String, Double> getMenu() {
        return menu;
    }

    public void addToMenu(String itemName, double price) {
        menu.put(itemName, price);
    }

    public double getPrice(String itemName) {
        return menu.getOrDefault(itemName, 0.0);
    }

    public void displayWaitingList() {
        String format = "| %2s | %-22s | %-3s | %-6s | %-35s |%n";
        System.out.println("Waiting List");
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(24) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+"
                + "-".repeat(37) + "+");
        System.out.printf(format, "No", "Name", "Age", "Gender", "Order");
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(24) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+"
                + "-".repeat(37) + "+");
        for (int i = 0; i < waitingList.size(); i++) {
            Resident resident = waitingList.get(i);
            System.out.printf(format, i + 1, resident.getName(), resident.getAge(),
                    resident.getGender(), resident.getOrderHistory().get(resident.getOrderHistory().size() - 1).getFood());
        }
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(24) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+"
                + "-".repeat(37) + "+\n");
    }

    public void displayOrderProcessingList() {
        String format = "| %2s | %-22s | %-3s | %-6s | %-35s |%n";
        System.out.println("Order Processing List");
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(24) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+"
                + "-".repeat(37) + "+");
        System.out.printf(format, "No", "Name", "Age", "Gender", "Order");
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(24) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+"
                + "-".repeat(37) + "+");
        for (int i = 0; i < orderProcessingList.size(); i++) {
            Resident resident = orderProcessingList.get(i);
            System.out.printf(format, i + 1, resident.getName(), resident.getAge(),
                    resident.getGender(), resident.getOrderHistory().get(resident.getOrderHistory().size() - 1).getFood());
        }
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(24) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+"
                + "-".repeat(37) + "+");
        System.out.println("=".repeat(70));
    }

    public void viewMenu() {
        String header = "| %2s | %-35s | %-5s |%n";
        String format = "| %2s | %-35s | %5.2f |%n";
        System.out.println("Restaurant: " + getName() + "\n");
        System.out.println("Menu");
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(37) + "+" + "-".repeat(7) + "+");
        System.out.printf(header, "No", "Food", "Price");
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(37) + "+" + "-".repeat(7) + "+");
        int i = 1;
        for (String food : menu.keySet()) {
            System.out.printf(format, i++, food, menu.get(food));
        }
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(37) + "+" + "-".repeat(7) + "+");
        System.out.println("=".repeat(70));
    }

    protected void modifyFoodName() {
        System.out.print("Enter food name: ");
        String foodName = sc.nextLine();
        if (!menu.containsKey(foodName)) {
            System.out.println("Food not found.");
            return;
        }
        System.out.print("Enter new name: ");
        String newName = sc.nextLine();

        menu.put(newName, menu.get(foodName));
        menu.remove(foodName);
        System.out.println("Food name modified successfully.");
    }

    protected void modifyFoodPrices() {
        System.out.print("Enter food name: ");
        String foodName = sc.nextLine();
        if (!menu.containsKey(foodName)) {
            System.out.println("Food not found.");
            return;
        }

        double newPrice;
        while (true) {
            try {
                System.out.print("Enter new price: $");
                newPrice = sc.nextDouble();
                sc.nextLine(); // Consume newline character
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again. ");
                sc.nextLine();
            }
        }

        menu.replace(foodName, newPrice);

        System.out.println("Food price modified successfully.");
    }

    protected void addNewFood() {
        System.out.print("Enter food name: ");
        String foodName = sc.nextLine();
        if (menu.containsKey(foodName)) {
            System.out.println("Food already exists.");
            return;
        }
        double price;
        while (true) {
            try {
                System.out.print("Enter price: $");
                price = sc.nextDouble();
                sc.nextLine(); // Consume newline character
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again. ");
                sc.nextLine();
            }
        }

        addToMenu(foodName, price);

        System.out.println("Food added successfully.");
    }

    protected void removeFood() {
        System.out.print("Enter food name: ");
        String foodName = sc.nextLine();
        if (!menu.containsKey(foodName)) {
            System.out.println("Food not found.");
            return;
        }

        menu.remove(foodName);

        System.out.println("Food removed successfully.");
    }

    protected void modifyMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Restaurant: " + name);
            System.out.println("\n[1] Create new food");
            System.out.println("[2] Modify food name");
            System.out.println("[3] Modify food price");
            System.out.println("[4] Remove food from menu");
            System.out.println("[5] Exit\n");

            System.out.print("Select: ");
            String select = sc.nextLine();
            
            if (select == ""||select.matches("\\s+")) {
                System.out.println("Invalid input. Please reselect.");
                continue;
            }
            switch (select) {
                case "1":
                    addNewFood();
                    break;
                case "2":
                    modifyFoodName();
                    break;
                case "3":
                    modifyFoodPrices();
                    break;
                case "4":
                    removeFood();
                    break;
                case "5":
                    exit = true;
                    System.out.println("=".repeat(70));
                    return;
                default:
                    System.out.println("Option " + select + " is not available. Please reselect.");
            }
        }
    }
}
