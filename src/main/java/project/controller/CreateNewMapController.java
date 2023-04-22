package project.controller;

import project.model.ApplicationManager;
import project.model.Block;
import project.model.Map;
import project.view.CreateNewMapMenu;
import project.view.Menu;

import java.util.Random;
import java.util.regex.Matcher;

public class CreateNewMapController {
    public static String setGovernment(Matcher matcher) {
        int number = CreateNewMapMenu.number;
        int capacity = CreateNewMapMenu.capacity;
        int size = CreateNewMapMenu.size;
        Map map = CreateNewMapMenu.map;
        int x=Integer.parseInt(matcher.group("x"));
        int y=Integer.parseInt(matcher.group("y"));
        if (number>capacity){
            return "The position of all governments has been set.";
        }
        if (x>size || y>size || x<1 || y<1){
            return "Error: Invalid position!";
        }
        if (map.getBlockByPosition(x,y).getGovernmentId()!=0){
            return "Error: This position has already been selected for another government!";
        }
        CreateNewMapMenu.number +=1;
        map.getBlockByPosition(x,y).setGovernmentId(number);
        map.addGovernmentBlock(map.getBlockByPosition(x,y));
        return "Government No. "+number+" position successfully set.";
    }

    public static String setMapName(Matcher matcher){
        String name = matcher.group("name");
        if (ApplicationManager.getMapByName(name)!=null){
            return "Error: The map is already exists!";
        }
        CreateNewMapMenu.map.setName(name);
        return "Map name set successfully.";
    }

    public static String dropRock(Matcher matcher){
        String x = matcher.group("x");
        String y = matcher.group("y");
        String direction = matcher.group("direction");
        int xNum = Integer.parseInt(x);
        int yNum = Integer.parseInt(y);
        Block block = CreateNewMapMenu.map.getBlockByPosition(xNum,yNum);
        if (block.getType()!=null || block.getThisBlockStructure()!=null || block.getPeoples()!=null){
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
    public static String dropTree(Matcher matcher){
        String x = matcher.group("x");
        String y = matcher.group("y");
        String type = matcher.group("type");
        int xNum = Integer.parseInt(x);
        int yNum = Integer.parseInt(y);
        Block block = CreateNewMapMenu.map.getBlockByPosition(xNum,yNum);
        if (block.getType()!=null || block.getThisBlockStructure()!=null || block.getPeoples()!=null){
            return "Error: You cannot place a tree in this block because the terrain is not suitable or it is full.";
        }
        String[] types = {"desert-shrub","cherry-palm","olive","coconut","date"};
        if (type.equals("random")){
            Random random = new Random();
            type = String.valueOf(types[random.nextInt()%5]);
        }
        block.setType("tree"+"|"+type);
        return "The tree was placed successfully.";
    }
    public static String dropUnit(){
        return "...";
    }

    public static String setTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        Block block = CreateNewMapMenu.map.getBlockByPosition(x,y);
        if (block.getThisBlockStructure()!=null || block.getPeoples()!=null){
            return "Error: You cannot change the gender of this block ("+x+","+y+") because it contains buildings or people.";
        }
        String[] types = {"land","gravel","boulder","stone","iron","grass","meadow","dense-meadow"};
        if (type.equals("random")){
            Random random = new Random();
            type = String.valueOf(types[random.nextInt()%8]);
        }
        block.setType(type);
        return "Block ("+x+","+y+") type has been changed successfully.";
    }

    public static String setTextureRectangle(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        String type = matcher.group("type");
        String input;
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <=y2 ; y++) {
                input = "settexture -x "+x+" -y "+y+" -t "+type;
                System.out.println(setTexture(Menu.getMatcher(input,Commands.SET_TEXTURE.getRegex())));
            }
        }
        return "Blocks that could be changed were changed and those that could not be changed remained intact.";

    }
}