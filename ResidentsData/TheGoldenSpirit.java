/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ResidentsData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TheGoldenSpirit {

    private static HashMap<String, Resident> joestarMap = new HashMap<>();

    public static void getLCA() {
        String[] residential = { "Morioh Grand Hotel", "Green Dolphin Street Prison", "San Giorgio Maggiore",
                "Polnareff Land", "Joestar Mansion", "Vineyard", "DIO's Mansion", "Angelo Rock" };
        ArrayList<Resident> allResidents = new ArrayList<>();
        for (String res : residential) {
            allResidents.addAll(CSVreader.readResident(res));
        }

        for (Resident resident : allResidents) {
            joestarMap.put(resident.getName(), resident);
        }

        // connect all the residents as a family tree
        for (Resident resident : joestarMap.values()) {
            for (String parent : resident.getParents()) {
                if (parent.equals("N/A")) {
                    continue;
                }
                Resident prt = joestarMap.get(parent);
                if (prt != null) {
                    resident.addtoParentList(joestarMap.get(parent));
                } else {
                    resident.addtoParentList(new Resident(parent, null, null, new ArrayList<>()));
                }
            }
        }

        Scanner input = new Scanner(System.in);
        Resident person1 = null;
        Resident person2 = null;
        while (person1 == null) {
            System.out.print("Enter the name of first Joestar: ");
            person1 = getJoestar(input.nextLine());
            if (person1 == null) {
                System.out.println("Invalid name for the first Joestar.");
                continue;
            }
            break;
        }

        while (person2 == null) {
            System.out.print("Enter the name of second Joestar: ");
            person2 = getJoestar(input.nextLine());
            if (person2 == null) {
                System.out.println("Invalid name for the second Joestar.");
                continue;
            }
            break;
        }

        System.out.println("==================================================================");

        List<ParentInfo> parentLine1 = findParentLine(person1);
        List<ParentInfo> parentLine2 = findParentLine(person2);

        List<Resident> lowestCommonAncestors = findLowestCommonAncestors(parentLine1, parentLine2);

        System.out.println("Lowest Common Ancestors of " + person1.getName() + " and " + person2.getName() + ": ");
        if (lowestCommonAncestors.isEmpty()) {
            System.out.println("The Joestars has no common ancestors.");
        } else {
            for (int i = 0; i < lowestCommonAncestors.size(); i++) {
                System.out.print(lowestCommonAncestors.get(i).getName());
                if (i < lowestCommonAncestors.size() - 1) {
                    System.out.print(" and ");
                }
            }
            System.out.println();
        }
        System.out.println("==================================================================");
    }

    public static Resident getJoestar(String name) {
        return joestarMap.get(name);
    }

    public static List<Resident> findLowestCommonAncestors(List<ParentInfo> parentLine1, List<ParentInfo> parentLine2) {
        List<Resident> lowestCommonAncestors = new ArrayList<>();
        int lowestLevel = Integer.MAX_VALUE;

        for (ParentInfo info1 : parentLine1) {
            for (ParentInfo info2 : parentLine2) {
                if (info1.getPerson() == info2.getPerson() && info1.getLevel() > 0 && info2.getLevel() > 0) {
                    int currentLevel = Math.min(info1.getLevel(), info2.getLevel());
                    if (currentLevel < lowestLevel) {
                        lowestCommonAncestors.clear();
                        lowestLevel = currentLevel;
                    }
                    if (currentLevel == lowestLevel) {
                        lowestCommonAncestors.add(info1.getPerson());
                    }
                }
            }
        }
        return lowestCommonAncestors;
    }

    public static List<ParentInfo> findParentLine(Resident person) {
        return findParentLine(person, 0);
    }

    private static List<ParentInfo> findParentLine(Resident person, int level) {
        List<ParentInfo> parentLine = new ArrayList<>();
        parentLine.add(new ParentInfo(person, level));
        List<Resident> parents = person.getParentList();
        if (parents.isEmpty()) {
            return parentLine; // Base case: no parents
        }
        for (Resident parent : parents) {
            parentLine.addAll(findParentLine(parent, level + 1)); // Recursive call with increased level
        }
        return parentLine;
    }
}
