/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JOJOLands;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Location {

    private final String name;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void displayLocationOptions(Graph map) {

        ArrayList<Edge> edges = map.getEdge(this);

        if (edges.isEmpty()) {
            System.out.println("There are no available destinations from this location.");
            return;
        }

        char option = 'A';

        for (Edge edge : edges) {
            Location destination = edge.getTovertex();
            System.out.print("[" + option + "] " + destination.getName()+"\t");
            option++;
        }
    }
    
}
