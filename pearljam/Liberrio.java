/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.HashMap;
import java.util.Map;

public class Liberrio extends PearlJam{
    
    private Map<String, Double> menu;
    
    public Liberrio(String name){
        super(name);
        this.menu = new HashMap<>();
        addToMenu("Formaggio", 12.5);
        addToMenu("Ghiaccio", 1.01);
        addToMenu("Melone", 5.2);
        addToMenu("Prosciutto and Pesci", 20.23);
        addToMenu("Risotto", 13.14);
        addToMenu("Zucchero and Sale", 0.6);
    }
    
    public void addToMenu(String itemName, double price){
        menu.put(itemName, price);
    }
    
    public double getPrice(String itemName){
        return menu.getOrDefault(itemName, 0.0);
    }
}

