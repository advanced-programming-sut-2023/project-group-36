package project.controller;

import project.model.Block;
import project.model.Game;
import project.model.Government;
import project.model.Peoples.Engineer;
import project.model.Peoples.Launcher;
import project.model.Peoples.People;
import project.model.Tools;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameController {

    private static Game game;
    private static Government currentGovernment;  ///?

    public static void setGame(Game game){
        GameController.game = game;
        currentGovernment = game.getCurrentGovernment();
    }
    public static String showPopularityFactors(Matcher matcher){
        return "...";
    }

    public static String showPopularity(Matcher matcher){
        return "...";
    }
    public static String showFood(Matcher matcher){
        return "...";
    }

    public static String foodRateSet(Matcher matcher){
        //int rate = Integer.parseInt(matcher.group("rate-number"));
        return "...";
    }
    public static String foodRateShow(Matcher matcher){
        return "...";
    }

    public static String taxRateSet(Matcher matcher){
        return "...";
    }

    public static String fearRateShow(Matcher matcher) {
        return "...";
    }

    public static String fearRateSet(Matcher matcher){
        return "...";
    }

    public static String taxRateShow(Matcher matcher){
        return "...";
    }

    public static String dropBuilding(Matcher matcher){
        return "...";
    }

    public static String selectBuilding(Matcher matcher){
        return "...";
    }
    public static String reSelectBuilding(Matcher matcher){
        return "...";
    }

    public static String createUnit(Matcher matcher){
        return "...";
    }
    public static String repair(Matcher matcher){
        return "...";
    }



    ///////////////////// ALI
    public static String selectUnit(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Block block = game.getMap().getBlockByPosition(x,y);
        if (block.getPeoples().size()==0){
            return "You have no units in this place!";
        }
        if (block.getPeoples().size()>1){
            return "You cannot select more than one unit!";
        }
        currentGovernment.setSelectedPeople(block.getPeoples().get(0));
        return "select!";
    }
    public static String unSelectUnit(Matcher matcher){
        if (currentGovernment.getSelectedPeople()==null){
            return "You have not selected any units!";
        }
        currentGovernment.setSelectedPeople(null);
        return "unSelect!";
    }

    public static String moveUnit(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Block block = game.getMap().getBlockByPosition(x,y);
        People selectedPeople = currentGovernment.getSelectedPeople();
        selectedPeople.startMove(block);

        return "...";
    }
    public static String patrolUnit(Matcher matcher){
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        People selectedPeople = currentGovernment.getSelectedPeople();
        return "...";
    }

    public static String stopUnit(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        People selectedPeople = currentGovernment.getSelectedPeople();
        selectedPeople.stop();
        return "...";
    }
    public static String setCondition(Matcher matcher){
        return "...";
    }

    public static String attackEnemy(Matcher matcher){
        return "...";
    }

    public static String attackLaunch(Matcher matcher){
        People selectedPeople = currentGovernment.getSelectedPeople();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (selectedPeople==null){
            return "No unit selected!";
        }
        if (!(selectedPeople instanceof Launcher)){
            return "The selected unit is not a launcher!";
        }
        if (((Launcher) selectedPeople).checkInRange(x,y)){
            return "The specified target is not in the selected unit's range!";
        }
        ((Launcher) selectedPeople).launch(game.getMap().getBlockByPosition(x,y));
        return "The launch to the requested position was successfully completed!";
    }

    public static String pourOil(Matcher matcher){
        People selectedPeople = currentGovernment.getSelectedPeople();
        String direction = matcher.group("direction");
        if (selectedPeople==null){
            return "Error: No unit selected!";
        }
        if (!(selectedPeople instanceof Engineer)){
            return "Error: The selected unit is not a engineer!";
        }
        String[] directions ={"r","l","s","n"};
        if (!Tools.validType(directions,direction)) {
            return "Error: Invalid Direction!";
        }
        ((Engineer) selectedPeople).pourOil(direction,game.getMap());
        return "Oil was successfully spilled.";
    }

    public static String digTunnel(Matcher matcher){

        return "...";
    }

    public static String disbandUnit(Matcher matcher){
        return "...";
    }

    ///////////////////// ALI

    public static String clearBlock(Matcher matcher){
        return "...";
    }


    public static void nextTurn() {
        game.nextTurn();
    }
}
