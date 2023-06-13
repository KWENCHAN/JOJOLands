/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Libeccio extends PearlJam {

    public Libeccio() {
        super("Libeccio");
        addToMenu("Formaggio", 12.50);
        addToMenu("Ghiaccio", 1.01);
        addToMenu("Melone", 5.20);
        addToMenu("Prosciutto and Pesci", 20.23);
        addToMenu("Risotto", 13.14);
        addToMenu("Zucchero and Sale", 0.60);
    }

    public void processOrdersLibeccio(int dayNumber) {
        List<Customer> waitinglist_copy = new ArrayList<>(waitingList);
        Stack<Customer> reverse = new Stack<>();
        int count = 1;
        while (!waitinglist_copy.isEmpty()) {
            List<Customer> temp = new ArrayList<>();
            for (int i = 0; i < waitinglist_copy.size(); i++) {
                if (count % dayNumber == 0) {
                    reverse.push(waitinglist_copy.get(i));
                    temp.add(waitinglist_copy.get(i));
                }
                count++;
            }
            for (Customer removed : temp) {
                waitinglist_copy.remove(removed);
            }
            temp.clear();
        }

        while (!reverse.isEmpty()) {
            orderProcessingList.add(reverse.pop());
        }
    }
}
