/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JOJOLands;

import Graph.Graph;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yaozh
 */
public class AnotherOneBitesTheDust {

    private Graph map;

    public AnotherOneBitesTheDust(Graph map) {
        this.map = map;
    }

    public String getPath() {
        Scanner sc = new Scanner(System.in);
        String path;
        while (true) {
            System.out.println("Sample input: Libeccio > Vineyard > Joestar Mansion > Vineyard");
            System.out.print("Enter Yoshikage Kira's path (Location > Location): ");
            path = sc.nextLine();
            if(path.matches("(.+) > (.+)")){
                break;
            }
            System.out.println("Wrong input format. Please try again. ");
        }
        return path;
    }

    public void hasBitesTheDust(String path) {

        ArrayList<String> result = new ArrayList<>();
        String[] pathArr = path.split(">");

        String temp = pathArr[0].trim();
        int numLocation = 1;

        for (int i = 0; i < pathArr.length - 1; i++) {

            temp = pathArr[i].trim();
            numLocation = 1;
            for (int j = i; j < pathArr.length - 1; j++) {

                String currentLocation = pathArr[j].trim();
                String nextLocation = pathArr[j + 1].trim();

                if (isConsecutive(currentLocation, nextLocation) && !temp.contains(nextLocation)) {
                    temp += " > " + nextLocation;
                    numLocation++;
                } else { // Consecutive location ended
                    if (numLocation > 1) {
                        result.add(temp);
                    }
                    temp = nextLocation;
                    numLocation = 1;
                    break;
                }
            }

        }

        if (numLocation > 1) {
            result.add(temp);
        }

        result.sort((a, b) -> a.length() - b.length());
        boolean hasBitesTheDust = false;
        System.out.println("=".repeat(70));

        for (int i = result.size() - 1; i >= 0; i--) {

            if (hasRepeatedPath(result.get(i), result, i)) {
                System.out.println("Bites the Dust is most likely to be activated when Kira passed through");
                System.out.println(result.get(i) + ".");
                hasBitesTheDust = true;
                break;
            }
        }

        if (!hasBitesTheDust) {
            System.out.println("Bites the Dust is not activated.");
        }
        System.out.println("=".repeat(70));
    }

    public boolean isConsecutive(String source, String destination) {
        return map.hasEdge(source, destination);
    }

    private boolean hasRepeatedPath(String path, ArrayList<String> allPath, int currentIndex) {

        for (int i = 0; i < allPath.size(); i++) {

            if (i == currentIndex) {
                continue;
            }
            if (allPath.get(i).contains(path)) {
                return true;
            }
        }
        return false;
    }
}
