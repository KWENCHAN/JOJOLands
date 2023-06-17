package JOJOLands;

import Graph.Edge;
import Graph.Graph;
import Graph.Location;
import ResidentsData.AngeloRock;
import ResidentsData.DIOsMansion;
import ResidentsData.GreenDolphinStreetPrison;
import ResidentsData.HeavenDoor;
import ResidentsData.JoestarMansion;
import ResidentsData.MoriohGrandHotel;
import ResidentsData.PolnareffLand;
import ResidentsData.RandomFoodSelection;
import ResidentsData.Resident;
import ResidentsData.SanGiorgioMaggiore;
import ResidentsData.Vineyard;
import pearljam.CafeDeuxMagots;
import pearljam.FoodItem;
import pearljam.JadeGarden;
import pearljam.Libeccio;
import pearljam.SavageGarden;
import pearljam.TrattoriaTrussardi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pearljam.PearlJam;

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
    private String mapSelectionPath;
    private boolean exit;
    private final List<PearlJam> restaurantList = new ArrayList<>();
    private final List<HeavenDoor> residentialArea = new ArrayList<>();

    public TheWorld() throws JSONException {
        locationlist.put("Town Hall", new TownHall());

        locationlist.put("Jade Garden", new JadeGarden());
        locationlist.put("Libeccio", new Libeccio());
        locationlist.put("Savage Garden", new SavageGarden());
        locationlist.put("Cafe Deux Magots", new CafeDeuxMagots());
        locationlist.put("Trattoria Trussardi", new TrattoriaTrussardi());

        locationlist.put("Joestar Mansion", new JoestarMansion());
        locationlist.put("Morioh Grand Hotel", new MoriohGrandHotel());
        locationlist.put("Angelo Rock", new AngeloRock());
        locationlist.put("DIO's Mansion", new DIOsMansion());
        locationlist.put("Green Dolphin Street Prison", new GreenDolphinStreetPrison());
        locationlist.put("Polnareff Land", new PolnareffLand());
        locationlist.put("San Giorgio Maggiore", new SanGiorgioMaggiore());
        locationlist.put("Vineyard", new Vineyard());

        restaurantList.add((PearlJam) locationlist.get("Cafe Deux Magots"));
        restaurantList.add((PearlJam) locationlist.get("Libeccio"));
        restaurantList.add((PearlJam) locationlist.get("Jade Garden"));
        restaurantList.add((PearlJam) locationlist.get("Savage Garden"));
        restaurantList.add((PearlJam) locationlist.get("Trattoria Trussardi"));

        residentialArea.add((HeavenDoor) locationlist.get("Angelo Rock"));
        residentialArea.add((HeavenDoor) locationlist.get("DIO's Mansion"));
        residentialArea.add((HeavenDoor) locationlist.get("Green Dolphin Street Prison"));
        residentialArea.add((HeavenDoor) locationlist.get("Joestar Mansion"));
        residentialArea.add((HeavenDoor) locationlist.get("Morioh Grand Hotel"));
        residentialArea.add((HeavenDoor) locationlist.get("Polnareff Land"));
        residentialArea.add((HeavenDoor) locationlist.get("San Giorgio Maggiore"));
        residentialArea.add((HeavenDoor) locationlist.get("Vineyard"));
        welcome();
        this.exit = false;
    }

    private void welcome() throws JSONException {
        System.out.println("Welcome, to the fantastical realm of JOJOLands.");
        System.out.println("[1] Start Game");
        System.out.println("[2] Load Game");
        System.out.println("[3] Exit\n");
        String select = getSelection();

        switch (select) {
            case "1" -> {
                setMap(JSONReader.readMap(JSONReader.readJSON(selectMap())));
                setCurrentLocation(map.getVertex("Town Hall"));
                //burningDownTheHouse("Trattoria Trussardi");
                AnotherOneBitesTheDust obj = new AnotherOneBitesTheDust(map);
                obj.hasBitesTheDust("Jade Garden > Cafe Deux Magots > Town Hall > Morioh Grand Hotel > Jade Garden > Town Hall > Jade Garden > Cafe Deux Magots > Town Hall > Jade Garden > Town Hall > Morioh Grand Hotel");
                obj.hasBitesTheDust("Savage Garden > Angelo Rock > Savage Garden > Angelo Rock > Savage Garden");
                obj.hasBitesTheDust("Joestar Mansion > Libeccio > DIO's Mansion > Vineyard > DIO's Mansion > Libeccio > DIO's Mansion > Vineyard > San Giorgio Maggiore");
                setDay(1);
                setDay(1);
                start();
                break;
            }
            case "2" -> {
                System.out.print("Enter the path of your save file: ");
                //“C:\\HON YAO ZHI\\Data Structure\\AssignmentJOJO\\loadGame.JSON”
                String savepath = sc.nextLine();
                loadGame(savepath);
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

        displayDay(getDay());
        generateFood_clearWaitingList();
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
        System.out.println("=".repeat(70));
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
        String path = "C:\\Users\\chank\\Documents\\UM\\SEM 2\\WIA1002 DATA STRUCTURE\\JOJOLandsMaster\\Map\\";
        switch (selection) {
            case "1" ->
                path += "DefaultMap.json";
            case "2" ->
                path += "ParallelMap.json";
            case "3" ->
                path += "AlternateMap.json";
            default -> {
                System.out.println("Option " + selection + " is not available. Please reselect.");
                selectMap();
            }
        }
        mapSelectionPath = path;
        return path;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public void setExit(boolean exit) {
        if (exit) {
            RandomFoodSelection.clearCSV();
        }
        this.exit = exit;
    }

    public void move(char selection) {
        backhistory.push(currentLocation);
        ArrayList<Edge> edge = map.getEdgeListforVertex(currentLocation);
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

        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int index = (day - 1) % 7;
        System.out.printf("It’s Day %d (%s) of our journey in JOJOLands!%n", getDay(), daysOfWeek[index]);
    }

    public void advanceToNextDay() {
        setDay(getDay() + 1);
        displayDay(getDay());
        generateFood_clearWaitingList();
    }

    public void generateFood_clearWaitingList() {

        for (PearlJam restaurant : restaurantList) {
            restaurant.getWaitingList().clear();
            restaurant.getOrderProcessingList().clear();
        }

        for (HeavenDoor resiArea : residentialArea) {
            RandomFoodSelection.selectFood(resiArea.getResidentList(), restaurantList);
        }
        RandomFoodSelection.printSalesCSV(day, restaurantList);
    }

    public void displayCurrentLocationOptions() {
        System.out.print("[1] Move to:\n    ");
        ArrayList<Edge> edges = map.getEdgeListforVertex(currentLocation);

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
            jsonObject.put("Day", getDay());
            jsonObject.put("Map", mapSelectionPath);
            jsonObject.put("Current Location", currentLocation.getName());
            JSONArray backHist = new JSONArray(backhistory);
            JSONArray forwardHist = new JSONArray(forwardhistory);
            jsonObject.put("Back History", backHist);
            jsonObject.put("Forward History", forwardHist);
            JSONObject orderHist = new JSONObject();
            for (HeavenDoor resArea : residentialArea) {
                for (Resident resident : resArea.getResidentList()) {
                    JSONArray foodHist = new JSONArray(resident.getOrderHistory());
                    JSONObject resi = new JSONObject();
                    resi.put("Food History", foodHist);
                    orderHist.put(resident.getName(), resi);
                }
            }
            jsonObject.put("Resident", orderHist);

            JSONObject menu = new JSONObject();
            for (PearlJam restaurant : restaurantList) {
                menu.put(restaurant.getName(), restaurant.getMenu());
            }
            jsonObject.put("Restaurant", menu);
        } catch (JSONException ex) {
            System.out.println("Error in saving the game.");
        }

        // Convert the JSON object or array to a string
        String json = jsonObject.toString();

        // Write the JSON string to a file
        try ( FileWriter writer = new FileWriter("loadGame.json")) {
            writer.write(json);
            System.out.println("Data written to the file successfully.");

        } catch (IOException e) {
            System.out.println("Error in saving the game.");
        }
    }

    public void loadGame(String path) {

        try {
            JSONObject obj = new JSONObject(JSONReader.readJSON(path));

            int daySaved = obj.getInt("Day");
            setDay(daySaved);

            String mapSelectedPath = obj.getString("Map");
            setMap(JSONReader.readMap(JSONReader.readJSON(mapSelectedPath)));
            // Need another way to retrieve path for map

            String currentLocationSaved = obj.getString("Current Location");
            setCurrentLocation(map.getVertex(currentLocationSaved));

            JSONArray backHist = obj.getJSONArray("Back History");
            JSONArray forwardHist = obj.getJSONArray("Forward History");
            for (int i = 0; i < backHist.length(); i++) {
                JSONObject name = backHist.getJSONObject(i);
                backhistory.push(map.getVertex(name.getString("name")));
            }
            for (int i = 0; i < forwardHist.length(); i++) {
                JSONObject name = forwardHist.getJSONObject(i);
                forwardhistory.push(map.getVertex(name.getString("name")));
            }

            JSONObject restaurant = obj.getJSONObject("Restaurant");
            for (PearlJam restau : restaurantList) {
                restau.getMenu().clear();
                JSONObject menu = restaurant.getJSONObject(restau.getName());
                Iterator<String> keys = menu.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    double value = menu.getInt(key);
                    restau.getMenu().put(key, value);
                }
            }

            JSONObject resident = obj.getJSONObject("Resident");
            for (HeavenDoor resArea : residentialArea) {
                for (Resident res : resArea.getResidentList()) {
                    JSONObject history = resident.getJSONObject(res.getName());
                    JSONArray foodHist = history.getJSONArray("Food History");
                    for (int i = 0; i < foodHist.length(); i++) {
                        JSONObject foodItem = foodHist.getJSONObject(i);
                        String restauName = foodItem.getString("restaurant");
                        String food = foodItem.getString("food");
                        double price = foodItem.getDouble("price");
                        FoodItem item = new FoodItem(restauName, food, 1, price);
                        res.addOrderHistory(item);
                    }
                    PearlJam LastRestaurant = (PearlJam) locationlist.get(foodHist.getJSONObject(foodHist.length() - 1).getString("restaurant"));
                    LastRestaurant.addResidentToWaitingList(res);
                }
            }

            int numOfDay = residentialArea.get(0).getResidentList().get(0).getOrderHistory().size();
            for (int i = 0; i < numOfDay; i++) {
                for (HeavenDoor resArea : residentialArea) {
                    for (Resident resident1 : resArea.getResidentList()) {
                        FoodItem foodItem = resident1.getOrderHistory().get(i);
                        RandomFoodSelection.addSales(foodItem.getFood(), foodItem.getPrice());
                    }
                }
                RandomFoodSelection.printSalesCSV(i + 1, restaurantList);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        displayDay(day);
        while (!exit) {
            locationlist.get(this.currentLocation.getName()).action(this);
        }
    }

    public void anotherOneBitesTheDust(){
        AnotherOneBitesTheDust btd = new AnotherOneBitesTheDust(map);
        btd.hasBitesTheDust(btd.getPath());
    }
}
