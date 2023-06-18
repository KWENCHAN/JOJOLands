/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResidentsData;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author yaozh
 */
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

        if (field.equalsIgnoreCase("age")) {
            return compareAges(r1.getAge(), r2.getAge());
        } else if (field.equalsIgnoreCase("name") || field.equalsIgnoreCase("stand") || field.equalsIgnoreCase("gender")) {
            return compareResidentInfo(r1, r2, field);
        } else {
            return compareStandField(r1.getStand(), r2.getStand(), field.toLowerCase());
        }

    }

    private int compareStandField(Stand s1, Stand s2, String field) {

        String value1 = "Null";
        String value2 = "Null";

        if (s1 != null) {
            value1 = getStandFieldValue(s1, field);
        }

        if (s2 != null) {
            value2 = getStandFieldValue(s2, field);
        }

        StandValueOrder order1 = StandValueOrder.getOrder(value1);
        StandValueOrder order2 = StandValueOrder.getOrder(value2);

        return order1.compareTo(order2);

    }

    private int compareResidentInfo(Resident r1, Resident r2, String field) {

        String value1 = getResidentInfoValue(r1, field);
        String value2 = getResidentInfoValue(r2, field);

        // Handle "N/A" values
        if (value1.equalsIgnoreCase("N/A") && value2.equalsIgnoreCase("N/A")) {
            return 0;
        } else if (value1.equalsIgnoreCase("N/A")) {
            return 1;
        } else if (value2.equalsIgnoreCase("N/A")) {
            return -1;
        }

        return value1.compareToIgnoreCase(value2);

    }

    private int compareAges(String age1, String age2) {
        // Handle "N/A" values
        if (age1.equalsIgnoreCase("N/A") && age2.equalsIgnoreCase("N/A")) {
            return 0;
        } else if (age1.equalsIgnoreCase("N/A")) {
            return 1;
        } else if (age2.equalsIgnoreCase("N/A")) {
            return -1;
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

    public enum StandValueOrder {
        INFINITY, A, B, C, D, E, QUESTION_MARK, NULL;
        public static StandValueOrder getOrder(String value) {
            if (value.equals("Infinity")) {
                return INFINITY;
            } else if (value.equals("A")) {
                return A;
            } else if (value.equals("B")) {
                return B;
            } else if (value.equals("C")) {
                return C;
            } else if (value.equals("D")) {
                return D;
            } else if (value.equals("E")) {
                return E;
            } else if (value.equals("?")) {
                return QUESTION_MARK;
            } else {
                return NULL;
            }
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
            case "stand":
                return stand.getName();
            default:
                return "Null";
        }
    }

    private String getResidentInfoValue(Resident resident, String field) {
        switch (field) {
            case "name":
                return resident.getName();
            case "age":
                return resident.getAge();
            case "gender":
                return resident.getGender();
            case "stand":
                if (!resident.isStandNull()) {
                    return resident.getStand().getName();
                } else {
                    return "N/A";
                }
            default:
                return "N/A";
        }
    }
}
