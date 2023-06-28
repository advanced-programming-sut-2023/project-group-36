package model;

import model.Buildings.BuildingType;
import model.Peoples.PeopleType;

import java.util.ArrayList;

public class Types {
    private static ArrayList<PeopleType> peopleTypes = new ArrayList<>();
    private static ArrayList<BuildingType> buildingTypes = new ArrayList<>();

    public static void run(){
        addPeopleTypes();
        addBuildingsTypes();
    }

    public static void addPeopleTypes() {

        peopleTypes.add(new PeopleType("Archer","launchers" ,8, 2,2, 4, 2, new String[]{"type1", "type2"},"Barrack"));
        peopleTypes.add(new PeopleType("Crossbowmen","launchers" ,9, 2,3, 4, 5, new String[]{"type1", "type2"},"Barrack"));
        peopleTypes.add(new PeopleType("ArcherBow","launchers" ,6, 2,2, 4, 2, new String[]{"type1", "type2"},"MercenaryPost"));
        peopleTypes.add(new PeopleType("Slingers","launchers" ,7, 2,1, 4, 1, new String[]{"type1", "type2"},"MercenaryPost"));
        peopleTypes.add(new PeopleType("HorseArchers","launchers" ,10, 2,3, 5, 3, new String[]{"type1", "type2"},"MercenaryPost"));
        peopleTypes.add(new PeopleType("FireThrowers","launchers" ,12, 4,2, 5, 5, new String[]{"type1", "type2"},"MercenaryPost"));

        peopleTypes.add(new PeopleType("Tunneler","NormalPeople" ,8, 3,1, 4, 0, new String[]{"type1", "type2"},"Barrack"));
        peopleTypes.add(new PeopleType("Laddermen","NormalPeople" ,9, 0,1, 4, 0, new String[]{"type1", "type2"},"EngineerGuild"));
        peopleTypes.add(new PeopleType("Engineer","NormalPeople" ,9, 0,1, 3, 0, new String[]{"type1", "type2"},"EngineerGuild"));

        peopleTypes.add(new PeopleType("Spearmen","fightingForce" ,7, 3,1, 3, 0, new String[]{"type1", "type2"},"Barrack"));
        peopleTypes.add(new PeopleType("Pikemen","fightingForce" ,9, 3,4, 2, 0, new String[]{"type1", "type2"},"Barrack"));
        peopleTypes.add(new PeopleType("Macemen","fightingForce" ,10, 4,3, 3, 0, new String[]{"type1", "type2"},"Barrack"));
        peopleTypes.add(new PeopleType("Swordsmen","fightingForce" ,11, 5,5, 1, 0, new String[]{"type1", "type2"},"Barrack"));
        peopleTypes.add(new PeopleType("Knight","fightingForce" ,14, 5,4, 5, 0, new String[]{"type1", "type2"},"Barrack"));
        peopleTypes.add(new PeopleType("BlackMonk","fightingForce" ,8, 3,3, 2, 0, new String[]{"type1", "type2"},"Cathedral"));
        peopleTypes.add(new PeopleType("Slaves","fightingForce" ,4, 1,1, 4, 0, new String[]{"type1", "type2"},"MercenaryPost"));
        peopleTypes.add(new PeopleType("Assassins","fightingForce" ,9, 3,3, 3, 0, new String[]{"type1", "type2"},"MercenaryPost"));
        peopleTypes.add(new PeopleType("ArabianSwordsmen","fightingForce" ,13, 4,4, 5, 0, new String[]{"type1", "type2"},"MercenaryPost"));

    }


    public static void addBuildingsTypes() {
        buildingTypes.add(new BuildingType("SmallStoneGatehouse","CastleBuildings",0,0,0,0,0,0,0,0,8,0,0));
        buildingTypes.add(new BuildingType("BigStoneGatehouse","CastleBuildings",0,20,0,0,0,0,0,0,10,0,0));
        buildingTypes.add(new BuildingType("Keep","CastleBuildings",0,0,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("DrawBridge","CastleBuildings",10,0,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("LookoutTower","TowerAndLookouts",0,10,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("CircleTower","TowerAndLookouts",0,40,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("SquareTower","TowerAndLookouts",0,35,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Perimeter tower","TowerAndLookouts",0,10,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Turret","TowerAndLookouts",0,15,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Armoury","WeaponBuildings",5,0,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Barrack","WeaponBuildings",0,15,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("EngineerGuild","WeaponBuildings",10,0,100,0,0,0,0,0,8,0,0));
        buildingTypes.add(new BuildingType("KillingPit","Trap",6,0,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Hovel","TownBuildings",6,0,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Church","TownBuildings",0,0,250,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Cathedral","WeaponBuildings",0,0,1000,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Armourer","WeaponBuildings",20,0,100,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("Blacksmith","WeaponBuildings",20,0,100,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("Fletcher","WeaponBuildings",20,0,100,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("Poleturner","WeaponBuildings",10,0,100,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("Tunnel","Other",0,0,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Stockpile","Storge",0,0,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Quarry","FarmBuildings",20,0,0,0,0,0,0,0,0,0,3));
        buildingTypes.add(new BuildingType("PitchRig","FarmBuildings",20,0,0,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("Mill","FoodProccessingBuildings",20,0,0,0,0,0,0,0,0,0,3));
        buildingTypes.add(new BuildingType("Inn","CastleBuildings",20,0,100,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("Ditch","Trap",0,0,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("MercenaryPost","WeaponBuildings",10,0,0,0,0,0,0,0,8,0,0));
        buildingTypes.add(new BuildingType("PitchDitch","Trap",0,0,0,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("CagedWarDogs","Trap",10,0,100,10000,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("SiegeTent","CastleBuildings",0,0,0,0,0,0,0,0,8,50,0));
        buildingTypes.add(new BuildingType("Stable","Storage",20,0,100,0,0,0,0,0,0,0,0));
        buildingTypes.add(new BuildingType("Store","Industry",20,0,0,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("OilSmelter","Industry",0,10,100,0,0,0,0,0,0,1,1));
        buildingTypes.add(new BuildingType("IronMine","FarmBuildings",20,0,0,0,0,0,0,0,0,0,2));
        buildingTypes.add(new BuildingType("WoodCutter","FarmBuildings",3,0,0,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("AppleFarm","FarmBuildings",5,0,0,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("WheatFarm","FarmBuildings",15,0,0,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("BarleyFarm","FarmBuildings",15,0,0,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("Bakery","FoodProccessingBuildings",10,0,0,0,0,0,0,0,0,0,1));
        buildingTypes.add(new BuildingType("Market","FoodProccessingBuildings",10,0,0,0,0,0,0,0,0,0,1));
    }

    public static PeopleType getPeopleTypeByType(String type){
        run();
        for (PeopleType peopleType : peopleTypes) {
            if(peopleType.type.equals(type)){
                return peopleType;
            }
        }
        return null;
    }

    public static BuildingType getBuildingTypeByType(String type){
        run();
        System.out.println("Type: "+type);
        for (BuildingType buildingType : buildingTypes) {
            System.out.println("BT: " +buildingType.getType());
            if(buildingType.getType().equals(type)){
                System.out.println("hiType!");
                return buildingType;
            }
        }
        return null;
    }

}
