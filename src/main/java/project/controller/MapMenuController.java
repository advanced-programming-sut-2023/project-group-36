package project.controller;

import project.model.ApplicationManager;
import project.model.Block;
import project.model.Game;
import project.model.Map;
import project.model.Peoples.Militia;
import project.model.Peoples.People;
import project.view.GameMenu;
import project.view.MapMenu;
import project.view.Menu;

import java.util.regex.Matcher;

public class MapMenuController {
    public static String showMap(int x,int y){
        String res="";
        if(x<0 || y<0){
            return "Invalid cordinates!";
        }
        Block block;
        Map CurrentMap=ApplicationManager.getCurrentGame().getMap();
        for(int i=Math.max(0,x-2);i<x+3;i++){
            for(int j=Math.max(0,y-2);j<y+3;j++){
                block=CurrentMap.getBlockByPosition(i,j);
                res+="----------\n";
                res+="| Type: "+block.getType()+"\n";
                res+="|  ";
                if(block.getTree() != null)
                    res+="T";
                res+="\n";
                res+="|  ";
                if(block.getThisBlockStructure() != null){
                    if(block.getThisBlockStructure().getBuildingType().category.equals("Castle Buildings"))
                        res+="W";
                    else
                        res+="B";
                }
                res+="\n";
                res+="|  ";

            }
            res+="\n";
        }
        return res;
    }
    public static String showDetails(int x,int y){
        String res="";
        if(x<0 || y<0){
            return "Invalid cordinates!";
        }
        Block block=ApplicationManager.getCurrentGame().getMap().getBlockByPosition(x,y);
        res+="| Biulding: "+block.getThisBlockStructure().name+"\n";
        res+="| Soldiers: \n";
        if(block.getPeoples().size()>0) {
            for (People selected : block.getPeoples()) {
                if (selected instanceof Militia) {
                    res += "| " + selected.getPeopleType().type + " " + selected.isInMove() + "\n";
                    res += "| " + selected.getGovernment().getOwner().getNickname() + "\n";
                }
            }
        }
        if(block.getTree() != null)
            res+="|   There is a Tree\n";

        return res;
    }
    public static String newCordinates(Matcher matcher){
        int x=0,y=0;
        Matcher SeconderyMathcer;
         SeconderyMathcer=Menu.getMatcher(matcher.group("dir1"),"(?<direction> (?<count>[0-9]*");
         if(SeconderyMathcer.group("dir1").equals("up"))
             x-=Integer.parseInt(SeconderyMathcer.group("count"));
        else if(SeconderyMathcer.group("dir1").equals("down"))
            x+=Integer.parseInt(SeconderyMathcer.group("count"));
        if(SeconderyMathcer.group("dir1").equals("right"))
            y+=Integer.parseInt(SeconderyMathcer.group("count"));
        else if(SeconderyMathcer.group("dir1").equals("left"))
            y-=Integer.parseInt(SeconderyMathcer.group("count"));
        SeconderyMathcer=Menu.getMatcher(matcher.group("dir2"),"(?<direction> (?<count>[0-9]*");
        if(SeconderyMathcer.group("dir1").equals("up"))
            x-=Integer.parseInt(SeconderyMathcer.group("count"));
        else if(SeconderyMathcer.group("dir1").equals("down"))
            x+=Integer.parseInt(SeconderyMathcer.group("count"));
        if(SeconderyMathcer.group("dir1").equals("right"))
            y+=Integer.parseInt(SeconderyMathcer.group("count"));
        else if(SeconderyMathcer.group("dir1").equals("left"))
            y-=Integer.parseInt(SeconderyMathcer.group("count"));
        return showMap(x+ MapMenu.x,y+MapMenu.y);
    }
}
