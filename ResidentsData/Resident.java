package ResidentsData;

import java.util.HashMap;

public class Resident {
    private final String name;
    private final String age;
    private final String gender;
    private final String parents;
    private final Stand stand;
    private final HashMap<String,String> orderHistory;
    
    public Resident(String name, String age, String gender, String parents) {
        this(name, age, gender, parents, null);
    }
    
    public Resident(String name, String age, String gender, String parents, Stand stand) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.parents = parents;
        this.stand=stand;
        this.orderHistory=new HashMap<>();
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
    
    public void addOrderHistory(String food,String restaurant){
        orderHistory.put(food, restaurant);
    }
    
    public HashMap getOrderHistory(){
        return orderHistory;
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