package controller;

import model.ApplicationManager;
import model.Block;
import model.Map;
import model.Peoples.Militia;
import model.Peoples.NormalPeople;
import model.Peoples.People;

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
                    mapToShow[5+i][5+j]+="|O ";
                if(CurrentMap.getBlockByPosition(x+i,y+j).getTree()!= null)
                    mapToShow[5+i][5+j]+="T ";
                else
                    mapToShow[5+i][5+j]+="O ";
                if (CurrentMap.getBlockByPosition(x+i,y+j).myEnemies(GameController.getCurrentGovernment()) != null && CurrentMap.getBlockByPosition(x+i,y+j).myEnemies(GameController.getCurrentGovernment()).size()>0 )
                    mapToShow[5+i][5+j]+="E ";
                else
                    mapToShow[5+i][5+j]+="O ";
                if (CurrentMap.getBlockByPosition(x+i,y+j).getThisBlockStructure() != null && CurrentMap.getBlockByPosition(x+i,y+j).getThisBlockStructure().getMilitias().size() >0)
                        mapToShow[5+i][5+j]+="S ";
                else
                    mapToShow[5+i][5+j]+="O ";

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
        if(x<0 || y<0 || x>ApplicationManager.getCurrentGame().getMap().getSize()|| y>ApplicationManager.getCurrentGame().getMap().getSize()){
            return "Invalid cordinates!";
        }
        Block block=ApplicationManager.getCurrentGame().getMap().getBlockByPosition(x,y);
        res+="| "+block.getType()+"\n";
        res+="| Biulding: ";
        if(block.getThisBlockStructure() != null)
            res+=block.getThisBlockStructure().getBuildingType().getType()+"\n";
        else
            res+="\n";
        res+="| Soldiers: \n";
        if(block.getPeoples() != null) {
            for (People selected : block.getPeoples()) {
                if (selected instanceof Militia || selected instanceof NormalPeople) {
                    res += "| " + selected.getPeopleType().type + " " + selected.isInMove() + "\n";
                    res += "| " + selected.getGovernment().getOwner().getUsername() + "\n";
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
         SeconderyMathcer=matcher;
         try {
             if(SeconderyMathcer.group("dir1").equals("up"))
                 y-=Integer.parseInt(SeconderyMathcer.group("a"));
             else if(SeconderyMathcer.group("dir1").equals("down"))
                 y+=Integer.parseInt(SeconderyMathcer.group("a"));
             if(SeconderyMathcer.group("dir1").equals("right"))
                 x+=Integer.parseInt(SeconderyMathcer.group("a"));
             else if(SeconderyMathcer.group("dir1").equals("left"))
                 x-=Integer.parseInt(SeconderyMathcer.group("a"));
         }catch (NullPointerException exception){
             x=0;
             y=0;
         }
         //MapMenu.x+=x;
         //MapMenu.y+=y;
         try{
             SeconderyMathcer=matcher;
             if(SeconderyMathcer.group("dir2").equals("up"))
                 y-=Integer.parseInt(SeconderyMathcer.group("b"));
             else if(SeconderyMathcer.group("dir2").equals("down"))
                 y+=Integer.parseInt(SeconderyMathcer.group("b"));
             if(SeconderyMathcer.group("dir2").equals("right"))
                 x+=Integer.parseInt(SeconderyMathcer.group("b"));
             else if(SeconderyMathcer.group("dir2").equals("left"))
                 x-=Integer.parseInt(SeconderyMathcer.group("b"));
         }catch (NullPointerException exception){
             x=0;
             y=0;
         }
        //MapMenu.x+=x;
        //MapMenu.y+=y;
        //return showMap(MapMenu.x,MapMenu.y);
        return null;
    }
}
