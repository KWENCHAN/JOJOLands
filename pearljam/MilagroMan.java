/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author chank
 */
public class MilagroMan {

    private static Scanner scanner = new Scanner(System.in);
    private static String csv = "C:\\Users\\chank\\Documents\\UM\\SEM 2\\WIA1002 DATA STRUCTURE\\TestJojo\\src\\pearljam\\MilagroMan.csv";

    public static void action(PearlJam restaurant) {
        copyCSVFile(
                "C:\\Users\\chank\\Documents\\UM\\SEM 2\\WIA1002 DATA STRUCTURE\\TestJojo\\src\\pearljam\\orderList.csv",
                csv);
        String option;
        do {
            printMenu(restaurant);
            option = scanner.nextLine();
            if (option == "" || option.matches("\\s+")) {
                System.out.println("Invalid input. Please reselect.");
                continue;
            }
            switch (option) {
                case "1":
                    modifyFoodPrices(restaurant);
                    break;
                case "2":
                    MoodyBlues.action(restaurant,
                            "C:\\Users\\chank\\OneDrive\\Documents\\UM\\SEM 2\\WIA1002 DATA STRUCTURE\\TestJojo\\src\\pearljam\\MilagroMan.csv",
                            true);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Option " + option + " is not available. Please try again.");
            }
        } while (!option.equals("3"));
    }

    private static void printMenu(PearlJam restaurant) {
        System.out.println("Restaurant: "+restaurant.getName()+" (Milagro Man Mode)");
        System.out.println("[1] Modify Food Prices");
        System.out.println("[2] View Sales Information");
        System.out.println("[3] Exit Milagro Man\n");
        System.out.print("Select: ");
    }

    public static void copyCSVFile(String sourceFilePath, String destinationFilePath) {
        try {
            // Read the source file
            BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
            StringBuilder content = new StringBuilder();
            String line;

            // Read each line and append it to the content
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            // Write the content to the destination file
            BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath));
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Fail to copy the file");
        }
    }

    private static void modifyFoodPrices(PearlJam restaurant) {
        try {
            String foodName;
            do {
                System.out.print("Enter food name: ");
                foodName = scanner.nextLine();
                if (!haveFood(foodName, csv)) {
                    System.out.println("Food not found.");
                }
            } while (!haveFood(foodName, csv));

            double newPrice;
            int startday;
            int endday;

            while (true) {
                try {
                    System.out.print("Enter new price: $");
                    newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter Start Day: ");
                    startday = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter End Day: ");
                    endday = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please try again. ");
                    scanner.nextLine();
                }
            }

            // Read the original file
            BufferedReader reader = new BufferedReader(new FileReader(csv));
            String line;
            StringBuilder content = new StringBuilder();

            // Read each line and append it to the content, replacing the line at the
            // specified index
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = startday; i <= endday; i++) {
                    if (values[1].equals(Integer.toString(i)) && values[2].equals(foodName)) {
                        values[4] = Double.toString(newPrice);
                    }
                }
                line = String.join(",", values);
                content.append(line).append("\n");
            }
            reader.close();

            // Write the modified content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(csv));
            writer.write(content.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean haveFood(String searchString, String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchString)) {
                    reader.close();
                    return true; // String found in the CSV file
                }
            }

            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File was not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // String not found in the CSV file
    }

}
