package project.controller;

import project.model.ApplicationManager;
import project.model.Block;
import project.model.Map;
import project.model.Peoples.Militia;
import project.model.Peoples.People;
import project.view.MapMenu;
import project.view.Menu;

import java.util.regex.Matcher;

public class MapMenuController {
    public static String showMap(int x,int y){
        String res="";
        if(x<0 || y<0 || Math.max(x,y)>ApplicationManager.getCurrentGame().getMap().getSize()){
            return "Invalid cordinates!";
        }
        Block block;
        Map CurrentMap=ApplicationManager.getCurrentGame().getMap();
        String[][] mapToShow = new String[10][10];
        for(int i=-5;i<5;i++){
            z:
            for(int j=-5;j<5;j++){
                mapToShow[5+i][5+j]="";
               if(x+i<=0 || y+j<=0 || y+j>CurrentMap.getSize() || x+i>CurrentMap.getSize()){
                   mapToShow[5+i][5+j]="|N U L L";
                   continue z;
               }
                if (CurrentMap.getBlockByPosition(x+i,y+j).getThisBlockStructure()!= null)
                    mapToShow[5+i][5+j]+="|B ";
                else
                    mapToShow[5+i][5+j]+="|  ";
                if(CurrentMap.getBlockByPosition(x+i,y+j).getTree()!= null)
                    mapToShow[5+i][5+j]+="T ";
                else
                    mapToShow[5+i][5+j]+="  ";
                if (CurrentMap.getBlockByPosition(x+i,y+j).myEnemies(GameController.getCurrentGovernment()) != null)
                    mapToShow[5+i][5+j]+="E ";
                else
                    mapToShow[5+i][5+j]+="  ";
                if (CurrentMap.getBlockByPosition(x+i,y+j).getThisBlockStructure() != null){
                    if(CurrentMap.getBlockByPosition(x+i,y+j).getThisBlockStructure().getMilitias() != null)
                        mapToShow[5+i][5+j]+="S ";
                }
                else
                    mapToShow[5+i][5+j]+="  ";
                switch (CurrentMap.getBlockByPosition(x+i,y+j).getType()){
                    case "Dirt":
                        mapToShow[5+i][5+j]="\u001B[0;43m"+mapToShow[5+i][5+j]+"\u001B[0m";
                        break ;
                    case "Gravel":
                        mapToShow[5+i][5+j]="\u001B[0;47m"+mapToShow[5+i][5+j]+"\u001B[0m";
                        break ;
                    case "Boulder":
                        mapToShow[5+i][5+j]="\u001B[0;47m"+mapToShow[5+i][5+j]+"\u001B[0m";
                        break ;
                    case "Stone":
                        mapToShow[5+i][5+j]="\u001B[40m"+mapToShow[5+i][5+j]+"\u001B[0m";
                        break ;
                    case "Grass":
                        mapToShow[5+i][5+j]="\u001B[42m"+mapToShow[5+i][5+j]+"\u001B[0m";
                        break ;
                    case "Meadow":
                        mapToShow[5+i][5+j]="\u001B[42m"+mapToShow[5+i][5+j]+"\u001B[0m";
                        break ;
                    case "Dense Meadow":
                        mapToShow[5+i][5+j]="\u001B[42m"+mapToShow[5+i][5+j]+"\u001B[0m";
                        break ;
                    case "Sea":
                        mapToShow[5+i][5+j]="\u001B[44m"+mapToShow[5+i][5+j]+"\u001B[0m";
                        break ;
                    /*default:
                        mapToShow[5+i][5+j]="\u001B[0;43m"+mapToShow[5+i][5+j]+"\u001B[0m";
                        break ;*/
                }
            }
        }
        for(int i=-5;i<5;i++) {
            for (int j = -5; j < 5; j++) {
                res+=" "+mapToShow[5+i][5+j];
            }
            res+="\n\n";
        }
        return res;

    }
    public static String showDetails(int x,int y){
        String res="";
        if(x<0 || y<0){
            return "Invalid cordinates!";
        }
        Block block=ApplicationManager.getCurrentGame().getMap().getBlockByPosition(x,y);
        res+="| Biulding: ";
        if(block.getThisBlockStructure() != null)
            res+=block.getThisBlockStructure().getBuildingType().getType()+"\n";
        else
            res+="\n";
        res+="| Soldiers: \n";
        if(block.getPeoples() != null) {
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
         SeconderyMathcer=Menu.getMatcher(matcher.group("dir1"),"(?<direction>[a-z]+ (?<count>[0-9]*");
         try {
             if(SeconderyMathcer.group("dir1").equals("up"))
                 x-=Integer.parseInt(SeconderyMathcer.group("count"));
             else if(SeconderyMathcer.group("dir1").equals("down"))
                 x+=Integer.parseInt(SeconderyMathcer.group("count"));
             if(SeconderyMathcer.group("dir1").equals("right"))
                 y+=Integer.parseInt(SeconderyMathcer.group("count"));
             else if(SeconderyMathcer.group("dir1").equals("left"))
                 y-=Integer.parseInt(SeconderyMathcer.group("count"));
         }catch (NullPointerException exception){
             x=0;
             y=0;
         }
         MapMenu.x+=x;
         MapMenu.y+=y;
         try{
             SeconderyMathcer=Menu.getMatcher(matcher.group("dir2"),"(?<direction> (?<count>[0-9]*");
             if(SeconderyMathcer.group("dir1").equals("up"))
                 x-=Integer.parseInt(SeconderyMathcer.group("count"));
             else if(SeconderyMathcer.group("dir1").equals("down"))
                 x+=Integer.parseInt(SeconderyMathcer.group("count"));
             if(SeconderyMathcer.group("dir1").equals("right"))
                 y+=Integer.parseInt(SeconderyMathcer.group("count"));
             else if(SeconderyMathcer.group("dir1").equals("left"))
                 y-=Integer.parseInt(SeconderyMathcer.group("count"));
         }catch (NullPointerException exception){
             x=0;
             y=0;
         }
        MapMenu.x+=x;
        MapMenu.y+=y;
        return showMap(MapMenu.x,MapMenu.y);
    }
}
