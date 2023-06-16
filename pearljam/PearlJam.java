/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ResidentsData.Resident;

public class PearlJam {

    protected String name;
    protected List<Resident> waitingList;
    protected List<Resident> orderProcessingList;
    protected final Map<String, Double> menu;

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
                    resident.getGender(), resident.getOrderHistory().get(resident.getOrderHistory().size() - 1));
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
                    resident.getGender(), resident.getOrderHistory().get(resident.getOrderHistory().size() - 1));
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
}
