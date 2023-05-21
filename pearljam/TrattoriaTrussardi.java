/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.HashMap;
import java.util.Map;

public class TrattoriaTrussardi extends PearlJam{
    
    private Map<String, Double> menu;
    
    public TrattoriaTrussardi(String name){
        super(name);
        this.menu = new HashMap<>();
        addToMenu("Caprese Salad", 10);
        addToMenu("Creme caramel", 6.5);
        addToMenu("Lamb Chops with Apple Sauce", 25);
        addToMenu("Spaghetti alla Puttanesca", 15);
    }
    
    public void addToMenu(String itemName, double price){
        menu.put(itemName, price);
    }
    
    public double getPrice(String itemName){
        return menu.getOrDefault(itemName, 0.0);
    }
}
