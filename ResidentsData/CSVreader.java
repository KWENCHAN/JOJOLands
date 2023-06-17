/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResidentsData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class CSVreader {

    public static ArrayList<Resident> readResident(String location) {
        try {
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\chank\\Documents\\UM\\SEM 2\\WIA1002 DATA STRUCTURE\\JOJOLandsMaster\\ResidentsData\\residents.csv"));
            ArrayList<Resident> residentList = new ArrayList<>();
            while (in.hasNextLine()) {
                String next = in.nextLine();
                if (next.contains(location)) {
                    String[] content = next.split(",", 5);
                    ArrayList<String> parent=new ArrayList<>(Arrays.asList(content[4].split(",")));
                    residentList.add(new Resident(content[0], content[1], content[2], parent, readStand(content[0])));
                }
            }
            in.close();
            return residentList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Stand readStand(String name) {
        try {
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\chank\\Documents\\UM\\SEM 2\\WIA1002 DATA STRUCTURE\\JOJOLandsMaster\\ResidentsData\\stands.csv"));
            while (in.hasNextLine()) {
                String next = in.nextLine();
                if (next.contains(name)) {
                    String[] content=next.split(",");
                    return new Stand(content[0],content[2],content[3],content[4],content[5],content[6],content[7]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
