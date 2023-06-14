package DirtyDeedsDoneDirtCheap;

import java.util.ArrayList;
import java.util.List;

class Vertex<T extends Comparable<T>, N extends Comparable <N>> {
    T vertexInfo;
    int deg;
    Vertex<T,N> nextVertex;
    Edge<T,N> firstEdge;
    boolean visited;
    int distance;   //distance to source
    List<String> shortestPath; //a list of vertices that forms the shortest path (Dijkstra algorithm)

    public Vertex(T vInfo, Vertex<T,N> next){
        vertexInfo = vInfo;
        deg = 0;
        nextVertex = next;
        firstEdge = null;
        visited = false;
        distance = Integer.MAX_VALUE;
        shortestPath = new ArrayList<>();
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<String> getShortestPaths() {
        return shortestPath;
    }

    public void setShortestPaths(List<String> path) {
        this.shortestPath = path;
    }

    //check if the vertex has been visited previously
    public boolean hasVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
