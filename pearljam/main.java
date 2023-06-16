/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import ResidentsData.Resident;

public class main {

    public static void main(String[] args) {
        TrattoriaTrussardi sg = new TrattoriaTrussardi();
        //sg.addResidentToWaitingList(new Resident("Jonathan", 34, "Male", "eateat"));
        //sg.addResidentToWaitingList(new Resident("Jonas", 32, "Male", "dunno"));
        //sg.addResidentToWaitingList(new Resident("Jeong", 23, "Female", "hungry"));
        //sg.addResidentToWaitingList(new Resident("Jaey", 54, "Female", "wat"));
        //sg.addResidentToWaitingList(new Resident("John", 4, "Male", "nothing"));
        //sg.addResidentToWaitingList(new Resident("Joseph", 105, "Male", "mapley"));
        //sg.addResidentToWaitingList(new Resident("June", 35, "Female", "israh cafe"));

        sg.displayWaitingList();
        sg.processOrdersTrattoriaTrussardi();
        sg.displayOrderProcessingList();

    }
}
