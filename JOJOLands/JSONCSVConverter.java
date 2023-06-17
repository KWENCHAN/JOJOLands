/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JOJOLands;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 *
 * @author chank
 */
public class JSONCSVConverter {

    public static String CSVtoJSON(String csvpath) {
            InputStream inputStream = JSONCSVConverter.class.getResourceAsStream(csvpath);
            String csvAsString = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
            return csvAsString;
    }
    public static void main(String[] args) {
        String csvjson=CSVtoJSON("C:\\Users\\chank\\OneDrive\\Documents\\UM\\SEM 2\\WIA1002 DATA STRUCTURE\\TestJojo\\src\\pearljam\\orderList.csv");
        System.out.println(csvjson);
    }
}
