/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResidentsData;

import JOJOLands.Action;
import JOJOLands.AnotherOneBitesTheDust;
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
<<<<<<< HEAD
        while (true) {
            displayMenu(game);
            String select = game.getSelection();
            if (select == ""||select.matches("\\s+")) {
                System.out.println("Invalid input. Please reselect.");
                continue;
            }
            if(select.length()<2){
                System.out.println("Option " + select + " not available. Please reselect.");
                continue;
            }
            switch (select.charAt(0)) {
                case '1':
                    char loc_select = select.charAt(1);
                    if ((loc_select - 'A' <= game.getMap().getEdgeListforVertex(game.getCurrentLocation()).size() - 1)
                            && Character.isUpperCase(loc_select)) {
                        game.move(loc_select);
                        return;
                    } else {
                        System.out.println("Option " + select + " not available. Please reselect.");
                        continue;
                    }
                case '2':
                    viewResidentInfo();
                    Innermenu();
                    break;
                case '3':
                    redHotChiliPepper(game);
                    break;
                case '4':
                    anotherOneBitesTheDust(game);
                    break;
                case '5':
                    if (!game.getBackhistory().isEmpty()) {
                        game.back();
                        return;
                    } else if (game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                        game.forward();
                        return;
                    } else if (game.getBackhistory().isEmpty() && game.getForwardhistory().isEmpty()) {
                        game.backToTownHall();
                        return;
                    } else {
                        System.out.println("Option " + select + " not available. Please reselect.");
                        continue;
                    }
                case '6':
                    if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                        game.forward();
                        return;
                    } else if ((game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty())
                            || (!game.getBackhistory().isEmpty()) && game.getForwardhistory().isEmpty()) {
                        game.backToTownHall();
                        return;
                    } else {
                        System.out.println("Option " + select + " is not available. Please reselect.");
                        continue;
                    }
                case '7':
                    if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                        game.backToTownHall();
                        return;
                    } else {
                        System.out.println("Option " + select + " is not available. Please reselect.");
                        continue;
                    }
                default:
=======
        displayMenu(game);
        String select = game.getSelection();
        if (select == "") {
            action(game);
        }
        switch (select.charAt(0)) {
            case '1':
                char loc_select = select.charAt(1);
                if ((loc_select - 'A' <= game.getMap().getEdgeListforVertex(game.getCurrentLocation()).size() - 1)
                        && Character.isUpperCase(loc_select)) {
                    game.move(loc_select);
                } else {
                    System.out.println("Option " + select + " not available. Please reselect.");
                    action(game);
                }
                break;
            case '2':
                viewResidentInfo();
                Innermenu();
                break;
            case '3':
                redHotChiliPepper(game);
                break;
            case '4':
                anotherOneBitesTheDust(game);
                break;
            case '5':
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
            case '6':
                if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.forward();
                } else if ((game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty())
                        || (!game.getBackhistory().isEmpty()) && game.getForwardhistory().isEmpty()) {
                    game.backToTownHall();
                } else {
>>>>>>> 33b7c425fbdc5e18f2fd9e319fa20b132083ca9e
                    System.out.println("Option " + select + " is not available. Please reselect.");
            }
        }
    }

    public static void displayMenu(TheWorld game) {
        int i = 2;
        System.out.println("Current Location: " + game.getCurrentLocation().getName());
        game.displayCurrentLocationOptions();
        System.out.printf("[%d] View Resident Information%n", i++);
        System.out.printf("[%d] Red Hot Chili Pepper%n", i++);
        System.out.printf("[%d] Another One Bites The Dust%n", i++);
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
<<<<<<< HEAD

    public void anotherOneBitesTheDust(TheWorld game) {
=======
    
    public void anotherOneBitesTheDust(TheWorld game){
>>>>>>> 33b7c425fbdc5e18f2fd9e319fa20b132083ca9e
        AnotherOneBitesTheDust btd = new AnotherOneBitesTheDust(game.getMap());
        btd.hasBitesTheDust(btd.getPath());
    }
}
