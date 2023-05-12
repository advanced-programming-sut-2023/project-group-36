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
        if (map.getBlockByPosition(x,y).getGovernmentId()!=0){
            return "Error: This position has already been selected for another government!";
        }
        if (EditMapMenu.colors.contains(color)){
            return "Error: This color already has selected!";
        }
        Block block = map.getBlockByPosition(x,y);
        block.setColor(color);
        Structure centralCastle = new Structure();
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
        if(! block.getThisBlockStructure().equals(null) )
            return "this block is already occiupied!";
        switch (buildingType.getType()){
            case "":
        }
        Structure structure=new Structure(block,government,new ArrayList<Militia>(),new ArrayList<NormalPeople>(),buildingType);
        block.setThisBlockStructure(structure);
        government.getStructures().add(structure);
        return null;
    }


    public static String checkMapPreparation() {
        if (EditMapMenu.number!= CreateNewGameMenu.capacity){
            return "You still haven't decided the headquarters of all the governments!";
        }
        return null;
    }



}
