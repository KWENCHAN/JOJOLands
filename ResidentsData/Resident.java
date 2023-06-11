package ResidentsData;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Resident {
    private final String name;
    private final String age;
    private final String gender;
    private final String parents;
    private final Stand stand;
    private final ArrayList<String> orderHistory=new ArrayList<>();
    private final ArrayList<String> restaurantHistory=new ArrayList<>();
    
    public Resident(String name, String age, String gender, String parents) {
        this(name, age, gender, parents, null);
    }
    
    public Resident(String name, String age, String gender, String parents, Stand stand) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.parents = parents;
        this.stand=stand;
    }

    public String getName() {
        return name;
    }

    public Stand getStand() {
        if (this.stand == null) {
            return null;
        }
        return this.stand;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getParents() {
        return parents;
    }
    
    public void addOrderHistory(String order){
        this.orderHistory.add(order);
    }
    
    public void addRestaurantHistory(String restaurant){
        this.restaurantHistory.add(restaurant);
    }

    public ArrayList<String> getOrderHistory() {
        return orderHistory;
    }

    public ArrayList<String> getRestaurantHistory() {
        return restaurantHistory;
    }

    @Override
    public String toString() {
        return "Name    : " + name + "\n" +
               "Age     : " + age + "\n" +
               "Gender  : " + gender + "\n" +
               "Parents : " + parents + "\n" +
               (getStand()==null? "":getStand());
    }
}