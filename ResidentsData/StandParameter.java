/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResidentsData;

/**
 *
 * @author ASUS
 */
public enum StandParameter {
    Infinity("Infinity", 8),
    A("A", 7),
    B("B", 6),
    C("C", 5),
    D("D", 4),
    E("E", 3),
    QUESTION_MARK("?", 2),
    Null("Null", 1);

    private String label;
    private int value;

    private StandParameter(String label, int value) {
        this.label = label;
        this.value = value;
    }
    
    public static StandParameter enumConverter(String parameter){
        switch(parameter){
            case "?":
                return QUESTION_MARK;
            default:
                return valueOf(parameter);
        }
    }
    
    @Override
    public String toString() {
        return this.label;
    }

    public int getValue() {
        return value;
    }
    
}
