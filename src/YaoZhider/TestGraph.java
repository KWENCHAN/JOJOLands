
package YaoZhider;


public class TestGraph{
    
    public static void main(String[] args) {
        
        Graph defaultMap = new Graph();
        //defaultMap.createGraph();
        System.out.println(defaultMap.currentLocation.cityName);
//        //String[] cities = {"TownHall", "CafeDeuxMagots", "JadeGarden", "MoriohGrandHotel", "SanGiorgioMaggiore", "TrattoriaTrussardi"};
//        Vertex[] cities = {new Vertex("TownHall", null), new Vertex("CafeDeuxMagots", null), new Vertex("JadeGarden", null), new Vertex("MoriohGrandHotel", null)};
//        for (Vertex i : cities)
//            defaultMap.addVertex(i);
//        
//        
//        System.out.println("The number of vertices in defaultMap: " + defaultMap.getSize());
//        
//        System.out.println("add edge TownHall - CafeDeuxMagots: " + defaultMap.addEdge("TownHall", "CafeDeuxMagots", 4) );
//        System.out.println("add edge TownHall - JadeGarden : " + defaultMap.addEdge("TownHall", "JadeGarden", 5) ); 
//        System.out.println("add edge TownHall - MoriohGrandHotel : " + defaultMap.addEdge("TownHall", "MoriohGrandHotel", 5) );
//        System.out.println("add edge CafeDeuxMagots - JadeGarden : " + defaultMap.addEdge("CafeDeuxMagots", "JadeGarden", 3) );
//        System.out.println();
        
        System.out.print("\nPrint Edges : " );
        defaultMap.printEdges();
        System.out.println(defaultMap.currentLocation.getCityName());
        
        System.out.println(defaultMap.getAllNeighbours("TownHall"));
        System.out.println(defaultMap.moveLocation("JadeGarden"));
        System.out.println(defaultMap.currentLocation.cityName);
        System.out.println(defaultMap.getAllNeighbours(defaultMap.currentLocation.cityName));
        
        
        System.out.println(defaultMap.hasVertex(defaultMap.currentLocation));
        
        defaultMap.printWaitingList();
        
        defaultMap.toNextDay();
        defaultMap.printWaitingList();
    }
    
}
