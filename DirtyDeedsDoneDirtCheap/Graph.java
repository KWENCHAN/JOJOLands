package DirtyDeedsDoneDirtCheap;

import java.util.List;

class Graph<T extends Comparable<T>, N extends Comparable<N>> {
    Vertex<T, N> head;
    int size;

    public Graph() {
        head = null;
        size = 0;
    }

    // For extra feature 4
    public void resetVertices() {
        Vertex<T, N> temp = head;
        while (temp != null) {
            temp.setDistance();
            temp.setVisited();
            temp.setShortestPaths();
            temp = temp.nextVertex;
        }
    }

    public boolean hasVertex(T v) {
        if (head == null)
            return true;
        Vertex<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0)
                return false;
            temp = temp.nextVertex;
        }
        return true;
    }

    public Vertex<T, N> getVertex(T v) {
        Vertex<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0) {
                return temp;
            }
            temp = temp.nextVertex;
        }
        return null;
    }

    public N getEdgeWeight(T source, T destination) {
        if (head == null)
            return null;
        if (hasVertex(source) || hasVertex(destination))
            return null;
        Vertex<T, N> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.equals(source)) {
                // Reached source vertex, look for destination now
                Edge<T, N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0)
                        // destination vertex found
                        return currentEdge.weight;
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return null;
    }

    public void resetEdgeWeight(T spurNode, T nextSpurNode, N maxValue) {
        Vertex<T, N> sourceVertex = getVertex(spurNode);
        if (sourceVertex != null) {
            Edge<T, N> currentEdge = sourceVertex.firstEdge;
            while (currentEdge != null) {
                if (currentEdge.toVertex.vertexInfo.compareTo(nextSpurNode) == 0) {
                    currentEdge.weight = maxValue;
                    break;
                }
                currentEdge = currentEdge.nextEdge;
            }
        }
    }

    public static class Vertex<T extends Comparable<T>, N extends Comparable<N>> {
        T vertexInfo;
        Edge<T, N> firstEdge;
        Vertex<T, N> nextVertex;

        public Vertex(T v) {
            vertexInfo = v;
            firstEdge = null;
            nextVertex = null;
        }

        public void setDistance() {
        }

        public void setVisited() {
        }

        public void setShortestPaths() {
        }

        public List<T> getShortestPath() {
            return null;
        }
    }

    public static class Edge<T extends Comparable<T>, N extends Comparable<N>> {
        Vertex<T, N> toVertex;
        Edge<T, N> nextEdge;
        N weight;

        public Edge(Vertex<T, N> v, N w) {
            toVertex = v;
            nextEdge = null;
            weight = w;
        }
    }
}
