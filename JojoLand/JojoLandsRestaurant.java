package JojoLand;

import java.util.HashMap;
import java.util.Map;

public class JojoLandsRestaurant {
    public static void main(String[] args) {
        // Create menus for restaurants
        Map<String, Double> jadeGardenMenu = new HashMap<>();
        jadeGardenMenu.put("Braised Chicken in Black Bean Sauce", 15.00);
        jadeGardenMenu.put("Braised Goose Web with Vermicelli", 21.00);
        jadeGardenMenu.put("Deep-fried Hiroshima Oysters", 17.00);
        jadeGardenMenu.put("Poached Tofu with Dried Shrimps", 12.00);
        jadeGardenMenu.put("Scrambled Egg White with Milk", 10.00);

        Map<String, Double> cafeDeuxMagotsMenu = new HashMap<>();
        cafeDeuxMagotsMenu.put("Sampling Matured Cheese Platter", 23.00);
        cafeDeuxMagotsMenu.put("Spring Lobster Salad", 35.00);
        cafeDeuxMagotsMenu.put("Spring Organic Omelette", 23.00);
        cafeDeuxMagotsMenu.put("Truffle-flavoured Poultry Supreme", 34.00);
        cafeDeuxMagotsMenu.put("White Asparagus", 26.00);

        Map<String, Double> trattoriaTrussardiMenu = new HashMap<>();
        trattoriaTrussardiMenu.put("Caprese Salad", 10.00);
        trattoriaTrussardiMenu.put("Creme caramel", 6.50);
        trattoriaTrussardiMenu.put("Lamb Chops with Apple Sauce", 25.00);
        trattoriaTrussardiMenu.put("Spaghetti alla Puttanesca", 15.00);

        Map<String, Double> libeccioMenu = new HashMap<>();
        libeccioMenu.put("Formaggio", 12.50);
        libeccioMenu.put("Ghiaccio", 1.01);
        libeccioMenu.put("Melone", 5.20);
        libeccioMenu.put("Prosciutto and Pesci", 20.23);
        libeccioMenu.put("Risotto", 13.14);
        libeccioMenu.put("Zucchero and Sale", 0.60);

        Map<String, Double> savageGardenMenu = new HashMap<>();
        savageGardenMenu.put("Abbacchio’s Tea", 1.00);
        savageGardenMenu.put("DIO’s Bread", 36.14);
        savageGardenMenu.put("Giorno’s Donuts", 6.66);
        savageGardenMenu.put("Joseph’s Tequila", 35.00);
        savageGardenMenu.put("Kakyoin’s Cherry", 3.50);
        savageGardenMenu.put("Kakyoin’s Porridge", 4.44);

        // Create restaurants
        Restaurant jadeGarden = new Restaurant("Jade Garden", jadeGardenMenu);
        Restaurant cafeDeuxMagots = new Restaurant("Cafe Deux Magots", cafeDeuxMagotsMenu);
        Restaurant trattoriaTrussardi = new Restaurant("Trattoria Trussardi", trattoriaTrussardiMenu);
        Restaurant libeccio = new Restaurant("Libeccio", libeccioMenu);
        Restaurant savageGarden = new Restaurant("Savage Garden", savageGardenMenu);
    }
}
