//package JOJOLands;
//
//import Graph.Graph;
//import Graph.Location;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class ExpressTrain {
//
//    private static Graph graph;
//
//    public static void action(Graph graph, TheWorld game) {
//        Location startingLocation = game.getCurrentLocation();
//
//        List<Location> locations = new ArrayList<>(graph.getVertexList());
//        locations.remove(startingLocation);
//
//        List<Location> shortestPath = null;
//        int shortestDistance = Integer.MAX_VALUE;
//
//        // Generate permutations of the remaining locations
//        List<List<Location>> permutations = generatePermutations(locations);
//
//        // Find the shortest path
//        for (List<Location> permutation : permutations) {
//            int totalDistance = 0;
//            Location currentLocation = startingLocation;
//
//            for (Location location : permutation) {
//                totalDistance += graph.getEdge(currentLocation, location).getWeight();
//                currentLocation = location;
//            }
//
//            totalDistance += graph.getEdge(currentLocation, startingLocation).getWeight();
//
//            if (totalDistance < shortestDistance) {
//                shortestDistance = totalDistance;
//                shortestPath = new ArrayList<>(permutation);
//            }
//        }
//
//        // Display the shortest possible path and its total distance
//        System.out.println("Shortest Express Train Route:");
//        System.out.println(startingLocation.getName());
//
//        if (shortestPath != null) {
//            for (Location location : shortestPath) {
//                System.out.println("> " + location.getName() + " (" + graph.getEdge(startingLocation, location).getWeight() + " km)");
//                startingLocation = location;
//            }
//            System.out.println("> " + game.getCurrentLocation().getName() + " (" + graph.getEdge(startingLocation, game.getCurrentLocation()).getWeight() + " km)");
//            System.out.println("Total Distance: " + shortestDistance + " km");
//        } else {
//            System.out.println("No path found that visits every location exactly once.");
//        }
//
//        // Return to the previous menu
//        game.displayCurrentLocationOptions();
//    }
//
//    private static List<List<Location>> generatePermutations(List<Location> locations) {
//        List<List<Location>> permutations = new ArrayList<>();
//        generatePermutationsHelper(locations, 0, permutations);
//        return permutations;
//    }
//
//    private static void generatePermutationsHelper(List<Location> locations, int start, List<List<Location>> permutations) {
//        if (start >= locations.size()) {
//            permutations.add(new ArrayList<>(locations));
//        } else {
//            for (int i = start; i < locations.size(); i++) {
//                Collections.swap(locations, start, i);
//                generatePermutationsHelper(locations, start + 1, permutations);
//                Collections.swap(locations, start, i);
//            }
//        }
//    }
//}
