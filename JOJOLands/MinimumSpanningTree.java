
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
    
    public List<Edge> sortResult(List<Edge> result) {
        
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
        Map<String, String> parent = new HashMap<>();
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
        List<Edge> resultToRemain = new ArrayList<>();
        Map<String, Edge> result = new HashMap<>();

        for (Edge e : edgeList) {
            parent.put(e.getSource().getName(), e.getSource().getName());
            parent.put(e.getTovertex().getName(), e.getTovertex().getName());
        }

        Collections.sort(edgeList, Collections.reverseOrder());

        for (Edge e : edgeList) {
            String rootU = findRoot(parent, e.getSource().getName());
            String rootV = findRoot(parent, e.getTovertex().getName());
            String key = e.getSource().getName() + " -- " + e.getTovertex().getName();

            if (!rootU.equals(rootV)) {
                mergeTrees(parent, rootU, rootV);
                resultToRemain.add(e);

            } else {
                result.put(key, e);;
            }
        }

        List<Edge> tryResult = new ArrayList<>(result.values());
        tryResult.retainAll(edgeList);

        for (int i = 0; i < resultToRemain.size(); i++) {
            String key = resultToRemain.get(i).getTovertex().getName() + " -- " + resultToRemain.get(i).getSource().getName();
            result.remove(key);
        }

        List<Edge> sortedResult = new ArrayList<>(result.values());
        System.out.println(sortedResult.size() + " " + result.size());
        
        
        for (int i = 0; i < sortedResult.size(); i++) {
            String keyForward = sortedResult.get(i).getSource().getName() + " -- " + sortedResult.get(i).getTovertex().getName();
            String keyBackward = sortedResult.get(i).getTovertex().getName() + " -- " + sortedResult.get(i).getSource().getName();
            if (result.containsKey(keyBackward)) {
                result.remove(keyForward);
            }
        }

        sortedResult = new ArrayList<>(result.values());
        sortResult(sortedResult);
        
        
        
        System.out.println("Unnecessary Water Connections to be Removed:");
        for (int i = 0; i < sortedResult.size(); i++) {
            Edge edge = sortedResult.get(i);
            cost += edge.getWeight();
            String key = edge.getSource().getName() + " -- " + edge.getTovertex().getName();
            System.out.printf("%2d. %s (%d km)\n", i + 1, key, edge.getWeight());
        }

    System.out.println("\nTotal length: " + cost + " km");
}

    
//    public void calculateMaxPath(LinkedList<Edge> edgeList) {
//        
//        int cost = 0;
//        Map<String, String> parent = new HashMap<>();
//        List<Edge> result = new ArrayList<>();
//
//        for (Edge e : edgeList) {
//            parent.put(e.getSource().getName(), e.getSource().getName());
//            parent.put(e.getTovertex().getName(), e.getTovertex().getName());
//
//        }
//
//        Collections.sort(edgeList, Collections.reverseOrder());
//
//        for (Edge e : edgeList) {
//            String rootU = findRoot(parent, e.getSource().getName());
//            String rootV = findRoot(parent, e.getTovertex().getName());
//
//            if (!rootU.equals(rootV)) {
//                cost += e.getWeight();
//                result.add(e);
//                mergeTrees(parent, rootU, rootV);
//            }
//        }
//        
//        System.out.println(result);
//        result = sortResult(result);
//        System.out.println("Necessary Power Cables to be Upgraded:");
//
//        for (int i = 0; i < result.size(); i++) {
//            System.out.printf("%2d. %s --- %s (%d km)\n", i + 1, result.get(i).getSource().getName(), result.get(i).getTovertex().getName(), result.get(i).getWeight());
//        }
//
//        System.out.println("\nTotal length: " + cost + " km");
//    }
    
//    public void calculateMaxPath(LinkedList<Edge> edgeList) {
//        
//        int cost = 0;
//        Map<String, String> parent = new HashMap<>();
//        List<Edge> result = new ArrayList<>();
//
//        // Find the edge connected to "Town Hall"
//        Edge townHallEdge = null;
//
//        for (Edge e : edgeList) {
//            parent.put(e.getSource().getName(), e.getSource().getName());
//            parent.put(e.getTovertex().getName(), e.getTovertex().getName());
//
//            if (e.getSource().getName().equals("Town Hall") || e.getTovertex().getName().equals("Town Hall")) {
//                townHallEdge = e;
//            }
//        }
//
//        // Add the edge connected to "Town Hall" as the first edge in the result list
//        if (townHallEdge != null) {
//            cost += townHallEdge.getWeight();
//            result.add(townHallEdge);
//            mergeTrees(parent, townHallEdge.getSource().getName(), townHallEdge.getTovertex().getName());
//        }
//
//        // Process the remaining edges in descending order of cost
//        Collections.sort(edgeList, Collections.reverseOrder());
//
//        for (Edge e : edgeList) {
//            if (e == townHallEdge) {
//                continue; // Skip the edge connected to "Town Hall"
//            }
//
//            String rootU = findRoot(parent, e.getSource().getName());
//            String rootV = findRoot(parent, e.getTovertex().getName());
//
//            if (!rootU.equals(rootV)) {
//                cost += e.getWeight();
//                result.add(e);
//                mergeTrees(parent, rootU, rootV);
//            }
//        }
//        System.out.println(result);
//        result = sortResult(result);
//        System.out.println("Necessary Power Cables to be Upgraded:");
//
//        for (int i = 0; i < result.size(); i++) {  
//            System.out.printf("%2d. %s --- %s (%d km)\n", i + 1, result.get(i).getSource().getName(), result.get(i).getTovertex().getName(), result.get(i).getWeight());
//        }
//
//        System.out.println("\nTotal length: " + cost + " km");
//    }
}
