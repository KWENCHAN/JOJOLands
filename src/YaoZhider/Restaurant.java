
package YaoZhider;

import java.util.ArrayList;

public class Restaurant {
    
    public void printMenu(){
        System.out.println("This is the menu");
    }
    
    public ArrayList<Integer> waitingList(){        // Can add parameter to choose where is the place to update waiting list
        ArrayList<Integer> waiting = new ArrayList<>();
        waiting.add(2);
        waiting.add(2);
        waiting.add(2);
        return waiting;
    }
}
