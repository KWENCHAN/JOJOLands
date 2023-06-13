/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import JOJOLands.Action;
import JOJOLands.TheWorld;
import ResidentsData.TheGoldenSpirit;

public class JadeGarden extends PearlJam implements Action{

    public JadeGarden() {
        super("Jade Garden");
        addToMenu("Braised Chicken in Black Bean Sauce", 15.00);
        addToMenu("Braised Goose Web with Vermicelli", 21.00);
        addToMenu("Deep-fried Hiroshima Oysters", 17.00);
        addToMenu("Poached Tofu with Dried Shrimps", 12.00);
        addToMenu("Scrambled Egg White with Milk", 10.00);
    }

    public void action(TheWorld game) {
        displayMenu(game);
        String select = game.getSelection();
        System.out.println("=".repeat(70));
        switch (select.charAt(0)) {
            case '1':
                char loc_select = select.charAt(1);
                if ((loc_select - 'A' <= game.getMap().getEdge(game.getCurrentLocation()).size() - 1) && Character.isUpperCase(loc_select)) {
                    game.move(loc_select);
                } else {
                    System.out.println("Option "+select+" is not available. Please reselect.");
                    action(game);
                }
                break;
            case '2':
            System.out.println("Restaurant: "+getName()+"\n");
                displayWaitingList();
                displayOrderProcessingList();
                break;
            case '3':
                viewMenu();
                break;
            case '4':
                //view sales info
                break;
            case '5':
                //Milagro Man
                break;
            case '6':
                if (!game.getBackhistory().isEmpty()) {
                    game.back();
                } else if (game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.forward();
                } else if (game.getBackhistory().isEmpty() && game.getForwardhistory().isEmpty()) {
                    game.backToTownHall();
                } else {
                    System.out.println("Option "+select+" is not available. Please reselect.");
                }
                break;
            case '7':
                if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.forward();
                } else if ((game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty())
                        || (!game.getBackhistory().isEmpty()) && game.getForwardhistory().isEmpty()) {
                    game.backToTownHall();
                } else {
                    System.out.println("Option "+select+" is not available. Please reselect.");
                }
                break;
            case '8':
                if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.backToTownHall();
                } else {
                    System.out.println("Option "+select+" is not available. Please reselect.");
                }
                break;
            default:
                System.out.println("Option "+select+" is not available. Please reselect.");
        }
    }

    public List<Customer> sortOrder(List<Customer> waitingList) {
        List<Customer> sortedList = new ArrayList<>();
        int left = 0;
        int right = waitingList.size() - 1;
        while (left <= right) {
            sortedList.add(waitingList.get(left));
            if (left == right) {
                break;
            }
            left += 1;

            sortedList.add(waitingList.get(right));
            if (left == right) {
                break;
            }
            right -= 1;
        }
        return sortedList;
    }

    public void processOrdersJadeGarden() {
        int start = 0;
        int end = waitingList.size() - 1;

        while (start < end) {
            Customer firstCustomer = waitingList.get(start);
            Customer lastCustomer = waitingList.get(end);

            orderProcessingList.add(firstCustomer);
            orderProcessingList.add(lastCustomer);

            start++;
            end--;
        }

        if (start == end) {
            orderProcessingList.add(waitingList.get(start));
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
