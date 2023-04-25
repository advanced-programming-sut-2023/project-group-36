package project.model;

import project.model.Buildings.BuildingType;
import project.model.Peoples.PeopleType;

import java.util.ArrayList;

public class Types {
    private static ArrayList<PeopleType> peopleTypes;
    private static ArrayList<BuildingType> buildingTypes;

    public static void run(){
        addPeopleTypes();
        addBuildingsTypes();
    }

    private static void addPeopleTypes() {

        peopleTypes.add(new PeopleType("Archer","launchers" ,8, 2,2, 4, 2));
        peopleTypes.add(new PeopleType("Crossbowmen","launchers" ,9, 2,3, 4, 5));
        peopleTypes.add(new PeopleType("ArcherBow","launchers" ,6, 2,2, 4, 2));
        peopleTypes.add(new PeopleType("Slingers","launchers" ,7, 2,1, 4, 1));
        peopleTypes.add(new PeopleType("HorseArchers","launchers" ,10, 2,3, 5, 3));
        peopleTypes.add(new PeopleType("FireThrowers","launchers" ,12, 4,2, 5, 5));

        peopleTypes.add(new PeopleType("Tunneler","NormalPeople" ,8, 3,1, 4, 0));
        peopleTypes.add(new PeopleType("Laddermen","NormalPeople" ,9, 0,1, 4, 0));
        peopleTypes.add(new PeopleType("Engineer","NormalPeople" ,9, 0,1, 3, 0));

        peopleTypes.add(new PeopleType("Spearmen","fightingForce" ,7, 3,1, 3, 0));
        peopleTypes.add(new PeopleType("Pikemen","fightingForce" ,9, 3,4, 2, 0));
        peopleTypes.add(new PeopleType("Macemen","fightingForce" ,10, 4,3, 3, 0));
        peopleTypes.add(new PeopleType("Swordsmen","fightingForce" ,11, 5,5, 1, 0));
        peopleTypes.add(new PeopleType("Knight","fightingForce" ,14, 5,4, 5, 0));
        peopleTypes.add(new PeopleType("BlackMonk","fightingForce" ,8, 3,3, 2, 0));
        peopleTypes.add(new PeopleType("Slaves","fightingForce" ,4, 1,1, 4, 0));
        peopleTypes.add(new PeopleType("Assassins","fightingForce" ,9, 3,3, 3, 0));
        peopleTypes.add(new PeopleType("ArabianSwordsmen","fightingForce" ,13, 4,4, 5, 0));
        // ... just for example!

    }

    private static void addBuildingsTypes() {
        // ...
    }

    public static PeopleType getPeopleTypeByType(String type){
        for (PeopleType peopleType : peopleTypes) {
            if(peopleType.type.equals(type)){
                return peopleType;
            }
        }
        return null;
    }

    public static BuildingType getBuildingTypeByType(String type){
        for (BuildingType buildingType : buildingTypes) {
            if(buildingType.type.equals(type)){
                return buildingType;
            }
        }
        return null;
    }

}
