/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pearljam;

public class Customer {
    private int count=0;
    private String name;
    private int age;
    private String gender;
    private String order;

    public Customer(String name, int age, String gender, String order){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.order = order;
        count++;
}
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    public void setOrder(String order){
        this.order = order;
    }
    
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
    public String getGender(){
        return gender;
    }
    
    public String getOrder(){
        return order;
    }
    
    @Override
    public String toString(){
        return String.format("| %-2d | %-18s | %-3d | %-6s | %-57s |",count,getName(),getAge(),getGender(),getOrder());
    }
}
