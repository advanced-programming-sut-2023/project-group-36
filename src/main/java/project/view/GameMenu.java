package project.view;

import project.controller.Commands;
import project.controller.GameController;
import project.model.ApplicationManager;
import project.model.Game;
import project.model.Tools;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private final static Scanner scanner = Menu.getScanner();
    public static void run(Game game) throws NoSuchAlgorithmException, InterruptedException {
        System.out.println("**<< Game Menu >>**");
        GameController.setGame(game);
        Matcher matcher;
        String input;
        String regex;
        boolean inThisMenu = true;
        System.out.println(game.getCurrentGovernment().getOwner().getUsername()+" playing!");
        while (inThisMenu) {

            input = scanner.nextLine();
            if (Tools.inputCheckFormat(input)!=null){
                input = Tools.inputCheckFormat(input);
            }

            /////////////// Amir mohammad

            if (input.matches(regex = Commands.SHOW_POPULARITY_FACTORS.getRegex())){
                System.out.println(GameController.showPopularityFactors());
            }
            else if (input.matches(regex = Commands.SHOW_POPULARITY.getRegex())){
                System.out.println(GameController.showPopularity());
            }
            else if (input.matches(regex = Commands.SHOW_FOOD_LIST.getRegex())){
                System.out.println(GameController.showFoodList());
            }
            else if (input.matches(regex = Commands.FOOD_RATE_SET.getRegex())){
                matcher = Menu.getMatcher(input,regex);
                System.out.println(GameController.feedRateSet(matcher));
            }
            else if (input.matches(regex = Commands.FOOD_RATE_SHOW.getRegex())){
                System.out.println(GameController.feedRateShow());
            }
            else if (input.matches(regex = Commands.TAX_RATE_SET.getRegex())){
                matcher = Menu.getMatcher(input,regex);
                System.out.println(GameController.taxRateSet(matcher));
            }
            else if (input.matches(regex = Commands.TAX_RATE_SHOW.getRegex())){
                System.out.println(GameController.taxRateShow());
            }
            else if (input.matches(regex = Commands.FEAR_RATE_SET.getRegex())){
                matcher = Menu.getMatcher(input,regex);
                System.out.println(GameController.fearRateSet(matcher));
            }
            else if (input.matches(regex = Commands.FEAR_RATE_SHOW.getRegex())){
                System.out.println(GameController.fearRateShow());
            }

            /////////////// Amir Mohammad

            ////////////// Mohammad

            else if ((matcher=Menu.getMatcher(input,regex = Commands.DROP_BUILDING.getRegex())) != null){
                System.out.println(matcher.group("type"));
                System.out.println(GameController.dropBuilding(matcher));
            }
            else if (input.matches(regex = Commands.SELECT_BUILDING.getRegex())){
                System.out.println(GameController.selectBuilding(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.UNSELECT_BUILDING.getRegex())){
                System.out.println(GameController.unSelectBuilding(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.REPAIR.getRegex())){
                System.out.println(GameController.repair(Menu.getMatcher(input,regex)));
            }

            ////////////// Mohammad

            ///////////////////// ALI

            else if (input.matches(regex = Commands.CREATE_UNIT.getRegex())){
                System.out.println(GameController.createUnit(Menu.getMatcher(input,regex)));
            }

            else if (input.matches(regex = Commands.SELECT_UNIT.getRegex())){
                System.out.println(GameController.selectUnit(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.UNSELECT_UNIT.getRegex())){
                System.out.println(GameController.unSelectUnit());
            }
            else if (input.matches(regex = Commands.MOVE_UNIT.getRegex())){
                System.out.println(GameController.moveUnit(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.PATROL_UNIT.getRegex())){
                System.out.println(GameController.patrolUnit(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.STOP_UNIT.getRegex())){
                System.out.println( GameController.stopUnit());
            }
            else if (input.matches(regex = Commands.SET_CONDITION.getRegex())){
                System.out.println(GameController.setCondition(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.ATTACK_ENEMY.getRegex())){
                System.out.println(GameController.attackEnemy(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.ATTACK_LAUNCH.getRegex())){
                GameController.attackLaunch(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.POUR_OIL.getRegex())){
                System.out.println(GameController.pourOil(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.DIG_TUNNEL.getRegex())){
                System.out.println(GameController.digTunnel(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.DISBAND_UNIT.getRegex())){
                System.out.println(GameController.disbandUnit(Menu.getMatcher(input,regex)));
            }

            ///////////////////// ALI

            else if (input.matches(regex = Commands.CLEAR_BLOCK.getRegex())){
                System.out.println(GameController.clearBlock(Menu.getMatcher(input,regex)));
            }
            else if (input.matches("trade menu")){
                inThisMenu = false;
                TradeMenu.run();
            }
            else if (input.matches("shop menu")){
                inThisMenu = false;
                ShopMenu.run();
            }
            else if (input.matches("main menu")){
                inThisMenu = false;
                MainMenu.run();
            }
            else if (input.matches("map menu")){
                inThisMenu = false;
                MapMenu.run();
            }
            else if (input.matches("next turn")){
                GameController.nextTurn();
            }
            else if (input.matches(Commands.QUIT_GAME.getRegex())) {
                ApplicationManager.exit();
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}


