/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.HashMap;
import java.util.Map;

public class SavageGarden extends PearlJam{
    
    private Map<String, Double> menu;
    
    public SavageGarden(String name){
        super(name);
        this.menu = new HashMap<>();
        addToMenu("Abbacchio's Tea", 1);
        addToMenu("DIO's Bread", 36.14);
        addToMenu("Giorno's Donuts", 6.66);
        addToMenu("Joseph's Tequila", 35);
        addToMenu("Kakyoin's Cherry", 3.5);
        addToMenu("Kakyoin's Porridge", 4.44);
    }
    
    public void addToMenu(String itemName, double price){
        menu.put(itemName, price);
    }
    
    public double getPrice(String itemName){
        return menu.getOrDefault(itemName, 0.0);
    }
}
