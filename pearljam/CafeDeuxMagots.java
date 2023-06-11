/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CafeDeuxMagots extends PearlJam{
    
    public CafeDeuxMagots(){
        super("Cafe Deux Magots");
        addToMenu("Sampling Matured Cheese Platter", 23);
        addToMenu("Spring Lobster Salad", 35);
        addToMenu("Spring Organic Omelette", 23);
        addToMenu("Truffle-flavoured Poultry Supreme", 34);
        addToMenu("White Asparagus", 26);
    }
    
    public void processOrdersCafeDeuxMagots() {
        List<Customer> sortedList = new ArrayList<>(waitingList);
        sortedList.sort(Comparator.comparingInt(Customer::getAge));
        
        while (!sortedList.isEmpty()) {            
            orderProcessingList.add(sortedList.remove(sortedList.size()-1));
            if (sortedList.isEmpty()) {
                break;
            }
            orderProcessingList.add(sortedList.remove(0));
        }
    }
}
