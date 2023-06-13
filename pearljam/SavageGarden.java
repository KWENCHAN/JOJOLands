/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.List;

public class SavageGarden extends PearlJam {

    public SavageGarden() {
        super("Savage Garden");
        addToMenu("Abbacchio's Tea", 1.00);
        addToMenu("DIO's Bread", 36.14);
        addToMenu("Giorno's Donuts", 6.66);
        addToMenu("Joseph's Tequila", 35.00);
        addToMenu("Kakyoin's Cherry", 3.50);
        addToMenu("Kakyoin's Porridge", 4.44);
    }

    public void processOrdersSavageGarden(int dayNumber) {
        List<Customer> waitinglist_copy = new ArrayList<>(waitingList);
        int count = 1;
        while (!waitinglist_copy.isEmpty()) {
            List<Customer> temp = new ArrayList<>();
            for (int i = 0; i < waitinglist_copy.size(); i++) {
                if (count == dayNumber) {
                    Customer matched = waitinglist_copy.get(i);
                    orderProcessingList.add(matched);
                    temp.add(matched);
                    count = 0;
                }
                count++;
            }
            for (Customer removed : temp) {
                waitinglist_copy.remove(removed);
            }
            temp.clear();

            for (int i = waitinglist_copy.size() - 1; i >= 0; i--) {
                if (count == dayNumber) {
                    orderProcessingList.add(waitinglist_copy.get(i));
                    temp.add(waitinglist_copy.get(i));
                    count = 0;
                }
                count++;
            }
            for (Customer removed : temp) {
                waitinglist_copy.remove(removed);
            }
            temp.clear();
        }
    }
}
