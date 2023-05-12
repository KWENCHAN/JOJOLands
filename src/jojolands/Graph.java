/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author ASUS
 */
public class Graph<T> {

    private HashMap<Vertex<T>, ArrayList<Edge<T>>> map;
    Vertex<String> current = map.;
    Stack<Vertex<String>> history = new Stack<>();

    public Graph() {
        this.map = new HashMap<>();
    }

    public boolean addVertex(Vertex<T> v) {
        if (!map.containsKey(v)) {
            map.put(v, new ArrayList<>());
            return true;
        }
        return false;
    }

    public boolean removeVertex(Vertex<T> v) {
        if (map.containsKey(v)) {
            map.remove(v);
            return true;
        }
        return false;
    }

    public boolean addEdge(Vertex<T> source, Vertex<T> destination, int weight) {
        if (!map.containsKey(source)) {
            addVertex(source);
        }
        if (!map.containsKey(destination)) {
            addVertex(destination);
        }
        map.get(source).add(new Edge<>(destination, weight));
        map.get(destination).add(new Edge<>(source, weight));
        return true;
    }

    public boolean removeEdge(Vertex<T> source, Vertex<T> destination) {
        if (map.containsKey(source) && map.containsKey(destination)) {
            map.get(source).remove(destination);
            map.get(destination).remove(source);
            return true;
        }
        return false;
    }

    public void printGraph() {
        for (Vertex<T> v : map.keySet()) {
            System.out.println(v.getVertexinfo());
        }
        for (Vertex<T> v : map.keySet()) {
            System.out.print(v.getVertexinfo() + " -->");
            for (Edge<T> e : map.get(v)) {
                System.out.println(e.getTovertex().vertexinfo + " (" + e.getWeight() + ")");
            }
        }
    }

    public void move(Vertex<T> v) {

    }
}
