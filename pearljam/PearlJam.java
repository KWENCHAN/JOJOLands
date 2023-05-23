/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PearlJam {

    private String name;
    protected List<Customer> waitingList;
    protected List<Customer> orderProcessingList;
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

    public void addCustomerToWaitingList(Customer customer) {
        waitingList.add(customer);
    }

    public void addToMenu(String itemName, double price) {
        menu.put(itemName, price);
    }

    public double getPrice(String itemName) {
        return menu.getOrDefault(itemName, 0.0);
    }
    
    public void displayWaitingList() {
        String format = "| %-2s | %-18s | %-3s | %-6s | %-35s |%n";
        System.out.println("Waiting List");
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(20) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+" + "-".repeat(37) + "+");
        System.out.printf(format, "No", "Name", "Age", "Gender", "Order");
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(20) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+" + "-".repeat(37) + "+");
        for (int i = 0; i < waitingList.size(); i++) {
            System.out.printf(format, i + 1, waitingList.get(i).getName(), waitingList.get(i).getAge(), waitingList.get(i).getGender(), waitingList.get(i).getOrder());
        }
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(20) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+" + "-".repeat(37) + "+");
    }

    public void displayOrderProcessingList() {
        String format = "| %-2s | %-18s | %-3s | %-6s | %-35s |%n";
        System.out.println("Order Processing List");
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(20) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+" + "-".repeat(37) + "+");
        System.out.printf(format, "No", "Name", "Age", "Gender", "Order");
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(20) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+" + "-".repeat(37) + "+");
        for (int i = 0; i < orderProcessingList.size(); i++) {
            System.out.printf(format, i + 1, orderProcessingList.get(i).getName(), orderProcessingList.get(i).getAge(), orderProcessingList.get(i).getGender(), orderProcessingList.get(i).getOrder());
        }
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(20) + "+" + "-".repeat(5) + "+" + "-".repeat(8) + "+" + "-".repeat(37) + "+");
    }

}
