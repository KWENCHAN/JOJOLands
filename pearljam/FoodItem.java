/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

public class FoodItem {

    private String restaurant;
    private String food;
    private int quantity;
    private double price;

    public FoodItem(String restaurant, String food, int quantity, double price) {
        this.restaurant = restaurant;
        this.food = food;
        this.quantity = quantity;
        this.price = price;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public String getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
