package JOJOLands;

import Graph.Edge;
import Graph.Graph;
import Graph.Location;
import ResidentsData.AngeloRock;
import ResidentsData.BurningDownTheHouse;
import ResidentsData.JoestarMansion;
import ResidentsData.MoriohGrandHotel;
import pearljam.JadeGarden;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class TheWorld {

    private final Scanner sc = new Scanner(System.in);
    private Graph map;
    private final HashMap<String, Action> locationlist = new HashMap<>();
    private Location currentLocation;
    private final Stack<Location> backhistory = new Stack<>();
    private final Stack<Location> forwardhistory = new Stack<>();
    private int day;
    private String mapSelection;
    private boolean exit;

    public TheWorld() throws JSONException {
        welcome();
        this.exit = false;
    }

    private void welcome() throws JSONException {
        System.out.println("Welcome, to the fantastical realm of JOJOLands.");
        System.out.println("[1] Start Game");
        System.out.println("[2] Load Game");
        System.out.println("[3] Exit\n");
        String select = getSelection();
        System.out.println("=".repeat(70));

        switch (select) {
            case "1" -> {
                setMap(JSONReader.readMap(JSONReader.readJSON(selectMap())));
                setCurrentLocation(map.getVertex("Town Hall"));
//                redHotChiliPepper();
//                theHand();
                burningDownTheHouse("Town Hall");
                setDay(1);
                start();
                break;
            }
            case "2" -> {
                System.out.print("Enter the path of your save file: ");
                sc.nextLine();
                String savepath = sc.nextLine();
                System.out.println("=".repeat(70));
                break;
            }
            case "3" -> {
                return;
            }
            default -> {
                System.out.println("Option " + select + " is not available. Please reselect.");
                welcome();
                break;
            }
        }
    }

    public void start() {
        locationlist.put("Town Hall", new TownHall());
        locationlist.put("Jade Garden", new JadeGarden());
        locationlist.put("Joestar Mansion", new JoestarMansion());
        locationlist.put("Morioh Grand Hotel", new MoriohGrandHotel());
        locationlist.put("Angelo Rock", new AngeloRock());
        displayDay(getDay());
        while (!exit) {
            locationlist.get(this.currentLocation.getName()).action(this);
        }
    }

    public void setMap(Graph map) {
        this.map = map;
    }

    public Graph getMap() {
        return map;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public String getSelection() {
        System.out.print("Select: ");
        String select = sc.nextLine();
        return select;
    }

    public Stack<Location> getBackhistory() {
        return backhistory;
    }

    public Stack<Location> getForwardhistory() {
        return forwardhistory;
    }

    public String selectMap() {
        System.out.println("Select a map:");
        System.out.println("[1] Default Map");
        System.out.println("[2] Parallel Map");
        System.out.println("[3] Alternate Map\n");
        String selection = getSelection();
        String path = "C:/HON YAO ZHI/Data Structure/AssignmentJOJO/src/Map/";
        switch (selection) {
            case "1" ->
                mapSelection = "DefaultMap.json";
            case "2" ->
                mapSelection = "ParallelMap.json";
            case "3" ->
                mapSelection = "AlternateMap.json";
            default -> {
                System.out.println("Option " + selection + " is not available. Please reselect.");
                selectMap();
            }
        }
        path += mapSelection;
        System.out.println("=".repeat(70));
        return path;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void move(char selection) {
        backhistory.push(currentLocation);
        ArrayList<Edge> edge = map.getEdge(currentLocation);
        setCurrentLocation(edge.get(selection - 'A').getTovertex());
        if (!forwardhistory.isEmpty()) {
            if (currentLocation != forwardhistory.peek()) {
                forwardhistory.clear();
            }
        }
    }

    public void back() {
        if (!backhistory.isEmpty()) {
            forwardhistory.push(currentLocation);
            Location previousLocation = backhistory.pop();
            System.out.println("Moving back to " + previousLocation.getName());
            setCurrentLocation(previousLocation);
        }
    }

    public void forward() {
        if (!forwardhistory.isEmpty()) {
            backhistory.push(currentLocation);
            Location nextLocation = forwardhistory.pop();
            System.out.println("Moving forward to " + nextLocation.getName());
            setCurrentLocation(nextLocation);
        }
    }

    public void backToTownHall() {
        setCurrentLocation(getMap().getVertex("Town Hall"));
        backhistory.clear();
        forwardhistory.clear();
    }

    public void displayDay(int day) {

        String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        int index = (day - 1) % 7;
        System.out.printf("It’s Day %d (%s) of our journey in JOJOLands!%n", getDay(), daysOfWeek[index]);
    }

    public void displayCurrentLocationOptions() {
        System.out.print("[1] Move to:\n    ");
        ArrayList<Edge> edges = map.getEdge(currentLocation);

        if (edges.isEmpty()) {
            System.out.println("There are no available destinations from this location.");
            return;
        }
        char option = 'A';

        for (Edge edge : edges) {
            Location destination = edge.getTovertex();
            System.out.print("[" + option + "] " + destination.getName() + "     ");
            option++;
        }
        System.out.println("");
    }

    public void saveGame() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("day", getDay());
            jsonObject.put("map select", mapSelection);
            jsonObject.put("current location", currentLocation.getName());
            // Need to save for reaastaurant sales, waiting list..

        } catch (JSONException ex) {
            System.out.println("Error in saving the game.");
        }

        // Convert the JSON object or array to a string
        String json = jsonObject.toString();

        // Write the JSON string to a file
        try (FileWriter writer = new FileWriter("loadGame.json")) {
            writer.write(json);
            System.out.println("Data written to the file successfully.");

        } catch (IOException e) {
            System.out.println("Error in saving the game.");
        }
    }

    public void redHotChiliPepper() {
        MinimumSpanningTree mst = new MinimumSpanningTree();
        mst.calculateCost(this.map.getEdgeList());
    }

    public void theHand() {
        MinimumSpanningTree mst = new MinimumSpanningTree();
        mst.calculateMaxPath(this.map.getEdgeList());
    }
    
    public void burningDownTheHouse(String cityName){
        BurningDownTheHouse bdh = new BurningDownTheHouse();
        
        if(map.containsCity(cityName)){
            bdh.breadthFirstTraversal(map, cityName);
        }
        else{
            System.out.println("No such city in the map.");
        }
    }
}
