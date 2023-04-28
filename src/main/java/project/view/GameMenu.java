package project.view;

import project.controller.Commands;
import project.controller.CreateNewMapController;
import project.controller.GameController;
import project.model.Game;
import project.model.Map;

import java.util.Scanner;

public class GameMenu {
    private final static Scanner scanner = Menu.getScanner();

    public static void run(Game game){
        System.out.println("**<< Game Menu >>**");
        GameController.setGame(game);
        String input;
        String regex;
        boolean inThisMenu = true;
        while (inThisMenu) {
            input = scanner.nextLine();
            if (input.matches(regex = Commands.SHOW_POPULARITY_FACTORS.getRegex())){
                GameController.showPopularityFactors(Menu.getMatcher(input, regex));
            }
            else if (input.matches(regex = Commands.SHOW_POPULARITY.getRegex())){
                GameController.showPopularity(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.SHOW_FOOD.getRegex())){
                GameController.showFood(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.FOOD_RATE_SET.getRegex())){
                GameController.foodRateSet(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.FOOD_RATE_SHOW.getRegex())){
                GameController.foodRateShow(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.TAX_RATE_SET.getRegex())){
                GameController.taxRateSet(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.TAX_RATE_SHOW.getRegex())){
                GameController.taxRateShow(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.FEAR_RATE_SET.getRegex())){
                GameController.fearRateSet(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.FEAR_RATE_SHOW.getRegex())){
                GameController.fearRateShow(Menu.getMatcher(input,regex));
            }
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

            ///////////////////// ALI

            else if (input.matches(regex = Commands.SELECT_UNIT.getRegex())){
                GameController.selectUnit(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.UNSELECT_UNIT.getRegex())){
                GameController.unSelectUnit(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.MOVE_UNIT.getRegex())){
                GameController.moveUnit(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.PATROL_UNIT.getRegex())){
                GameController.patrolUnit(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.STOP_UNIT.getRegex())){
                GameController.stopUnit(Menu.getMatcher(input,regex));
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


