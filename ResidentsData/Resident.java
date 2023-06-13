package ResidentsData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Resident {
    private final String name;
    private final String age;
    private final String gender;
    private final List<String> parents;
    private final List<Resident> parentList;
    private final Stand stand;
    private final ArrayList<String> orderHistory=new ArrayList<>();
    private final ArrayList<String> restaurantHistory=new ArrayList<>();
    
    public Resident(String name, String age, String gender, ArrayList<String> parents) {
        this(name, age, gender, parents, null);
    }
    
    public Resident(String name, String age, String gender, ArrayList<String> parents, Stand stand) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.parents = parents;
        this.stand=stand;
        this.parentList=new ArrayList<>();
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

    public List<String> getParents() {
        return parents;
    }

    public List<Resident> getParentList() {
        return parentList;
    }
    
    public void addtoParentList(Resident parent){
        parentList.add(parent);
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
               "Parents : " + parents.toString().substring(1, parents.toString().length()-1) + "\n" +
               (getStand()==null? "":getStand());
    }
}