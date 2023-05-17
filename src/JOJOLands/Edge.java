package JOJOLands;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author ASUS
 */
public class Edge {
    Location destination;
    int weight;

    public Edge(Location tovertex, int weight) {
        this.destination = tovertex;
        this.weight = weight;
    }

    public Location getTovertex() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
    
    
}
