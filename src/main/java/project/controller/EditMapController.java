package project.controller;

import project.model.*;
import project.model.Buildings.BuildingType;
import project.model.Buildings.Structure;
import project.model.Peoples.*;
import project.view.CreateNewGameMenu;
import project.view.EditMapMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class EditMapController {
    public static int x=0;
    public static int y=0;
    public static String setGovernment(Matcher matcher, Map map, ArrayList<User> users) {
         x=Integer.parseInt(matcher.group("x"));
         y=Integer.parseInt(matcher.group("y"));
        String color = matcher.group("color");
        if (x> map.getSize() || y> map.getSize() || x<1 || y<1){
            return "Error: Invalid position!";
        }
        if (map.getBlockByPosition(x,y).getGovernmentId()!=0){
            return "Error: This position has already been selected for another government!";
        }
        if (EditMapMenu.colors.contains(color)){
            return "Error: This color already has selected!";
        }
        map.getBlockByPosition(x,y).setColor(color);
        Government government = new Government(users.get(EditMapMenu.number),color);
        EditMapMenu.governments.add(government);
        EditMapMenu.government = government;
        EditMapMenu.number+=1;
        return "Government "+users.get(EditMapMenu.number).getUsername()+" , "+color+" position successfully set.";
    }

    public static String dropUnit(Matcher matcher,Government government,Map map){
        if (government==null){
            return "Error: No government selected!";
        }
        String type = matcher.group("type");
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (x> map.getSize() || y> map.getSize() || x<1 || y<1){
            return "Error: Invalid position!";
        }
        PeopleType peopleType = Types.getPeopleTypeByType(type);
        if (peopleType==null){
            return "Error: Invalid people type!";
        }
        People people;
        Block block = map.getBlockByPosition(x,y);
        if (block.getThisBlockStructure()!=null || !block.suitableTypeForUnit(type)){
            return "Error: You can't drop this unit in this block!";
        }
        if (peopleType.category.equals("launchers")){
            people = new Launcher(peopleType,government,block);
        }
        else if (peopleType.category.equals("fightingForce")){
            people = new FightingForce(peopleType,government,block);
        }
        else {
            people = new NormalPeople(peopleType,government,block);
        }
        government.addPeople(people);
        block.addPeople(people);
        return "drop unit done successfully.";
    }

    public static String dropBuilding(Matcher matcher,Government government){
        if (government==null){
            return "Error: No government selected!";
        }
        if(checkMapPreparation().equals(null))
            return checkMapPreparation();
        x=Integer.parseInt(matcher.group("x"));
        y=Integer.parseInt(matcher.group("y"));
        String type=matcher.group("type");
        if(x>EditMapMenu.capacity || y>EditMapMenu.capacity)
            return "Invalid cordinates!";
        BuildingType buildingType=Types.getBuildingTypeByType(type);
        if(buildingType.equals(null))
            return "Invalid Building name!";
        Map currentMap=ApplicationManager.getCurrentGame().getMap();
        Block currentBlock = currentMap.getBlockByPosition(x,y);
        if(!currentBlock.getThisBlockStructure().equals(null))
            return "This Block has already been occupied by another structure!";
        if(!checkBuildingPrerequisite(type).equals(null)){
            return checkBuildingPrerequisite(type);
        }
        employment(buildingType);
        currentBlock.setThisBlockStructure(new Structure(currentBlock,government,new ArrayList<Militia>(),new ArrayList<NormalPeople>(),buildingType));
        return "drop building done successfully.";
    }


    public static String checkMapPreparation() {
        if (EditMapMenu.number!= CreateNewGameMenu.capacity){
            return "You still haven't decided the headquarters of all the governments!";
        }
        return null;
    }
    public static String checkBuildingPrerequisite(String type) throws ArrayIndexOutOfBoundsException{
        if(checkForEnoughResources(Types.getBuildingTypeByType(type).getWoodCost(),Types.getBuildingTypeByType(type).getStoneCost(),Types.getBuildingTypeByType(type).getGoldCost())==true)
                        return "you don't have enough resourses for building this structure!";
        if(checkForEnoughWorkingPeople(type)==false)
                        return "you don't have enough free people for employeeng in this building!";
        switch (type){
            case "SmallGateHouse":
                return null;
            case "BigGateHouse":
                return null;
            case "CircleTower":
                return null;
            case "LookoutTower":
                return null;
            case "DrawBridge":
                return null;
            case "Turret":
                return null;
            case "Perimeter tower":
                return null;
            case "SquareTower":
                return null;
            case "Armoury":
                return null;
            case "Barrack":
                return null;
            case "EngineerGuild":
                return null;
            case "KillingPit":
                return null;
            case "Hovel":
                return null;
            case "Church":
                return null;
            case "Cathedral":
                return null;
            case "Armourer":
                return null;
            case "Blacksmith":
                return null;
            case "Fletcher":
                return null;
            case "Poleturner":
                return null;
            case "Tunnel":
                return null;
            case "Stockpile":
                if(EditMapMenu.government.getBuildingByNameForGoverment("Stockpile").equals(null))
                    return null;
                else{
                        if(EditMapMenu.map.getBlockByPosition(x,y+1).getThisBlockStructure().getBuildingType().getType().equals("Stockpile"))
                            return null;
                    if(EditMapMenu.map.getBlockByPosition(x,y-1).getThisBlockStructure().getBuildingType().getType().equals("Stockpile"))
                        return null;
                    if(EditMapMenu.map.getBlockByPosition(x+1,y).getThisBlockStructure().getBuildingType().getType().equals("Stockpile"))
                        return null;
                    if(EditMapMenu.map.getBlockByPosition(x-1,y).getThisBlockStructure().getBuildingType().getType().equals("Stockpile"))
                        return null;

                    return "you have already a stockpile in your city.you should put the new stockpile near it!";
                }
            case "Quarry":
                return null;
            case "PitchRig":
                return null;
            case "Mill":
                return null;
            case "Inn":
                return null;
            case "Ditch":
                return null;
            case "MercenaryPost":
                return null;
            case "PitchDitch":
                return null;
            case "CagedWarDogs":
                return null;
            case "SiegeTent":
                return null;
            case "Stable":
                return null;
            case "Store":
                return null;
            case "OilSmelter":
                return null;
            case "IronMine":
                return null;
            case "WoodCutter":
                return null;

        }
       return null;
    }
    public static  boolean checkForEnoughResources(int Wood,int Stone,int gold){
        if(EditMapMenu.government.getCoins()<gold)
            return false;
        if(EditMapMenu.government.getAmountOfResource("wood")<Wood || EditMapMenu.government.getAmountOfResource("stone")<Stone)
            return false;

        return true;
    }
    public static boolean checkForEnoughWorkingPeople(String type){
        BuildingType buildingType= Types.getBuildingTypeByType(type);
        int d=buildingType.getRequiredPeopleToWork();
        if(d==0)
            return true;
        int count=0;
        for(People normalPeople:EditMapMenu.government.getPeoples()){
            if(normalPeople instanceof NormalPeople){
                count++;
                if(d==count)
                    return true;
            }
        }
        return false;
    }
    public static void employment(BuildingType buildingType){
        int cou=buildingType.getRequiredPeopleToWork();
        int d=0;
        for(People normalPeople:EditMapMenu.government.getPeoples()){
            if(normalPeople instanceof NormalPeople && ((NormalPeople) normalPeople).isEmployed()==false){
                ((NormalPeople) normalPeople).setEmployed(true);
                d++;
                if(d==cou)
                    return;
            }
        }
    }
    public static void CostPay(String BuildingType){
        BuildingType buildingType=Types.getBuildingTypeByType(BuildingType);
        int Wood=buildingType.getWoodCost();
        int Stone= buildingType.getStoneCost();
        int gold=buildingType.getGoldCost();
        EditMapMenu.government.getResources().changeResourceAmount("wood",Wood);
        EditMapMenu.government.getResources().changeResourceAmount("stone",Stone);
        EditMapMenu.government.changeCoins((-1)*gold);
    }
}
