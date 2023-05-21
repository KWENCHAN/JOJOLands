/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.HashMap;
import java.util.Map;

public class CafeDeuxMagots extends PearlJam{
    
    private Map<String, Double> menu;
    
    public CafeDeuxMagots(String name){
        super(name);
        this.menu = new HashMap<>();
        addToMenu("Sampling Matured Cheese Platter", 23);
        addToMenu("Spring Lobster Salad", 35);
        addToMenu("Spring Organic Omelette", 23);
        addToMenu("Truffle-flavoured Poultry Supreme", 34);
        addToMenu("White Asparagus", 26);
    }
    
    public void addToMenu(String itemName, double price){
        menu.put(itemName, price);
    }
    
    public double getPrice(String itemName){
        return menu.getOrDefault(itemName, 0.0);
    }
}
