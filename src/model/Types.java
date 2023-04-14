package model;

import model.Peoples.PeopleType;

import java.util.ArrayList;

public class Types {
    private static ArrayList<PeopleType> peopleTypes;
    private static ArrayList<BuildingType> buildingTypes;

    public static void run(){
        addPeopleTypes();
        addBuildingsTypes();
    }

    private static void addPeopleTypes() {
        peopleTypes.add(new PeopleType("Archer","launchers" ,1, 2,2, 4, 0));
        peopleTypes.add(new PeopleType("Crossbowmen","launchers" ,1, 2,2, 4, 0));
        peopleTypes.add(new PeopleType("Spearmen","launchers" ,1, 2,2, 4, 0));
        peopleTypes.add(new PeopleType("Pikemen","launchers" ,1, 2,2, 4, 0));
        peopleTypes.add(new PeopleType("Macemen","launchers" ,1, 2,2, 4, 0));
        peopleTypes.add(new PeopleType("Swordsmen","launchers" ,1, 2,2, 4, 0));
        // ... just for example!
    }

    private static void addBuildingsTypes() {
        // ...
    }

    public PeopleType getPeopleTypeByType(String type){
        for (PeopleType peopleType : peopleTypes) {
            if(peopleType.type.equals(type)){
                return peopleType;
            }
        }
        return null;
    }

    public BuildingType getBuildingTypeByType(String type){
        for (BuildingType buildingType : buildingTypes) {
            if(buildingType.type.equals(type)){
                return buildingType;
            }
        }
        return null;
    }

}
