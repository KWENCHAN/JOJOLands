/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import JOJOLands.Action;
import JOJOLands.TheWorld;
import ResidentsData.Resident;

public class Libeccio extends PearlJam implements Action {

    public Libeccio() {
        super("Libeccio");
        addToMenu("Formaggio", 12.50);
        addToMenu("Ghiaccio", 1.01);
        addToMenu("Melone", 5.20);
        addToMenu("Prosciutto and Pesci", 20.23);
        addToMenu("Risotto", 13.14);
        addToMenu("Zucchero and Sale", 0.60);
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

    public void processOrdersLibeccio(int dayNumber) {
        List<Resident> waitinglist_copy = new ArrayList<>(waitingList);
        Stack<Resident> reverse = new Stack<>();
        int count = 1;
        while (!waitinglist_copy.isEmpty()) {
            List<Resident> temp = new ArrayList<>();
            for (int i = 0; i < waitinglist_copy.size(); i++) {
                if (count % dayNumber == 0) {
                    reverse.push(waitinglist_copy.get(i));
                    temp.add(waitinglist_copy.get(i));
                }
                count++;
            }
            for (Resident removed : temp) {
                waitinglist_copy.remove(removed);
            }
            temp.clear();
        }

        while (!reverse.isEmpty()) {
            orderProcessingList.add(reverse.pop());
        }
    }

    public static void displayMenu(TheWorld game) {
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
