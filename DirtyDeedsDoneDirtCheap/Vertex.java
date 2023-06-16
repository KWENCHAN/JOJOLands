package DirtyDeedsDoneDirtCheap;

class Vertex<T extends Comparable<T>, N extends Comparable<N>> {
    private final T vertexInfo;
    private final Edge<T, N> firstEdge;
    private int distance;


    public Vertex(T vertexInfo) {
        this.vertexInfo = vertexInfo;
        this.firstEdge = null;
        this.distance = Integer.MAX_VALUE;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setVisited() {
    }

    public T getVertexInfo() {
        return vertexInfo;
    }

    public Edge<T, N> getFirstEdge() {
        return firstEdge;
    }

    public boolean isVisited() {
        return false;
    }

    public N getShortestPath() {
        return null;
    }

    public void setShortestPath() {

    }
}