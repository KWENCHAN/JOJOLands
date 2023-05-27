package Graph;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author ASUS
 */
public class Edge implements Comparable<Edge>{
    Location destination;
    int weight;
    Location source;

    public Edge(Location tovertex, int weight) {
        this.destination = tovertex;
        this.weight = weight;
    }
    
    public Edge(Location tovertex, Location source, int weight) {
        this.destination = tovertex;
        this.weight = weight;
        this.source = source;
    }

    public Location getSource() {
        return source;
    }
    
    public Location getTovertex() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(weight, other.weight);
    }
    
    public String toString(){
        return source.getName() + " -> " + destination.getName() + " " + weight;
    }
    
}
