package DirtyDeedsDoneDirtCheap;

import java.util.ArrayList;

class Graph<T extends Comparable<T>, N extends Comparable <N>> {
    Vertex<T,N> head;
    int size;

    public Graph(){
        head=null;
        size=0;
    }

    //for extra feature 4
    public void resetVertices() {
        Vertex<T, N> temp = head;
        while (temp != null) {
            temp.setDistance(Integer.MAX_VALUE);
            temp.setVisited(false);
            temp.setShortestPaths(new ArrayList<>());
            temp = temp.nextVertex;
        }
    }

    public boolean hasVertex(T v){
        if (head==null)
            return true;
        Vertex<T,N> temp = head;
        while (temp!=null){
            if ( temp.vertexInfo.compareTo( v ) == 0 )
                return false;
            temp=temp.nextVertex;
        }
        return true;
    }

    public Vertex<T, N> getVertex(T v){
        Vertex<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0) {
                return temp;
            }
            temp = temp.nextVertex;
        }
        return null;
    }

    public ArrayList<T> getAllVertexObjects(){
        ArrayList<T> list = new ArrayList<>();
        Vertex<T,N> temp = head;
        while (temp!=null){
            list.add(temp.vertexInfo);
            temp=temp.nextVertex;
        }
        return list;
    }

    public N getEdgeWeight(T source, T destination){
        N notFound=null;
        if (head==null)
            return notFound;
        if (hasVertex(source) || hasVertex(destination))
            return notFound;
        Vertex<T,N> sourceVertex = head;
        while (sourceVertex!=null){
            if (sourceVertex.vertexInfo.equals(source)){
                // Reached source vertex, look for destination now
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null){
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0)
                        // destination vertex found
                        return currentEdge.weight;
                    currentEdge=currentEdge.nextEdge;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return notFound;
    }

    public void resetEdgeWeight(String spurNode, String nextSpurNode, int maxValue) {

    }


    public static class Location {
    }
}