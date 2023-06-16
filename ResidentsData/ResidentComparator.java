package ResidentsData;

import java.util.Comparator;
import java.util.List;

public class ResidentComparator implements Comparator<Resident> {
    private List<SortCriteria> criteriaList;

    public ResidentComparator(List<SortCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @Override
    public int compare(Resident r1, Resident r2) {
        for (SortCriteria criteria : criteriaList) {
            int result = compareField(r1, r2, criteria.getField());

            if (criteria.getSortOrder().equalsIgnoreCase("asc")) {
                if (result != 0) {
                    return result;
                }
            } else if (criteria.getSortOrder().equalsIgnoreCase("desc")) {
                if (result != 0) {
                    return -result;
                }
            }
        }

        // If all sorting criteria are the same, maintain the original order
        return 0;
    }

    private int compareField(Resident r1, Resident r2, String field) {
        if (field.equalsIgnoreCase("destructive")) {
            return compareStandField(r1.getStand(), r2.getStand(), "destructive");
        } else if (field.equalsIgnoreCase("speed")) {
            return compareStandField(r1.getStand(), r2.getStand(), "speed");
        } else if (field.equalsIgnoreCase("range")) {
            return compareStandField(r1.getStand(), r2.getStand(), "range");
        } else if (field.equalsIgnoreCase("stamina")) {
            return compareStandField(r1.getStand(), r2.getStand(), "stamina");
        } else if (field.equalsIgnoreCase("precision")) {
            return compareStandField(r1.getStand(), r2.getStand(), "precision");
        } else if (field.equalsIgnoreCase("development")) {
            return compareStandField(r1.getStand(), r2.getStand(), "development");
        } else if (field.equalsIgnoreCase("name")) {
            return compareNames(r1.getName(), r2.getName());
        } else if (field.equalsIgnoreCase("stand")) {
            return compareStands(r1.getStand(), r2.getStand());
        } else if (field.equalsIgnoreCase("age")) {
//            return compareAges(r1.getAge(), r2.getAge());
        } else {
            return 0; // No sorting for other fields
        }
        return 0;
    }

    private int compareStandField(Stand s1, Stand s2, String field) {
        if (s1 == null && s2 == null) {
            return 0;
        } else if (s1 == null) {
            return -1;
        } else if (s2 == null) {
            return 1;
        } else {
            String value1 = getStandFieldValue(s1, field);
            String value2 = getStandFieldValue(s2, field);

            // Handle "N/A" values
            if (value1.equals("N/A") && value2.equals("N/A")) {
                return 0;
            } else if (value1.equals("N/A")) {
                return -1;
            } else if (value2.equals("N/A")) {
                return 1;
            }

            // Invert the comparison for certain fields
            if (field.equalsIgnoreCase("destructive") ||
                    field.equalsIgnoreCase("speed") ||
                    field.equalsIgnoreCase("range") ||
                    field.equalsIgnoreCase("stamina") ||
                    field.equalsIgnoreCase("precision") ||
                    field.equalsIgnoreCase("development")) {
                return value2.compareTo(value1);
            }

            // Default comparison for other fields
            return value1.compareTo(value2);
        }
    }

    private String getStandFieldValue(Stand stand, String field) {
        switch (field) {
            case "destructive":
                return stand.getDestructivePower();
            case "speed":
                return stand.getSpeed();
            case "range":
                return stand.getRange();
            case "stamina":
                return stand.getStamina();
            case "precision":
                return stand.getPrecision();
            case "development":
                return stand.getDevelopmentPotential();
            default:
                return "N/A";
        }
    }
    private int compareNames(String name1, String name2) {
        return name1.compareToIgnoreCase(name2);
    }

    private int compareStands(Stand s1, Stand s2) {
        if (s1 == null && s2 == null) {
            return 0;
        } else if (s1 == null) {
            return -1;
        } else if (s2 == null) {
            return 1;
        } else {
            String stand1 = s1.getName();
            String stand2 = s2.getName();
            return stand1.compareToIgnoreCase(stand2);
        }
    }

    private int compareAges(String age1, String age2) {
        // Handle "N/A" values
        if (age1.equalsIgnoreCase("N/A") && age2.equalsIgnoreCase("N/A")) {
            return 0;
        } else if (age1.equalsIgnoreCase("N/A")) {
            return -1;
        } else if (age2.equalsIgnoreCase("N/A")) {
            return 1;
        }

        int ageValue1 = parseAge(age1);
        int ageValue2 = parseAge(age2);

        return Integer.compare(ageValue1, ageValue2);
    }

    private int parseAge(String age) {
        if (age.equalsIgnoreCase("N/A")) {
            return -1;
        } else {
            try {
                return Integer.parseInt(age);
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

}
