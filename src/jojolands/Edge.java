/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

/**
 *
 * @author ASUS
 */
public class Edge <T>{
    Vertex<T> tovertex;
    int weight;

    public Edge(Vertex<T> tovertex, int weight) {
        this.tovertex = tovertex;
        this.weight = weight;
    }

    public Vertex<T> getTovertex() {
        return tovertex;
    }

    public int getWeight() {
        return weight;
    }
    
    
}
