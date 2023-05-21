/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.List;


public class PearlJam {

    private String name;
    protected List<Customer> waitingList;
    
    public PearlJam(String name){
        this.name = name;
        this.waitingList = new ArrayList<>();
    }
    
    public String getName(){
        return name;
}
    
    public void setName(String name){
        this.name = name;
    }   
    
    public void addCustomerToWaitingList(Customer customer){
        waitingList.add(customer);
    }
    
    public String displayWaitingList(){
        return "";
    }
    
    public String displayOrderProcessingList(){
        return "";
    }
    
}
