package JOJOLands;

import Graph.Edge;
import Graph.Graph;
import Graph.Location;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import org.json.JSONException;

/**
 *
 * @author ASUS
 */
public class TheWorld {

    private final Scanner sc = new Scanner(System.in);
    private Graph map;
    private Location currentLocation;
    private Stack<Location> history;
    private int day;

    public TheWorld() throws JSONException {
        welcome();
    }

    private void welcome() throws JSONException {
        System.out.println("Welcome, to the fantastical realm of JOJOLands.");
        System.out.println("[1] Start Game");
        System.out.println("[2] Load Game");
        System.out.println("[3] Exit\n");
        int select = getSelection();
        System.out.println("=".repeat(70));

        switch (select) {
            case 1 -> {
                setMap(JSONReader.readMap(JSONReader.readJSON(selectMap())));
                map.printGraph();
                setCurrentLocation(map.getVertex("Town Hall"));
                setDay(1);
                start();
            }
            case 2 -> {
                System.out.print("Enter the path of your save file: ");
                sc.nextLine();
                String savepath = sc.nextLine();
                System.out.println("=".repeat(70));
            }
            case 3 -> {
                return;
            }
            default -> {
                System.out.println("Option " + select + " is not available. Please reselect.");
                welcome();
            }
        }
    }
    
    public void start(){
        System.out.printf("It’s Day %d (%s) of our journey in JOJOLands!%n",getDay(),getDayOfWeek(getDay()));
        System.out.println("Current Location: "+currentLocation.getName());
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
    
    public int getSelection() {
        System.out.print("Select: ");
        int select = sc.nextInt();
        return select;
    }

    public String selectMap() {
        System.out.println("Select a map:");
        System.out.println("[1] Default Map");
        System.out.println("[2] Parallel Map");
        System.out.println("[3] Alternate Map\n");
        int select = getSelection();
        String path = "C:/Users/ASUS/Documents/UM/SEM 2/WIA1002 DATA STRUCTURE/AssignmentSem2/src/Map/";
        switch (select) {
            case 1 -> path += "DefaultMap.json";
            case 2 -> path += "ParallelMap.json";
            case 3 -> path += "AlternateMap.json";
        }
        System.out.println("=".repeat(70));
        return path;
    }
    
    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public void move(char selection) {

        ArrayList<Edge> edge = map.getEdge(currentLocation);
        setCurrentLocation(edge.get(selection - 'A').getTovertex());
    }

    private void back() {
        if (!history.isEmpty()) {
            Location previousLocation = history.pop();
            System.out.println("Moving back to " + previousLocation.getName());
            setCurrentLocation(previousLocation);
        } else {
            System.out.println("No previous location.");
        }
    }
    
    private static String getDayOfWeek(int day) {
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int index = (day - 1) % 7;
        return daysOfWeek[index];
    }
}
