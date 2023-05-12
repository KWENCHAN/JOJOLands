/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author ASUS
 */
public class HermitPurple {

    public static void main(String[] args) {
        // TODO code application logic here
        Graph<String> map = new Graph<>();
        String[] name = {"Town Hall", "Morioh Grand Hotel", "Trattoria Trussardi", "Green Dolphin Street Prison", "Libeccio", "San Giorgio Maggiore", "Jade Garden", "Cafe Deux Magots", "Polnareff Land", "Savage Garden", "Joestar Mansion", "Vineyard", "DIO's Mansion", "Angelo Rock"};
        ArrayList<Vertex<String>> location = new ArrayList<>();
        for (String string : name) {
            location.add(new Vertex<>(string));
        }

        map.addEdge(location.get(0), location.get(1), 5);
        map.addEdge(location.get(0), location.get(6), 5);
        map.addEdge(location.get(0), location.get(7), 4);
        map.addEdge(location.get(1), location.get(2), 6);
        map.addEdge(location.get(1), location.get(6), 3);
        map.addEdge(location.get(2), location.get(3), 6);
        map.addEdge(location.get(2), location.get(5), 3);
        map.addEdge(location.get(3), location.get(4), 3);
        map.addEdge(location.get(3), location.get(13), 2);
        map.addEdge(location.get(4), location.get(5), 4);
        map.addEdge(location.get(4), location.get(10), 6);
        map.addEdge(location.get(4), location.get(11), 6);
        map.addEdge(location.get(4), location.get(12), 2);
        map.addEdge(location.get(5), location.get(6), 2);
        map.addEdge(location.get(6), location.get(7), 3);
        map.addEdge(location.get(6), location.get(10), 2);
        map.addEdge(location.get(7), location.get(8), 4);
        map.addEdge(location.get(7), location.get(9), 4);
        map.addEdge(location.get(8), location.get(9), 6);
        map.addEdge(location.get(9), location.get(10), 4);
        map.addEdge(location.get(9), location.get(11), 8);
        map.addEdge(location.get(10), location.get(11), 3);
        map.addEdge(location.get(11), location.get(12), 3);
        map.addEdge(location.get(12), location.get(13), 3);

        int daynum = 1;
        System.out.printf("It's Day %d (%s) of our journey in JOJOLands!%n", daynum, getDayOfWeek(daynum));
        System.out.println("Current Location: " + current.vertexinfo);

        Vertex<String> current = location.get(0);
        Stack<Vertex<String>> history = new Stack<>();
    }

    private static String getDayOfWeek(int day) {
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int index = (day - 1) % 7;
        return daysOfWeek[index];
    }

    public void move(Vertex<T> v, Graph<T> map) {
        System.out.println("[1] Move to:");

    }
}
