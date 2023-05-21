/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JOJOLands;

import Graph.Graph;
import Graph.Location;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONException;
import org.json.*;

/**
 *
 * @author ASUS
 */
public class JSONReader {

    public static String readJSON(String path) {
        try {
            Scanner in = new Scanner(new File(path));
            String temp = "";
            while (in.hasNextLine()) {
                temp += in.nextLine();
            }
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Graph readMap(String content) throws JSONException {
        JSONObject obj = new JSONObject(content);
        Graph graph = new Graph();
        HashMap<String, Location> checkExist = new HashMap<>();

        Iterator<String> iter = obj.keys();
        while (iter.hasNext()) {
            String next = iter.next();

            // Check if the location already exists in the map
            Location loc;
            if (checkExist.containsKey(next)) {
                loc = checkExist.get(next);
            } else {
                loc = new Location(next);
                checkExist.put(next, loc);
                graph.addVertex(loc);
            }

            JSONArray weight = obj.getJSONObject(next).getJSONArray("weight");
            JSONArray edge = obj.getJSONObject(next).getJSONArray("edge");

            for (int i = 0; i < edge.length(); i++) {
                String edgeName = edge.getString(i);

                // Check if the edge location already exists in the map
                Location edgeLoc;
                if (checkExist.containsKey(edgeName)) {
                    edgeLoc = checkExist.get(edgeName);
                } else {
                    edgeLoc = new Location(edgeName);
                    checkExist.put(edgeName, edgeLoc);
                    graph.addVertex(edgeLoc);
                }

                graph.addEdge(loc, edgeLoc, weight.getInt(i));
            }
        }

        return graph;
    }

}
