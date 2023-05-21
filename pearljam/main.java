/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[]args){
        List<Customer> waitingList = new ArrayList<>();
        System.out.println("+----+--------------------+-----+--------+-----------------------------------------------------------+");
        System.out.println("| No | Name               | Age | Gender | Order                                                     |");
        System.out.println("+----+--------------------+-----+--------+-----------------------------------------------------------+");
        waitingList.add(new Customer("Jonathan",34,"Male","eateat"));
        waitingList.add(new Customer("Jonas",32,"Male","dunno"));
        waitingList.add(new Customer("Jeong",23,"Female","hungry"));
        waitingList.add(new Customer("Jaey",54,"Male","wat"));
        for(int i=0;i<waitingList.size();i++){
            System.out.println(waitingList.get(i));
        }
        
        List<Customer> sortedCustomers = JadeGarden.sortOrder(waitingList);
        System.out.println("Sorted customers: " + sortedCustomers);
    }
}
