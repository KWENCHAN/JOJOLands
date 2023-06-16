/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

public class FoodItem {
        private String restaurant;
        private int day;
        private String food;
        private int quantity;

        public FoodItem(String restaurant, int day, String food, int quantity) {
            this.restaurant = restaurant;
            this.day = day;
            this.food = food;
            this.quantity = quantity;
        }

        public String getRestaurant() {
            return restaurant;
        }

        public int getDay() {
            return day;
        }

        public String getFood() {
            return food;
        }

        public int getQuantity() {
            return quantity;
        }
    }