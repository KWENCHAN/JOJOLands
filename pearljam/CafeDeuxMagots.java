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

public class CafeDeuxMagots extends PearlJam implements Action{
    
    public CafeDeuxMagots(){
        super("Cafe Deux Magots");
        addToMenu("Sampling Matured Cheese Platter", 23.00);
        addToMenu("Spring Lobster Salad", 35.00);
        addToMenu("Spring Organic Omelette", 23.00);
        addToMenu("Truffle-flavoured Poultry Supreme", 34.00);
        addToMenu("White Asparagus", 26.00);
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

    public void processOrdersCafeDeuxMagots() {
        List<Customer> sortedList = new ArrayList<>(waitingList);
        sortedList.sort(Comparator.comparingInt(Customer::getAge));
        
        while (!sortedList.isEmpty()) {            
            orderProcessingList.add(sortedList.remove(sortedList.size()-1));
            if (sortedList.isEmpty()) {
                break;
            }
            orderProcessingList.add(sortedList.remove(0));
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
