/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.List;

import JOJOLands.Action;
import JOJOLands.TheWorld;

public class SavageGarden extends PearlJam implements Action {

    public SavageGarden() {
        super("Savage Garden");
        addToMenu("Abbacchio's Tea", 1.00);
        addToMenu("DIO's Bread", 36.14);
        addToMenu("Giorno's Donuts", 6.66);
        addToMenu("Joseph's Tequila", 35.00);
        addToMenu("Kakyoin's Cherry", 3.50);
        addToMenu("Kakyoin's Porridge", 4.44);
    }

    public void action(TheWorld game) {
        displayMenu(game);
        String select = game.getSelection();
        if(select==""){
            action(game);
        }
        switch (select.charAt(0)) {
            case '1':
                char loc_select = select.charAt(1);
                if ((loc_select - 'A' <= game.getMap().getEdge(game.getCurrentLocation()).size() - 1)
                        && Character.isUpperCase(loc_select)) {
                    game.move(loc_select);
                } else {
                    System.out.println("Option " + select + " is not available. Please reselect.");
                    action(game);
                }
                break;
            case '2':
                System.out.println("Restaurant: " + getName() + "\n");
                displayWaitingList();
                displayOrderProcessingList();
                break;
            case '3':
                viewMenu();
                break;
            case '4':
                // view sales info
                break;
            case '5':
                // Milagro Man
                break;
            case '6':
                if (!game.getBackhistory().isEmpty()) {
                    game.back();
                } else if (game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.forward();
                } else if (game.getBackhistory().isEmpty() && game.getForwardhistory().isEmpty()) {
                    game.backToTownHall();
                } else {
                    System.out.println("Option " + select + " is not available. Please reselect.");
                }
                break;
            case '7':
                if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.forward();
                } else if ((game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty())
                        || (!game.getBackhistory().isEmpty()) && game.getForwardhistory().isEmpty()) {
                    game.backToTownHall();
                } else {
                    System.out.println("Option " + select + " is not available. Please reselect.");
                }
                break;
            case '8':
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

    public void processOrdersSavageGarden(int dayNumber) {
        List<Customer> waitinglist_copy = new ArrayList<>(waitingList);
        int count = 1;
        while (!waitinglist_copy.isEmpty()) {
            List<Customer> temp = new ArrayList<>();
            for (int i = 0; i < waitinglist_copy.size(); i++) {
                if (count == dayNumber) {
                    Customer matched = waitinglist_copy.get(i);
                    orderProcessingList.add(matched);
                    temp.add(matched);
                    count = 0;
                }
                count++;
            }
            for (Customer removed : temp) {
                waitinglist_copy.remove(removed);
            }
            temp.clear();

            for (int i = waitinglist_copy.size() - 1; i >= 0; i--) {
                if (count == dayNumber) {
                    orderProcessingList.add(waitinglist_copy.get(i));
                    temp.add(waitinglist_copy.get(i));
                    count = 0;
                }
                count++;
            }
            for (Customer removed : temp) {
                waitinglist_copy.remove(removed);
            }
            temp.clear();
        }
    }

    public static void displayMenu(TheWorld game) {
        int i = 2;
        System.out.println("Current Location: " + game.getCurrentLocation().getName());
        game.displayCurrentLocationOptions();
        System.out.printf("[%d] View Waiting List and Order Processing List%n", i++);
        System.out.printf("[%d] View Menu%n", i++);
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
