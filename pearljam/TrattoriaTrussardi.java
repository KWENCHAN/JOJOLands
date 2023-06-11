/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrattoriaTrussardi extends PearlJam {

    public TrattoriaTrussardi() {
        super("Trattoria Trussardi");
        addToMenu("Caprese Salad", 10);
        addToMenu("Creme caramel", 6.5);
        addToMenu("Lamb Chops with Apple Sauce", 25);
        addToMenu("Spaghetti alla Puttanesca", 15);
    }

    public void processOrdersTrattoriaTrussardi() {
        List<Customer> males = new ArrayList<>();
        List<Customer> females = new ArrayList<>();
        List<Customer> unspecified = new ArrayList<>();

        for (Customer customer : waitingList) {
            String gender = customer.getGender();
            if (gender.equals("Male")) {
                males.add(customer);
            } else if (gender.equals("Female")) {
                females.add(customer);
            } else {
                unspecified.add(customer);
            }
        }

        males.sort(Comparator.comparingInt(Customer::getAge));
        females.sort(Comparator.comparingInt(Customer::getAge).reversed());

        while (!males.isEmpty() && !females.isEmpty()) {
            orderProcessingList.add(males.remove(0));
            orderProcessingList.add(females.remove(0));
            if (males.isEmpty() || females.isEmpty()) {
                break;
            }
            orderProcessingList.add(males.remove(males.size() - 1));
            orderProcessingList.add(females.remove(females.size() - 1));
        }

        orderProcessingList.addAll(males);
        orderProcessingList.addAll(females);
        orderProcessingList.addAll(unspecified);
    }
}
