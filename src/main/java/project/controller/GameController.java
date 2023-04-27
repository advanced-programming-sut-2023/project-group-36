package project.controller;

import project.model.Game;
import project.model.Government;
import project.view.GameMenu;

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

    public static String selectUnit(Matcher matcher){
        return "...";
    }
    public static String reSelectUnit(Matcher matcher){
        return "...";
    }

    public static String moveUnit(Matcher matcher){
        return "...";
    }
    public static String patrolUnit(Matcher matcher){
        return "...";
    }

    public static String stopUnit(Matcher matcher){
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
