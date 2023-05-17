package YaoZhider;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    
    Vertex head;
    int size;
    Vertex currentLocation;
    private static int day;
    Vertex[] cityArr;  // = {addVertex("TownHall"), addVertex("CafeDeuxMagots"), addVertex("JadeGarden"), addVertex("MoriohGrandHotel")};
    //ArrayList<ArrayList<Integer>> waitingList = new ArrayList<>();
    
    public Graph(){
        head = null;
        size = 0;
        // add parameter an integer to select map
        this.createGraph();
        this.toNextDay();    //Update day and waitinglist restaurant
    }
    
    public int getSize(){
        return size;
    }
    
    public boolean hasVertex(String city){
        
        if(head == null) return false;
        Vertex current = head;
        while(current != null){
            if(current.cityName.equalsIgnoreCase(city))
                return true;
            current = current.nextVertex;
        }
        return false;
    }
    
    public boolean hasVertex(Vertex city){
        
        if(head == null) return false;
        Vertex current = head;
        while(current != null){
            if(current.equals(city))
                return true;
            current = current.nextVertex;
        }
        return false;
    }
    
    public int getDeg(String city){
        
        if(hasVertex(city)){
            Vertex current = head;
            while(current != null){
                if(current.cityName.equalsIgnoreCase(city))
                    return current.deg;
                current = current.nextVertex;
            }
        }
        return -1;
    }
    
    public Vertex addVertex(String name){
        
        if(hasVertex(name)) return null;
        Vertex current = head;
        Vertex newVertex = new Vertex(name, null);
        
        if(head == null){
            head = newVertex;
            currentLocation = head;
        }
        
        else{
            Vertex previous = head;
            while(current != null){
                previous = current;     // preserve the previous point to link to the newVertex
                current = current.nextVertex;
            }
            previous.nextVertex = newVertex;
        }
        size ++;
        return newVertex;
    }
    
    public boolean addVertex(Vertex newVertex){
        
        if(hasVertex(newVertex.cityName)) return false;
        
        if(head == null){
            head = newVertex;
            currentLocation = head;
        }
        
        else{    
            
            Vertex current = head;
            Vertex previous = head;

            while(current != null){
                previous = current;
                current = current.nextVertex;
            }

            previous.nextVertex = newVertex;
            size++;
        }
        return true;
    }
    
    public int getIndex(String city){
        
        Vertex current = head;
        int index = 0;
        while(current != null){
            if(current.cityName.equalsIgnoreCase(city))
                return index;
            current = current.nextVertex;
            index++;
        }
        return -1;
    }
    
// Place for getAllVertexObjects() method if require
// Place for getVertex() method if require
// Place for hasEdge() method if require

    public boolean addEdge(String source, String destination, int weight){
        
        if (!hasVertex(source) || !hasVertex(destination))  return false;
        
        Vertex sourceVertex = head;
        while(sourceVertex != null){
            if(sourceVertex.cityName.equalsIgnoreCase(source)){
                
                Vertex destinationVertex = head;
                while(destinationVertex != null){
                    if(destinationVertex.cityName.equalsIgnoreCase(destination)){
                        
                        Edge currentEdgeSource = sourceVertex.firstEdge;
                        Edge newEdgeSource = new Edge(destinationVertex, weight, currentEdgeSource);
                        sourceVertex.firstEdge = newEdgeSource;
                        sourceVertex.deg++;
                        
                        Edge currentEdgeDestination = destinationVertex.firstEdge;
                        Edge newEdgeDestination = new Edge(sourceVertex, weight, currentEdgeDestination);
                        destinationVertex.firstEdge = newEdgeDestination;
                        destinationVertex.deg++;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }
    
    public boolean addEdge(Vertex source, Vertex destination, int weight){
        
        if (!hasVertex(source) || !hasVertex(destination))  return false;
        
        Vertex sourceVertex = head;
        while(sourceVertex != null){
            if(sourceVertex.equals(source)){
                
                Vertex destinationVertex = head;
                while(destinationVertex != null){
                    if(destinationVertex.equals(destination)){
                        
                        Edge currentEdgeSource = sourceVertex.firstEdge;
                        Edge newEdgeSource = new Edge(destinationVertex, weight, currentEdgeSource);
                        sourceVertex.firstEdge = newEdgeSource;
                        sourceVertex.deg++;
                        
                        Edge currentEdgeDestination = destinationVertex.firstEdge;
                        Edge newEdgeDestination = new Edge(sourceVertex, weight, currentEdgeDestination);
                        destinationVertex.firstEdge = newEdgeDestination;
                        destinationVertex.deg++;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }
    
// Place for getEdgeWeight() method if require
// Place for getNeighbours() method if require
    
    public void printEdges(){
        
        Vertex current = head;
        
        while(current != null){    // Vertex by vertex
            System.out.println("# " + current.cityName + " : ");
            Edge currentEdge = current.firstEdge;
            
            while (currentEdge != null){    // Edge by edge
                System.out.println("[" + current.cityName + "," + currentEdge.toVertex.cityName + "]");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println("");
            current = current.nextVertex;
        }
    }
    
    public String getAllNeighbours(String city){
        
        if(!hasVertex(city)) return null;
        
        String cityConnected = "";
        Vertex current = head;
        while(current != null){
            if(current.cityName.equalsIgnoreCase(city)){
                Edge currentEdge = current.firstEdge;
                while(currentEdge != null){
                    cityConnected += currentEdge.toVertex.cityName;
                    if(currentEdge.nextEdge != null)    cityConnected += ",";
                    currentEdge = currentEdge.nextEdge;
                }
            }
            current = current.nextVertex;
        }
        return cityConnected;
    }
    
// move current to nextVertices
    
    public boolean moveLocation(String cityName){
        
        Edge currentEdge = currentLocation.firstEdge;
        while(currentEdge != null){
            if(currentEdge.toVertex.cityName.equals(cityName)){
                currentLocation = currentEdge.toVertex;
                return true;
            }
            currentEdge = currentEdge.nextEdge;
        }
        return false;
    }
    
    public  void createGraph(){
        
        Vertex[] city = {addVertex("TownHall"), addVertex("CafeDeuxMagots"), addVertex("JadeGarden"), addVertex("MoriohGrandHotel")};
        this.cityArr = city;
        
        List<Integer> neighbors = new ArrayList<>();
// use loop, neighbours save index of cityName[] and weight
        addEdge(cityArr[0], cityArr[1], 4);
        addEdge(cityArr[0], cityArr[2], 5);
        addEdge(cityArr[0], cityArr[3], 5);
        addEdge(cityArr[1], cityArr[2], 3);
    }
    
    
    public void toNextDay(){
        day++;
        Vertex[] restaurantName = {cityArr[0], cityArr[1]};
        for (int i = 0; i < restaurantName.length; i++) {
            
            updateWaitingList(restaurantName[i]);
        }
        
    }
    
    public void ifRestaurant(){
        Restaurant res1 = new Restaurant();
        res1.printMenu();
    }
    
    public void updateWaitingList(Vertex rest){
        Restaurant res1 = new Restaurant();
        ArrayList<Integer> newWaitingList = res1.waitingList();         
        rest.waitingList.add(newWaitingList);
    }
    
    public void printWaitingList(){
        System.out.println(cityArr[0].cityName + "WaitingList: " + cityArr[0].waitingList);
    }
}
