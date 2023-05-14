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
    public static Block currentBlock;
    public static String setGovernment(Matcher matcher, Map map, ArrayList<User> users) {
         x=Integer.parseInt(matcher.group("x"));
         y=Integer.parseInt(matcher.group("y"));
        String color = matcher.group("color");
        if (x> map.getSize() || y> map.getSize() || x<1 || y<1){
            return "Error: Invalid position!";
        }
        if (map.getBlockByPosition(x,y).getThisBlockStructure()!=null){
            return "Error: This position has already been selected for another government!";
        }
        if (EditMapMenu.colors.contains(color)){
            return "Error: This color already has selected!";
        }
        Block block = map.getBlockByPosition(x,y);
        block.setColor(color);
        Structure centralCastle = new Structure(100);
        Government government = new Government(users.get(EditMapMenu.number),color, centralCastle);
        block.setThisBlockStructure(centralCastle);
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

    public static String dropBuilding(Matcher matcher,Government government,Map map){
        BuildingType buildingType=Types.getBuildingTypeByType(matcher.group("type"));
        Resources resources=government.getResources();
        if (buildingType.equals(null))
            return "Invalid building name!";
        try {
            x=Integer.parseInt(matcher.group("x"));
            y=Integer.parseInt(matcher.group("y"));

        }catch (Exception e){
            return "Invalid Cordinates!";
        }
        if (x> map.getSize() || y> map.getSize() || x<1 || y<1){
            return "Error: Invalid position!";
        }
        Block block=map.getBlockByPosition(x,y);
        if(block.getThisBlockStructure() != null)
            return "this block is already occiupied!";
        if( !simpleBuildingPrecutionsCheck(buildingType,government).equals(null))
            return simpleBuildingPrecutionsCheck(buildingType,government);
        Structure structure=new Structure(block,government,new ArrayList<Militia>(),new ArrayList<NormalPeople>(),buildingType);
        block.setThisBlockStructure(structure);
        government.getStructures().add(structure);
        return null;
    }
    public  static String  simpleBuildingPrecutionsCheck(BuildingType buildingType,Government government){
        Resources resources=government.getResources();
        switch (buildingType.getType()){
            case "SmallGateHouse":
                return null;
            case "Turret":
                return null;
            case "BigGateHouse":
                return null;
            case "CircleTower":
                return null;
            case "LookoutTower":
                return null;
            case "DrawBridge":
                return null;
            case "Perimeter tower":
                return null;
            case "SquareTower":
                return null;
            case "Armoury":
                if(government.getBuildingByNameForGoverment("َArmoury").equals(null)){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "Barrack":
                if(government.getBuildingByNameForGoverment("َBarrack").equals(null)){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "EngineerGuild":
                if(government.getBuildingByNameForGoverment("َEngineerGuild").equals(null)){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "KillingPit":
                return null;
            case "Hovel":
                return null;
            case "Church":
                return null;
            case "Cathedral":
                if(government.getBuildingByNameForGoverment("َCathedral").equals(null)){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "Armourer":
                if(government.getBuildingByNameForGoverment("Armourer").equals(null)){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "Blacksmith":
                return null;
            case "Fletcher":
                return null;
            case "Poleturner":
                return null;
            case "Tunnel":
                if(currentBlock.isThereIsTunnel())
                    return "there is already a tunnel!";
                currentBlock.setThereIsTunnel(true);
                return null;
            case "Stockpile":
                if(government.getBuildingByNameForGovernment("Stockpile").equals(null))
                    return null;
                else{
                    try {
                        if(EditMapMenu.map.getBlockByPosition(x,y+1).getThisBlockStructure().getBuildingType().getType().equals("Stockpile")) {
                            government.getResources().maximumCapacity+=500;
                            return null;
                        }
                        if(EditMapMenu.map.getBlockByPosition(x,y-1).getThisBlockStructure().getBuildingType().getType().equals("Stockpile")) {
                            government.getResources().maximumCapacity+=500;
                            return null;
                        }
                        if(EditMapMenu.map.getBlockByPosition(x+1,y).getThisBlockStructure().getBuildingType().getType().equals("Stockpile")) {
                            government.getResources().maximumCapacity+=500;
                            return null;
                        }
                        if(EditMapMenu.map.getBlockByPosition(x-1,y).getThisBlockStructure().getBuildingType().getType().equals("Stockpile")) {
                            government .getResources().maximumCapacity+=500;
                            return null;
                        }
                    }catch (Exception exception){
                    }
                    return "you have already a stockpile in your city.you should put the new stockpile near it!";
                }
            case "Quarry":
                if(!currentBlock.getType().equals("Stone"))
                    return "This Block type is not suitable for this structure!";
                resources.getResource("Stone").ProductionRate+=50;
                return null;

            case "PitchRig":
                if(!currentBlock.getType().equals("Meadow"))
                    return "This Block type is not suitable for this structure!";
                resources.getResource("Pitch").ProductionRate+=40;
                return null;
            case "Mill":
                resources.getResource("Wheat").ProductionRate-=20;
                resources.getResource("Flour").ProductionRate+=30;
                return null;
            case "Inn":
                resources.getResource("Wine").ProductionRate-=60;
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
                resources.getResource("Horse").ProductionRate+=10;
                return null;
            case "Store":
                if(government.getBuildingByNameForGoverment("َStore").equals(null)){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "OilSmelter":
                resources.getResource("Pitch").ProductionRate-=20;
                resources.getResource("Oil").ProductionRate+=15;
                return null;
            case "IronMine":
                if(!currentBlock.getType().equals("Iron"))
                    return "This Block type is not suitable for this structure!";
                resources.getResource("Iron").ProductionRate+=22;
                return null;
            case "WoodCutter":
                resources.getResource("Wood").ProductionRate+=resources.getResource("Wood").ProductionRate*25/100;
                return null;
            case "AppleFarm":
                if(!currentBlock.getType().equals("Dense Meadow"))
                    return "This Block type is not suitable for this structure!";
                resources.getResource("Apple").ProductionRate+=50;
                return null;
        }
        return null;
    }


    public static String checkMapPreparation() {
        if (EditMapMenu.number!= CreateNewGameMenu.capacity){
            return "You still haven't decided the headquarters of all the governments!";
        }
        return null;
    }
    /*public static String setTexture(Matcher matcher){
        x=Integer.parseInt(matcher.group("x"));
        y=Integer.parseInt(matcher.group("y"));
        Block currentBlock=EditMapMenu.map.getBlockByPosition(x,y);
        if(!currentBlock.getThisBlockStructure().equals(null))
            return "you can't change this block while there is a structure on it!";
        String type=matcher.group("type");
        switch (type){
            case "Dirt":
                currentBlock.setType("Dirt");
                return "Texture in "+x+" "+y+" set to Dirt";
            case "Gravel":
                currentBlock.setType("Gravel");
                return "Texture in "+x+" "+y+" set to Gravel";
            case "Stone":
                currentBlock.setType("Stone");
                return "Texture in "+x+" "+y+" set to Stone";
            case "Iron":
                currentBlock.setType("Iron");
                return "Texture in "+x+" "+y+" set to Iron";
            case "Grass":
                currentBlock.setType("Grass");
                return "Texture in "+x+" "+y+" set to Grass";
            case "Meadow":
                currentBlock.setType("Meadow");
                return "Texture in "+x+" "+y+" set to Meadow";
            case "Dense Meadow":
                currentBlock.setType("Dense Meadow");
                return "Texture in "+x+" "+y+" set to Dense Meadow";

        }
        return "Error! something went wrong...";
    }*/



}
