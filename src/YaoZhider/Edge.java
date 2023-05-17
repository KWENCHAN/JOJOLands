
package YaoZhider;

public class Edge {
    
    Vertex toVertex;
    int weight;
    Edge nextEdge;
    
    public Edge(Vertex destination, int weight, Edge nextEdge){
        
        toVertex = destination;
        this.weight = weight;
        this.nextEdge = nextEdge;
    }
}
