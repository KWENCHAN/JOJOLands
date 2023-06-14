package DirtyDeedsDoneDirtCheap;

class Edge<T extends Comparable<T>, N extends Comparable <N>> {

    Vertex<T, N> toVertex;
    Vertex<T, N> fromVertex;
    N weight;
    Edge<T, N> nextEdge;

    public Edge(Vertex<T, N> source, Vertex<T, N> destination, N w, Edge<T, N> a) {
        fromVertex = source;
        toVertex = destination;
        weight = w;
        nextEdge = a;
    }

    public N getWeight() {
        return weight;
    }

    public void setWeight(N weight) {
        this.weight = weight;
    }
}
