package DirtyDeedsDoneDirtCheap;

class Edge<T extends Comparable<T>, N extends Comparable<N>> {
    private final Vertex<T, N> toVertex;
    private final N weight;
    private final Edge<T, N> nextEdge;

    public Edge(Vertex<T, N> destination, N weight, Edge<T, N> nextEdge) {
        this.toVertex = destination;
        this.weight = weight;
        this.nextEdge = nextEdge;
    }

    public N getWeight() {
        return weight;
    }

    public Vertex<T, N> getToVertex() {
        return toVertex;
    }

    public Edge<T, N> getNextEdge() {
        return nextEdge;
    }

}

