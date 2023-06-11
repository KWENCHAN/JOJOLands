/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResidentsData;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class test {
    public static void main(String[] args) {
        ArrayList<Resident> temp=CSVreader.readResident("Vineyard");
        HeavenDoor vine=new HeavenDoor("Vineyard");
        vine.viewResidentInfo();
        vine.viewResidentProfile();
        vine.sort();
    }
}
