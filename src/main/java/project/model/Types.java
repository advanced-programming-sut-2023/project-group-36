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

        peopleTypes.add(new PeopleType("Archer","launchers" ,8, 2,2, 4, 2, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("Crossbowmen","launchers" ,9, 2,3, 4, 5, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("ArcherBow","launchers" ,6, 2,2, 4, 2, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("Slingers","launchers" ,7, 2,1, 4, 1, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("HorseArchers","launchers" ,10, 2,3, 5, 3, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("FireThrowers","launchers" ,12, 4,2, 5, 5, new String[]{"type1", "type2"}));

        peopleTypes.add(new PeopleType("Tunneler","NormalPeople" ,8, 3,1, 4, 0, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("Laddermen","NormalPeople" ,9, 0,1, 4, 0, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("Engineer","NormalPeople" ,9, 0,1, 3, 0, new String[]{"type1", "type2"}));

        peopleTypes.add(new PeopleType("Spearmen","fightingForce" ,7, 3,1, 3, 0, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("Pikemen","fightingForce" ,9, 3,4, 2, 0, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("Macemen","fightingForce" ,10, 4,3, 3, 0, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("Swordsmen","fightingForce" ,11, 5,5, 1, 0, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("Knight","fightingForce" ,14, 5,4, 5, 0, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("BlackMonk","fightingForce" ,8, 3,3, 2, 0, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("Slaves","fightingForce" ,4, 1,1, 4, 0, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("Assassins","fightingForce" ,9, 3,3, 3, 0, new String[]{"type1", "type2"}));
        peopleTypes.add(new PeopleType("ArabianSwordsmen","fightingForce" ,13, 4,4, 5, 0, new String[]{"type1", "type2"}));
        // ... just for example!

    }

    private static void addBuildingsTypes() {
        buildingTypes.add(new BuildingType("SmallStoneGatehouse","CastleBuildings",25,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Big stone gatehouse","CastleBuildings",25,0,0,0,0,0,10,0));
        buildingTypes.add(new BuildingType("Keep","CastleBuildings",40,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("DrawBridge","CastleBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("LookoutTower","TowerAndLookouts",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("CircleTower","TowerAndLookouts",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("SquareTower","TowerAndLookouts",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Perimeter tower","TowerAndLookouts",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Turret","TowerAndLookouts",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Armoury","WeaponBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Barrack","WeaponBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("EngineerGuild","WeapomBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("KillingPit","Trap",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Hovel","TownBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Church","TownBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Cathedral","WeaponBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Armourer","WeaponBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Blacksmith","WeaponBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Fletcher","WeaponBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Poleturner","WeaponBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Tunnel","Other",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Stockpile","Storge",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Quarry","FarmBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("PitchRig","FarmBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Mill","FoodProccessingBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Inn","CastleBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Ditch","Trap",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("MercenaryPost","WeaponBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("PitchDitch","Trap",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("CagedWarDogs","Trap",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("SiegeTent","CastleBuildings",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Stable","Storge",0,0,0,0,0,0,8,0));
        buildingTypes.add(new BuildingType("Store","Industry",0,0,0,0,0,0,8,0));
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
            if(buildingType.getType().equals(type)){
                return buildingType;
            }
        }
        return null;
    }

}
