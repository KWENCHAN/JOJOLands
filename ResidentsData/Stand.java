package ResidentsData;

public class Stand {
    private String stand_name;
    private String destructivePower;
    private String speed;
    private String range;
    private String stamina;
    private String precision;
    private String developmentPotential;

    public Stand(String stand, String destructivePower, String speed, String range, String stamina, String precision, String developmentPotential) {
        this.stand_name = stand;
        this.destructivePower = destructivePower;
        this.speed = speed;
        this.range = range;
        this.stamina = stamina;
        this.precision = precision;
        this.developmentPotential = developmentPotential;
    }

    public String getName() {
        return stand_name;
    }

    public String getDestructivePower() {
        return destructivePower;
    }

    public String getSpeed() {
        return speed;
    }

    public String getRange() {
        return range;
    }

    public String getStamina() {
        return stamina;
    }

    public String getPrecision() {
        return precision;
    }

    public String getDevelopmentPotential() {
        return developmentPotential;
    }  
    
    @Override
    public String toString() {
        return "Stand                 : " + stand_name + "\n" +
               "Destructive Power     : " + destructivePower + "\n" +
               "Speed                 : " + speed + "\n" +
               "Range                 : " + range + "\n" +
               "Stamina               : " + stamina + "\n" +
               "Precision             : " + precision + "\n" +
               "Development Potential : " + developmentPotential + "\n";
    }
}