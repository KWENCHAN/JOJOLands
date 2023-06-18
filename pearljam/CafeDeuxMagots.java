/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import JOJOLands.Action;
import JOJOLands.TheWorld;
import ResidentsData.Resident;
import java.util.LinkedList;

public class CafeDeuxMagots extends PearlJam implements Action {

    public CafeDeuxMagots() {
        super("Cafe Deux Magots");
        addToMenu("Sampling Matured Cheese Platter", 23.00);
        addToMenu("Spring Lobster Salad", 35.00);
        addToMenu("Spring Organic Omelette", 23.00);
        addToMenu("Truffle-flavoured Poultry Supreme", 34.00);
        addToMenu("White Asparagus", 26.00);
    }

    public void action(TheWorld game) {
        while (true) {
            displayMenu(game);
            String select = game.getSelection();
            if (select == "" || select.matches("\\s+")) {
                System.out.println("Invalid input. Please reselect.");
                continue;
            }
            switch (select.charAt(0)) {
                case '1':
                    if (select.length() != 2) {
                        System.out.println("Option " + select + " not available. Please reselect.");
                        continue;
                    }
                    char loc_select = select.charAt(1);
                    if ((loc_select - 'A' <= game.getMap().getEdgeListforVertex(game.getCurrentLocation()).size() - 1)
                            && Character.isUpperCase(loc_select)) {
                        game.move(loc_select);
                        return;
                    } else {
                        System.out.println("Option " + select + " is not available. Please reselect.");
                        continue;
                    }
                case '2':
                    System.out.println("Restaurant: " + getName() + "\n");
                    displayWaitingList();
                    displayOrderProcessingList();
                    break;
                case '3':
                    viewMenu();
                    break;
                case '4':
                    modifyMenu();
                    break;
                case '5':
                    MoodyBlues.action(this,
                            "C:\\Users\\chank\\OneDrive\\Documents\\UM\\SEM 2\\WIA1002 DATA STRUCTURE\\TestJojo\\src\\pearljam\\orderList.csv",
                            false);
                    break;
                case '6':
                    MilagroMan.action(this);
                    break;
                case '7':
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
                        System.out.println("Option " + select + " is not available. Please reselect.");
                        continue;
                    }
                case '8':
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
                case '9':
                    if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                        game.backToTownHall();
                        return;
                    } else {
                        System.out.println("Option " + select + " is not available. Please reselect.");
                        continue;
                    }
                default:
                    System.out.println("Option " + select + " is not available. Please reselect.");
            }
        }
    }

    public void processOrdersCafeDeuxMagots() {
        List<Resident> sortedList = new LinkedList<>(waitingList);
        List<Resident> NAList = new ArrayList<>();
        for (Resident resident : sortedList) {
            if (resident.getAge().equals("N/A")) {
                NAList.add(resident);
            }
        }
        sortedList.removeAll(NAList);
        sortedList.sort(Comparator.comparing(Resident::getAge));

        while (!sortedList.isEmpty()) {
            orderProcessingList.add(sortedList.remove(sortedList.size() - 1));
            if (sortedList.isEmpty()) {
                break;
            }
            orderProcessingList.add(sortedList.remove(0));
        }
        orderProcessingList.addAll(NAList);
    }

    public void displayMenu(TheWorld game) {
        int i = 2;
        System.out.println("Current Location: " + game.getCurrentLocation().getName());
        game.displayCurrentLocationOptions();
        System.out.printf("[%d] View Waiting List and Order Processing List%n", i++);
        System.out.printf("[%d] View Menu%n", i++);
        System.out.printf("[%d] Modify Menu%n", i++);
        System.out.printf("[%d] View Sales Information%n", i++);
        System.out.printf("[%d] Milagro Man%n", i++);
        if (!game.getBackhistory().isEmpty()) {
            System.out.printf("[%d] Back (%s)%n", i++, game.getBackhistory().peek().getName());
        }
        if (!game.getForwardhistory().isEmpty()) {
            System.out.printf("[%d] Forward (%s)%n", i++, game.getForwardhistory().peek().getName());
        }
        System.out.printf("[%d] Back to Town Hall%n%n", i++);
    }

}
