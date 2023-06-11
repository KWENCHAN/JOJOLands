/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

public class main {

    public static void main(String[] args) {
        TrattoriaTrussardi sg = new TrattoriaTrussardi();
        sg.addCustomerToWaitingList(new Customer("Jonathan", 34, "Male", "eateat"));
        sg.addCustomerToWaitingList(new Customer("Jonas", 32, "Male", "dunno"));
        sg.addCustomerToWaitingList(new Customer("Jeong", 23, "Female", "hungry"));
        sg.addCustomerToWaitingList(new Customer("Jaey", 54, "Female", "wat"));
        sg.addCustomerToWaitingList(new Customer("John", 4, "Male", "nothing"));
        sg.addCustomerToWaitingList(new Customer("Joseph", 105, "Male", "mapley"));
        sg.addCustomerToWaitingList(new Customer("June", 35, "Female", "israh cafe"));

        sg.displayWaitingList();
        sg.processOrdersTrattoriaTrussardi();
        sg.displayOrderProcessingList();

    }
}
