/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResidentsData;

import JOJOLands.Action;
import JOJOLands.MinimumSpanningTree;
import JOJOLands.TheWorld;

/**
 *
 * @author ASUS
 */
public class AngeloRock extends HeavenDoor implements Action {

    public AngeloRock() {
        super("Angelo Rock");
    }

    public void action(TheWorld game) {
        displayMenu(game);
        String select = game.getSelection();
        System.out.println("=".repeat(70));
        switch (select.charAt(0)) {
            case '1':
                char loc_select = select.charAt(1);
                if ((loc_select - 'A' <= game.getMap().getEdge(game.getCurrentLocation()).size() - 1)
                        && Character.isUpperCase(loc_select)) {
                    game.move(loc_select);
                } else {
                    System.out.println("Option " + select + " not available. Please reselect.");
                    action(game);
                }
                break;
            case '2':
                viewResidentInfo(game);
                break;
            case '3':
                redHotChiliPepper(game);
                break;
            case '4':
                if (!game.getBackhistory().isEmpty()) {
                    game.back();
                } else if (game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.forward();
                } else if (game.getBackhistory().isEmpty() && game.getForwardhistory().isEmpty()) {
                    game.backToTownHall();
                } else {
                    System.out.println("Option " + select + " not available. Please reselect.");
                }
                break;
            case '5':
                if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.forward();
                } else if ((game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty())
                        || (!game.getBackhistory().isEmpty()) && game.getForwardhistory().isEmpty()) {
                    game.backToTownHall();
                } else {
                    System.out.println("Option " + select + " is not available. Please reselect.");
                }
                break;
            case '6':
                if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.backToTownHall();
                } else {
                    System.out.println("Option " + select + " is not available. Please reselect.");
                }
                break;
            default:
                System.out.println("Option " + select + " is not available. Please reselect.");
        }
    }

    public static void displayMenu(TheWorld game) {
        int i = 2;
        System.out.println("Current Location: " + game.getCurrentLocation().getName());
        game.displayCurrentLocationOptions();
        System.out.printf("[%d] View Resident Information%n", i++);
        System.out.printf("[%d] Red Hot Chili Pepper%n", i++);
        if (!game.getBackhistory().isEmpty()) {
            System.out.printf("[%d] Back (%s)%n", i++, game.getBackhistory().peek().getName());
        }
        if (!game.getForwardhistory().isEmpty()) {
            System.out.printf("[%d] Forward (%s)%n", i++, game.getForwardhistory().peek().getName());
        }
        System.out.printf("[%d] Back to Town Hall%n%n", i++);
    }

    public void redHotChiliPepper(TheWorld game) {
        MinimumSpanningTree mst = new MinimumSpanningTree();
        mst.calculateCost(game.getMap().getEdgeList());
        System.out.println("=".repeat(70));
    }
}
