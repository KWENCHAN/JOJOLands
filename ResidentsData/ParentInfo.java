/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResidentsData;

public class ParentInfo {
    private Resident person;
    private int level;
    
    public ParentInfo(Resident person,int level){
        this.person = person;
        this.level = level;
    }
    public Resident getPerson(){
        return person;
    }
    
    public int getLevel(){
        return level;
    }
}
