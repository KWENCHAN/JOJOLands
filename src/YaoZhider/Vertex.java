
package YaoZhider;

import java.util.ArrayList;

public class Vertex {
    
    String cityName;
    int deg;
    Vertex nextVertex;
    Edge firstEdge;
    ArrayList<ArrayList<Integer>> waitingList = new ArrayList<>(); 
    
    public Vertex(String cityName, Vertex next){
        
        this.cityName = cityName;
        deg = 0;
        nextVertex = next;
        firstEdge = null;
    }

    public String getCityName() {
        return cityName;
    }

    public ArrayList<ArrayList<Integer>> getWaitingList() {
        return waitingList;
    }
    
    
}
