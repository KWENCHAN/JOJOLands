
package JOJOLands;

import Graph.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            String edgeKey2 = e.getTovertex().getName() + "-" + e.getSource().getName();
            if(!edgeInHashMap.containsKey(edgeKey) && !edgeInHashMap.containsKey(edgeKey2))
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
        int counter = 0;
        Edge[]edgeListTownHall = {edgeList.get(0), edgeList.get(1), edgeList.get(2)};
        
        for (int i = 0; i < 3; i++) {
            edgeList.remove();
        }

        Collections.sort(edgeList, Collections.reverseOrder());
        
        for (int i = 0; i < 3; i++) {
            edgeList.addFirst(edgeListTownHall[i]);
        }
        
//        for (int i = 0; i < edgeList.size(); i++) {
//            System.out.println(edgeList.get(i).getSource().getName());
//            
//        }
        
        Map<String, String> parent = preprocessEdges(edgeList);
        List<Edge> remainingEdges = new ArrayList<>();

        for (Edge e : edgeList) {
            parent.put(e.getSource().getName(), e.getSource().getName());
            parent.put(e.getTovertex().getName(), e.getTovertex().getName());
        }

        for (Edge e : edgeList) {
            String rootU = findRoot(parent, e.getSource().getName());
            String rootV = findRoot(parent, e.getTovertex().getName());

            if (!rootU.equals(rootV)) {
                cost += e.getWeight();
                mergeTrees(parent, rootU, rootV);
            } else {
                if(!remainingEdges.contains(e)){
                    remainingEdges.add(e);
                    
                }
            }
        }
        
        remainingEdges = sortResult(remainingEdges);
        
        System.out.println("Remaining edges (non-maximum):");
        for (int i = 0; i < remainingEdges.size(); i++) {  
            System.out.printf("%2d. %s --- %s (%d km)\n", i + 1, remainingEdges.get(i).getSource().getName(), remainingEdges.get(i).getTovertex().getName(), remainingEdges.get(i).getWeight());
        }
        
        System.out.println("\nTotal length: " + cost + " km"+counter);
    }

    
    
}
