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
<<<<<<< HEAD
    
=======

    // extra feature 4

    public Graph.Edge getNextEdge() {
        return null;
    }

    static class Edge1<T extends Comparable<T>, N extends Comparable<N>> {
        private final Graph.Vertex1<T, N> toVertex;
        private final N weight;
        private final Edge1<T, N> nextEdge;

        public Edge1(Graph.Vertex1<T, N> destination, N weight, Edge1<T, N> nextEdge) {
            this.toVertex = destination;
            this.weight = weight;
            this.nextEdge = nextEdge;
        }

        public N getWeight() {
            return weight;
        }

        public Graph.Vertex1<T, N> getToVertex() {
            return toVertex;
        }

        public Edge1<T, N> getNextEdge() {
            return nextEdge;
        }

    }
>>>>>>> 6c7ecb5844fd95091ac606d539f48392992a5936
}
