
package JOJOLands;

import Graph.Edge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MinimumSpanningTree {
    
    public String findRoot(Map<String, String> parent, String vertex) {
        if (parent.get(vertex).equals(vertex))
            return vertex;
        return findRoot(parent, parent.get(vertex));
    }

    public void mergeTrees(Map<String, String> parent, String oldRoot, String newRoot) {
        for (String key : parent.keySet()) {
            if (parent.get(key).equals(oldRoot))
                parent.put(key, newRoot);
        }
    }

    public Map<String, String> preprocessEdges(LinkedList<Edge> edgeList) {
        
        Map<String, String> edgeInHashMap = new HashMap<>();

        for (Edge e : edgeList) {
            String edgeKey = e.getSource().getName() + "-" + e.getTovertex().getName();
            edgeInHashMap.put(edgeKey, edgeKey);
        }
        
        return edgeInHashMap;
    }
    
    public List<Edge> sortResult(List<Edge> result){
        
        result.sort((a, b) -> {
            String sourceA = a.getSource().getName();
            String sourceB = b.getSource().getName();
            int sourceComparison = sourceA.compareTo(sourceB);

            if (sourceComparison != 0) {
                return sourceComparison;
            }

            String destinationA = a.getTovertex().getName();
            String destinationB = b.getTovertex().getName();
            return destinationA.compareTo(destinationB);
        });
        
        return result;
    }
    
    public void calculateCost(LinkedList<Edge> edgeList) {
        
        int cost = 0;

        Collections.sort(edgeList);
        Map<String, String> parent = preprocessEdges(edgeList);
        List<Edge> result = new ArrayList<>();
         
        for (Edge e : edgeList) {
            parent.put(e.getSource().getName(), e.getSource().getName());
            parent.put(e.getTovertex().getName(), e.getTovertex().getName());
        }

        for (Edge e : edgeList) {
            String rootU = findRoot(parent, e.getSource().getName());
            String rootV = findRoot(parent, e.getTovertex().getName());

            if (!rootU.equals(rootV)) {
                cost += e.getWeight();
                result.add(e);
                mergeTrees(parent, rootU, rootV);
            }
        }
        
        result = sortResult(result);
        
        System.out.println("Necessary Power Cables to be Upgraded:");

        for (int i = 0; i < result.size(); i++) {  
            System.out.printf("%2d. %s --- %s (%d km)\n", i + 1, result.get(i).getSource().getName(), result.get(i).getTovertex().getName(), result.get(i).getWeight());
        }
        
        System.out.println("\nTotal length: " + cost + " km");
    }

    public void calculateMaxPath(LinkedList<Edge> edgeList) {
        
        int cost = 0;
        Map<String, String> parent = new HashMap<>();
        List<Edge> result = new ArrayList<>();

        // Find the edge connected to "Town Hall"
        Edge townHallEdge = null;

        for (Edge e : edgeList) {
            parent.put(e.getSource().getName(), e.getSource().getName());
            parent.put(e.getTovertex().getName(), e.getTovertex().getName());

            if (e.getSource().getName().equals("Town Hall") || e.getTovertex().getName().equals("Town Hall")) {
                townHallEdge = e;
            }
        }

        // Add the edge connected to "Town Hall" as the first edge in the result list
        if (townHallEdge != null) {
            cost += townHallEdge.getWeight();
            result.add(townHallEdge);
            mergeTrees(parent, townHallEdge.getSource().getName(), townHallEdge.getTovertex().getName());
        }

        // Process the remaining edges in descending order of cost
        Collections.sort(edgeList, Collections.reverseOrder());

        for (Edge e : edgeList) {
            if (e == townHallEdge) {
                continue; // Skip the edge connected to "Town Hall"
            }

            String rootU = findRoot(parent, e.getSource().getName());
            String rootV = findRoot(parent, e.getTovertex().getName());

            if (!rootU.equals(rootV)) {
                cost += e.getWeight();
                result.add(e);
                mergeTrees(parent, rootU, rootV);
            }
        }

        result = sortResult(result);
        System.out.println("Necessary Power Cables to be Upgraded:");

        for (int i = 0; i < result.size(); i++) {  
            System.out.printf("%2d. %s --- %s (%d km)\n", i + 1, result.get(i).getSource().getName(), result.get(i).getTovertex().getName(), result.get(i).getWeight());
        }

        System.out.println("\nTotal length: " + cost + " km");
    }
}
