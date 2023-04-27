package project.controller;

import project.model.Block;
import project.model.Game;
import project.model.Government;
import project.model.Peoples.People;
import project.view.GameMenu;

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
        for (int i = 0; i < block.getPeoples().size(); i++) {
            block.getPeoples().get(i).select();
        }
        return "select!";
    }
    public static String reSelectUnit(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Block block = game.getMap().getBlockByPosition(x,y);
        for (int i = 0; i < block.getPeoples().size(); i++) {
            block.getPeoples().get(i).reSelect();
        }
        return "reSelect!";
    }

    public static String moveUnit(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Block block = game.getMap().getBlockByPosition(x,y);
        ArrayList<People> selectedPeoples = game.getCurrentGovernment().getSelectedPeoples();
        for (People selectedPeople : selectedPeoples) {
            selectedPeople.startMove(block);
        }
        return "...";
    }
    public static String patrolUnit(Matcher matcher){
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        ArrayList<People> selectedPeoples = game.getCurrentGovernment().getSelectedPeoples();
        for (People selectedPeople : selectedPeoples) {
            //....
        }

        return "...";
    }

    public static String stopUnit(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        ArrayList<People> selectedPeoples = game.getCurrentGovernment().getSelectedPeoples();
        for (People selectedPeople : selectedPeoples) {
            selectedPeople.stop();
        }
        return "...";
    }
    public static String setCondition(Matcher matcher){
        return "...";
    }

    public static String attackEnemy(Matcher matcher){
        return "...";
    }
    public static String attackLaunch(Matcher matcher){
        return "...";
    }

    public static String pourOil(Matcher matcher){
        return "...";
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


    public static String mainMenu(Matcher matcher){
        return "...";
    }

    public static String tradeMenu(Matcher matcher){
        return "...";
    }

    public static String shopMenu(Matcher matcher){
        return "...";
    }


    public static String fearRateShow(Matcher matcher) {
        return "...";
    }

    public static void nextTurn() {
        game.nextTurn();
    }
}
