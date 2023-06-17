//package JOJOLands;
//
//import Graph.Graph;
//import Graph.Location;
//
//import java.util.*;
//
//public class MoriohBus {
//
//    private static Graph graph;
//
//    public static void action(Graph graph, TheWorld game) {
//        Location startingLocation = game.getCurrentLocation();
//
//        // Use Dijkstra's algorithm to find the shortest path
//        Map<Location, Integer> distances = new HashMap<>();
//        Map<Location, Location> previousLocations = new HashMap<>();
//        PriorityQueue<Location> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
//        Set<Location> visited = new HashSet<>();
//
//        // Initialize distances
//        for (Location location : graph.getVertexList()) {
//            if (location == startingLocation) {
//                distances.put(location, 0);
//            } else {
//                distances.put(location, Integer.MAX_VALUE);
//            }
//            queue.offer(location);
//        }
//
//        while (!queue.isEmpty()) {
//            Location currentLocation = queue.poll();
//            visited.add(currentLocation);
//
//            if (currentLocation != startingLocation) {
//                System.out.println("> " + currentLocation.getName() + " (" + distances.get(currentLocation) + " km)");
//            }
//
//            List<Location> neighbors = graph.getNeighbors(currentLocation);
//            for (Location neighbor : neighbors) {
//                if (!visited.contains(neighbor)) {
//                    int distance = distances.get(currentLocation) + graph.getEdge(currentLocation, neighbor).getWeight();
//                    if (distance < distances.get(neighbor)) {
//                        distances.put(neighbor, distance);
//                        previousLocations.put(neighbor, currentLocation);
//                        queue.remove(neighbor); // Remove and re-add to update its position in the priority queue
//                        queue.offer(neighbor);
//                    }
//                }
//            }
//        }
//
//        // Display the shortest possible route and its total distance
//        System.out.println("Shortest Morioh Bus Route:");
//        Location currentLocation = game.getCurrentLocation();
//        while (currentLocation != startingLocation) {
//            Location previousLocation = previousLocations.get(currentLocation);
//            System.out.println("> " + currentLocation.getName() + " (" + graph.getEdge(previousLocation, currentLocation).getWeight() + " km)");
//            currentLocation = previousLocation;
//        }
//        System.out.println("> " + startingLocation.getName() + " (" + distances.get(game.getCurrentLocation()) + " km)");
//
//        // Return to the previous menu
//        game.displayCurrentLocationOptions();
//    }
//}
