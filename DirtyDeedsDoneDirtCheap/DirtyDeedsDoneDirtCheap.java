package DirtyDeedsDoneDirtCheap;

import java.util.*;

public class DirtyDeedsDoneDirtCheap {

    private static final Scanner scanner = new Scanner(System.in);
    private static Graph<String, Integer> map;
    private static ArrayList locations;

    public static void dirtyDeeds() {
        System.out.println("List of locations:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.printf("[%d] %s%n", i, locations.get(i));
        }
        System.out.println();

        System.out.print("Source: ");
        String source = scanner.nextLine();
        System.out.print("Destination: ");
        String destination = scanner.nextLine();

        ThreeShortestPaths(source, destination);

        System.out.println("======================================================================");
        System.out.print("Do you want to try again? [y/n]: ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            System.out.println("======================================================================");
            dirtyDeeds();
        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println("Returning back.....\n");
        }
    }

    public static void ThreeShortestPaths(String source, String destination) {
        List<List<String>> result = new ArrayList<>();
        List<List<String>> potentialPaths = new ArrayList<>();

        List<String> topShortestPath = Dijkstra(source, destination);
        topShortestPath.add(destination);
        result.add(topShortestPath);

        for (int i = 1; i < 3; i++) {
            List<String> previousPath = result.get(i - 1);

            for (int j = 0; j < previousPath.size() - 1; j++) {
                String spurNode = previousPath.get(j);
                String nextSpurNode = previousPath.get(j + 1);
                List<String> rootPath = previousPath.subList(0, j + 1);

                int weight = map.getEdgeWeight(spurNode, nextSpurNode);
                map.resetEdgeWeight(spurNode, nextSpurNode, Integer.MAX_VALUE);

                List<String> spurPath = Dijkstra(spurNode, destination);

                if (!spurPath.isEmpty()) {
                    List<String> totalPath = new ArrayList<>(rootPath);
                    totalPath.addAll(spurPath.subList(1, spurPath.size()));
                    totalPath.add(destination);
                    potentialPaths.add(totalPath);
                }

                map.resetEdgeWeight(spurNode, nextSpurNode, weight);
            }

            if (potentialPaths.isEmpty()) {
                break;
            }

            potentialPaths.sort(Comparator.comparingInt(DirtyDeedsDoneDirtCheap::calculatePathLength));

            boolean added = false;
            for (List<String> path : potentialPaths) {
                for (List<String> existingPath : result) {
                    if (path.size() != existingPath.size() && !new HashSet<>(existingPath).containsAll(path)) {
                        result.add(path);
                        potentialPaths.remove(path);
                        added = true;
                        break;
                    }
                }
                if (added) {
                    break;
                }
            }
        }

        result.sort(Comparator.comparingInt(DirtyDeedsDoneDirtCheap::calculatePathLength));
        List<Integer> pathLengths = new ArrayList<>();
        for (List<String> path : result) {
            int length = calculatePathLength(path);
            pathLengths.add(length);
        }

        printPath(result, pathLengths);
    }

    public static int calculatePathLength(List<String> path) {
        int length = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String source = path.get(i);
            String destination = path.get(i + 1);
            length += map.getEdgeWeight(source, destination);
        }
        return length;
    }

    public static List<String> Dijkstra(String source, String destination) {
        Graph.Vertex<String, Integer> sourceVertex = map.getVertex(source);
        Graph.Vertex<String, Integer> destinationVertex = map.getVertex(destination);

        map.resetVertices();
        sourceVertex.setDistance();
        PriorityQueue<Vertex<String, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Vertex::getDistance));

        while (!queue.isEmpty()) {
            Vertex<String, Integer> vertex = queue.poll();
            vertex.setVisited();

            Edge<String, Integer> edge = vertex.getFirstEdge();
            while (edge != null) {
                Vertex<String, Integer> neighbour = edge.getToVertex();

                if (!neighbour.isVisited()) {
                    int distance = vertex.getDistance() + edge.getWeight();
                    if (distance < neighbour.getDistance()) {
                        neighbour.setDistance(distance);
                        List<String> newPath = new ArrayList<>(vertex.getShortestPath());
                        newPath.add(vertex.getVertexInfo());
                        neighbour.setShortestPath();
                        queue.add(neighbour);
                    }
                }
                edge = edge.getNextEdge();
            }
        }

        return destinationVertex.getShortestPath();
    }

    public static void printPath(List<List<String>> paths, List<Integer> lengths) {
        System.out.println("Top Three Shortest Paths:");
        for (int i = 0; i < paths.size(); i++) {
            System.out.printf("%d. ", i + 1);
            List<String> path = paths.get(i);
            for (int j = 0; j < path.size(); j++) {
                if (j == path.size() - 1) {
                    System.out.printf("%s (%dkm)%n", path.get(j), lengths.get(i));
                } else {
                    System.out.print(path.get(j) + " --- ");
                }
            }
        }
        System.out.println();
        System.out.println("======================================================================");
    }

    public static void setLocations(ArrayList locations) {
        DirtyDeedsDoneDirtCheap.locations = locations;
    }
}
