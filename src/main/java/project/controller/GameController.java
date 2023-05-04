package project.controller;

import project.model.Block;
import project.model.Game;
import project.model.Government;
import project.model.Peoples.Engineer;
import project.model.Peoples.FightingForce;
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
    public static String showPopularityFactors(){
        return "Food" + "\n" + "Tax" + "\n" + "Religion" + "\n" + "Fear";
    }

    public static int showPopularity(){
        return currentGovernment.getPopularity();
    }

    // Food
    public static String showFoodList(){
        String result; //1: bread, 2: rice, 3: apple, 4: meat

        result =  "Bread: " + currentGovernment.getAmountOfFoods("bread") + "\n";

        result += "Rice: "  + currentGovernment.getAmountOfFoods("rice")  + "\n";

        result += "Apple: " + currentGovernment.getAmountOfFoods("apple") + "\n";

        result += "meat: "  + currentGovernment.getAmountOfFoods("meat");

        return result;
    }

    public static String feedRateSet(Matcher matcher){
        int rate = Integer.parseInt(matcher.group("rate-number"));

        if (rate < -2 || rate > 2)
            return "The feed rate should be between -2 and 2 !";

        if (currentGovernment.amountOfFoods() == 0)
            return "You don't have any food, so you can't change the feed rate!";

        currentGovernment.setFeedRate(rate);
        return "The feed rate has been changed successfully";
    }

    public static String feedRateShow(){
        return "Feed rate = " + currentGovernment.getFeedRate();
    }

    // Tax
    public static String taxRateSet(Matcher matcher){
        int rate = Integer.parseInt(matcher.group("rate-number"));

        if (rate < -3 || rate > 8)
            return "The tax rate should be between -3 and 8 !";

        if (currentGovernment.getCoins() == 0)
            return "You don't have any coin, so you can't change the tax rate!";

        currentGovernment.setTaxRate(rate);
        return "The tax rate has been changed successfully";
    }

    public static String taxRateShow(){
        return "Fear rate = " + currentGovernment.getTaxRate();
    }

    // Fear
    public static String fearRateSet(Matcher matcher){
        int rate = Integer.parseInt(matcher.group("rate-number"));

        if (rate < -5 || rate > 5)
            return "The fear rate should be between -5 and 5 !";

        currentGovernment.setFearRate(rate);
        return "The fear rate has been changed successfully";
    }

    public static String fearRateShow() {
        return "Fear rate = " + currentGovernment.getFearRate();
    }



    // Mohammad
    public static String dropBuilding(Matcher matcher){
        return "...";
    }

    public static String selectBuilding(Matcher matcher){
        return "...";
    }
    public static String reSelectBuilding(Matcher matcher){
        return "...";
    }

    public static String repair(Matcher matcher){
        return "...";
    }

    public static String clearBlock(Matcher matcher){
        return "...";
    }


    // Mohammad



    ///////////////////// ALI

    public static String createUnit(Matcher matcher){
        return "...";
    }

    public static String selectUnit(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        ArrayList<People> selectedPeoples = new ArrayList<>();
        Block block = game.getMap().getBlockByPosition(x,y);
        People people;
        for (int i = 0; i < block.getPeoples().size(); i++) {
            people = block.getPeoples().get(i);
            if (people.getGovernment().equals(currentGovernment) && people.getPeopleType().type.equals(type)){
                selectedPeoples.add(people);
            }
        }
        if (selectedPeoples.size()==0){
            return "You have no units with this type in this place!";
        }
        currentGovernment.setSelectedPeoples(selectedPeoples);
        return "selected!";
    }
    public static String unSelectUnit(){
        if (currentGovernment.getSelectedPeoples()==null){
            return "You have not selected any units!";
        }
        currentGovernment.setSelectedPeoples(null);
        return "unSelect!";
    }

    public static String moveUnit(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Block block;
        if ((block = game.getMap().getBlockByPosition(x,y))== null){
            return "Error: Invalid position!";
        }
        ArrayList<People> selectedPeoples = currentGovernment.getSelectedPeoples();
        if (selectedPeoples.size()==0){
            return "No unit selected!";
        }
        for (People selectedPeople : selectedPeoples) {
            selectedPeople.startMove(block);
        }
        return "OK!"; //...
    }
    public static String patrolUnit(Matcher matcher){
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        ArrayList<People> selectedPeoples = currentGovernment.getSelectedPeoples();
        Block block1 , block2;
        if ((block1 = game.getMap().getBlockByPosition(x1,y1))== null || (block2 = game.getMap().getBlockByPosition(x2,y2))== null){
            return "Error: Invalid position!";
        }
        if (selectedPeoples==null){
            return "No unit selected!";
        }
        for (People selectedPeople : selectedPeoples) {
            selectedPeople.startPatrol(block1, block2);
        }
        return "OK!"; //...
    }

    public static String stopUnit(){
        ArrayList<People> selectedPeoples = currentGovernment.getSelectedPeoples();
        if (selectedPeoples==null || selectedPeoples.size()==0){
            return "No unit selected!";
        }
        for (People selectedPeople : selectedPeoples) {
            selectedPeople.stop();
        }
        return "OK!";
    }
    public static String setCondition(Matcher matcher){
        return "...";
    }

    public static String attackEnemy(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Block block;
        if ((block = game.getMap().getBlockByPosition(x,y))== null){
            return "Error: Invalid position!";
        }
        ArrayList<People> selectedPeoples = currentGovernment.getSelectedPeoples();
        for (People selectedPeople : selectedPeoples) {
            if (!selectedPeople.getPeopleType().category.equals("fightingForce")) {
                return "Error: The unit you have selected isn't a fightingForce!";
            }
            ((FightingForce) selectedPeople).attack(block);
        }
        return "OK!";
    }

    public static String attackLaunch(Matcher matcher){
        ArrayList<People> selectedPeoples = currentGovernment.getSelectedPeoples();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (selectedPeoples==null){
            return "No unit selected!";
        }
        for (People selectedPeople : selectedPeoples) {
            if (!(selectedPeople instanceof Launcher)) {
                return "The selected unit is not a launcher!";
            }
            if (((Launcher) selectedPeople).checkInRange(x, y)) {
                return "The specified target is not in the selected unit's range!";
            }
            ((Launcher) selectedPeople).launch(game.getMap().getBlockByPosition(x, y));
        }
        return "The launch to the requested position was successfully completed!";
    }

    public static String pourOil(Matcher matcher){
        ArrayList<People> selectedPeoples = currentGovernment.getSelectedPeoples();
        String direction = matcher.group("direction");
        if (selectedPeoples==null){
            return "Error: No unit selected!";
        }
        for (People selectedPeople : selectedPeoples) {
            if (!(selectedPeople instanceof Engineer)) {
                return "Error: The selected unit is not a engineer!";
            }
            String[] directions = {"r", "l", "s", "n"};
            if (!Tools.validType(directions, direction)) {
                return "Error: Invalid Direction!";
            }
            ((Engineer) selectedPeople).pourOil(direction, game.getMap());
        }
        return "Oil was successfully spilled.";
    }

    public static String digTunnel(Matcher matcher){

        return "...";
    }

    public static String disbandUnit(Matcher matcher){
        return "...";
    }


    public static void nextTurn() {
        game.nextTurn();
    }


    ///////////////////// ALI



    public static Game getGame() {
        return game;
    }

    public static void endGame() {

    }
}
