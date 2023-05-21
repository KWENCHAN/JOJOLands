/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JadeGarden extends PearlJam{
    
    private Map<String, Double> menu;
    
    public JadeGarden(String name){
        super(name);
        this.menu = new HashMap<>();
        addToMenu("Braised Chicken in Black Bean Sauce", 15);
        addToMenu("Braised Goose Web with Vermicelli", 21);
        addToMenu("Deep-fried Hiroshima Oysters", 17);
        addToMenu("Poached Tofu with Dried Shrimps", 12);
        addToMenu("Scrambled Egg White with Milk", 10);
    }
    
    public void addToMenu(String itemName, double price){
        menu.put(itemName, price);
    }
    
    public double getPrice(String itemName){
        return menu.getOrDefault(itemName, 0.0);
    }
    
    public static List<Customer> sortOrder(List<Customer> waitingList){
        List<Customer> sortedList = new ArrayList<>();
        int left = 0;
        int right = waitingList.size()-1;
        while(left<=right){
            sortedList.add(waitingList.get(left));
            if(left==right){
                break;
            }
            left+=1;
            
            sortedList.add(waitingList.get(right));
            if(left==right){
                break;
            }
            right-=1;
        }
            return sortedList;
    }
    
    
}
