/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.List;

public class JadeGarden extends PearlJam{
    
    public JadeGarden(){
        super("Jade Garden");
        addToMenu("Braised Chicken in Black Bean Sauce", 15.00);
        addToMenu("Braised Goose Web with Vermicelli", 21.00);
        addToMenu("Deep-fried Hiroshima Oysters", 17.00);
        addToMenu("Poached Tofu with Dried Shrimps", 12.00);
        addToMenu("Scrambled Egg White with Milk", 10.00);
    }
    
    public List<Customer> sortOrder(List<Customer> waitingList){
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
    
    public void processOrdersJadeGarden() {
        int start = 0;
        int end = waitingList.size() - 1;

        while (start < end) {
            Customer firstCustomer = waitingList.get(start);
            Customer lastCustomer = waitingList.get(end);

            orderProcessingList.add(firstCustomer);
            orderProcessingList.add(lastCustomer);

            start++;
            end--;
        }

        if (start == end) {
            orderProcessingList.add(waitingList.get(start));
        }
    }
}
