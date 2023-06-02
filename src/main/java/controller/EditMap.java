package controller;

import model.*;
import model.Buildings.BuildingType;
import model.Buildings.Structure;
import model.Peoples.*;
import view.CreateNewGameMenu;
import view.EditMapMenu;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

public class EditMap {
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
        Structure centralCastle = new Structure(100);

        if(EditMapMenu.number>=users.size()){
            return "you have already selected all governments keeps!";
        }

        Government government = new Government(users.get(EditMapMenu.number),color, centralCastle);
        block.setThisBlockStructure(centralCastle);
        centralCastle.setBuildingType(Types.getBuildingTypeByType("Keep"));
        centralCastle.setGovernment(government);
        EditMapMenu.governments.add(government);
        EditMapMenu.number+=1;
        return "Government "+users.get(EditMapMenu.number-1).getUsername()+" , "+color+" position successfully set.";
    }



    public static String dropRock(Matcher matcher, Map map){
        String x = matcher.group("x");
        String y = matcher.group("y");
        String direction = matcher.group("direction");
        int xNum = Integer.parseInt(x);
        int yNum = Integer.parseInt(y);
        Block block = map.getBlockByPosition(xNum,yNum);
        if (block.getThisBlockStructure()!=null || block.getPeoples()!=null){
            return "Error: You cannot place a rock in this block because the terrain is not suitable or it is full.";
        }
        char[] directions = {'s','n','e','w'};
        if (direction.equals("random")){
            Random random = new Random();
            direction = String.valueOf(directions[random.nextInt()%4]);
        }
        block.setType("rock"+direction);
        return "The rock was placed successfully.";
    }
    public static String dropTree(Matcher matcher,Map map){
        String x = matcher.group("x");
        String y = matcher.group("y");
        String type = matcher.group("type");
        int xNum = Integer.parseInt(x);
        int yNum = Integer.parseInt(y);
        Block block = map.getBlockByPosition(xNum,yNum);
        if (block.getTree()!=null || block.getThisBlockStructure()!=null || block.getPeoples()!=null || !block.suitableTypeForTree()){
            return "Error: You cannot place a tree in this block because the terrain is not suitable or it is full.";
        }
        String[] types = {"desert-shrub","cherry-palm","olive","coconut","date"};
        if (type.equals("random")){
            Random random = new Random();
            type = types[random.nextInt() % 5];
        }
        block.setTree("tree"+"|"+type);
        return "The tree was placed successfully.";
    }

    public static String setTexture(Matcher matcher,Map map) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        Block block = map.getBlockByPosition(x,y);
        if (block.getThisBlockStructure()!=null || block.getPeoples().size()!=0){
            return "Error: You cannot change the gender of this block ("+x+","+y+") because it contains buildings or people.";
        }
        String[] types = {"Dirt","Gravel","Boulder","Stone","Iron","Grass","Meadow","Dense Meadow"};
        if (type.equals("random")){
            Random random = new Random();
            type = String.valueOf(types[random.nextInt()%8]);
        }
        block.setType(type);
        return "Block ("+x+","+y+") type has been changed successfully.";
    }








}
