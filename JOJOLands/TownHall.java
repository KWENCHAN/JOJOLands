/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JOJOLands;

import Graph.Location;

/**
 *
 * @author ASUS
 */
public class TownHall extends Location {
    
    public TownHall() {
        super("Town Hall");
    }
    
    public static void action(TheWorld game){
        menu(game);
        String select = game.getSelection();
        System.out.println("=".repeat(70));
        switch (select.charAt(0)) {
            case '1':
                game.move(select.charAt(1));
                break;
            case '2':
                advanceToNextDay(game);
                break;
            case '3':
                game.saveGame();
                break;
            case '4':
                game.setExit(true);
                break;
            default:
                System.out.println("Selected option is not available");
        }
    }
    
    private static void advanceToNextDay(TheWorld game) {
        game.setDay(game.getDay() + 1);
        game.displayDay(game.getDay());
    }

    private static void menu(TheWorld game) {
        System.out.println("Current Location: " + game.getCurrentLocation().getName());
        game.displayCurrentLocationOptions();
        System.out.println("[2] Advance to Next Day");
        System.out.println("[3] Save Game");
        System.out.println("[4] Exit\n");
    }
}
