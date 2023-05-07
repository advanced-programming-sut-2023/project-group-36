package project.view;

import project.controller.Commands;
import project.controller.GameController;
import project.model.Game;
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
        while (inThisMenu) {

            /////////////// Amir mohammad
            input = scanner.nextLine();
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

            else if (input.matches(regex = Commands.DROP_BUILDING.getRegex())){
                GameController.dropBuilding(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.SELECT_BUILDING.getRegex())){
                GameController.selectBuilding(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.UNSELECT_BUILDING.getRegex())){
                GameController.reSelectBuilding(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.CREATE_UNIT.getRegex())){
                GameController.createUnit(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.REPAIR.getRegex())){
                GameController.repair(Menu.getMatcher(input,regex));
            }

            ////////////// Mohammad

            ///////////////////// ALI

            else if (input.matches(regex = Commands.SELECT_UNIT.getRegex())){
                GameController.selectUnit(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.UNSELECT_UNIT.getRegex())){
                GameController.unSelectUnit();
            }
            else if (input.matches(regex = Commands.MOVE_UNIT.getRegex())){
                GameController.moveUnit(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.PATROL_UNIT.getRegex())){
                GameController.patrolUnit(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.STOP_UNIT.getRegex())){
                GameController.stopUnit();
            }
            else if (input.matches(regex = Commands.SET_CONDITION.getRegex())){
                GameController.setCondition(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.ATTACK_ENEMY.getRegex())){
                GameController.attackEnemy(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.ATTACK_LAUNCH.getRegex())){
                GameController.attackLaunch(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.POUR_OIL.getRegex())){
                GameController.pourOil(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.DIG_TUNNEL.getRegex())){
                GameController.digTunnel(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.DISBAND_UNIT.getRegex())){
                GameController.disbandUnit(Menu.getMatcher(input,regex));
            }

            ///////////////////// ALI

            else if (input.matches(regex = Commands.CLEAR_BLOCK.getRegex())){
                GameController.clearBlock(Menu.getMatcher(input,regex));
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
            else if (input.matches("next turn")){
                GameController.nextTurn();
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}


