package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.*;
import model.Buildings.BuildingType;
import model.Buildings.Structure;
import model.Peoples.*;
import view.GameMenuController;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameController {

    public static String inDropBuilding = null;

    public static String inDropUnit = null;

    private static Game game;
    public static Government currentGovernment;
    static int x,y;
    public static Block currentBlock;

    public static Government getCurrentGovernment() {
        return currentGovernment;
    }

    public static Structure currentStructure;
    public static void setGame(Game game){
        GameController.game = game;
        if(game.getCurrentGovernment() != null)
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
        int rate = Integer.parseInt(matcher.group("rateNumber"));

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
        int rate = Integer.parseInt(matcher.group("rateNumber"));

        if (rate < -3 || rate > 8)
            return "The tax rate should be between -3 and 8 !";

        if (currentGovernment.getCoins() == 0)
            return "You don't have any coin, so you can't change the tax rate!";

        currentGovernment.setTaxRate(rate);
        return "The tax rate has been changed successfully";
    }

    public static String taxRateShow(){
        return "Tax rate = " + currentGovernment.getTaxRate();
    }

    // Fear
    public static String fearRateSet(Matcher matcher){
        int rate = Integer.parseInt(matcher.group("rateNumber"));

        if (rate < -5 || rate > 5)
            return "The fear rate should be between -5 and 5 !";

        currentGovernment.setFearRate(rate);
        return "The fear rate has been changed successfully";
    }

    public static String fearRateShow() {
        return "Fear rate = " + currentGovernment.getFearRate();
    }



    // Mohammad
    public static String dropBuilding(String type, Block currentBlock){
        if (currentGovernment==null){
            return "Error: No government selected!";
        }
        BuildingType buildingType= Types.getBuildingTypeByType(type);
        if(buildingType == null)
            return "Invalid Building name!";
        if(currentBlock.getThisBlockStructure() != null)
            return "This Block has already been occupied by another structure!";
        if(checkBuildingPrerequisite(type) != null){
            return checkBuildingPrerequisite(type);
        }
        employment(buildingType);
        currentStructure=new Structure(currentBlock,currentGovernment,new ArrayList<Militia>(),new ArrayList<NormalPeople>(),buildingType);
        currentGovernment.getStructures().add(currentStructure);
        currentBlock.setThisBlockStructure(currentStructure);
        return "drop building is done successfully.";
    }
    public static String checkBuildingPrerequisite(String type) throws ArrayIndexOutOfBoundsException{
        if(checkForEnoughResources(Types.getBuildingTypeByType(type).getWoodCost(), Types.getBuildingTypeByType(type).getStoneCost(), Types.getBuildingTypeByType(type).getGoldCost()))
            return "you don't have enough resourses for building this structure!";
        if(!checkForEnoughWorkingPeople(type))
            return "you don't have enough free people for employing in this building!";
        Resources resources=currentGovernment.getResources();
        switch (type){
            case "SmallGateHouse":
                return null;
            case "Turret":
                return null;
            case "BigGateHouse":
                return null;
            case "CircleTower":
                return null;
            case "LookoutTower":
                return null;
            case "DrawBridge":
                return null;
            case "Perimeter tower":
                return null;
            case "SquareTower":
                return null;
            case "Armoury":
                if(currentGovernment.getBuildingByNameForGoverment("َArmoury") == null){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "Barrack":
                if(currentGovernment.getBuildingByNameForGoverment("َBarrack") == null){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "EngineerGuild":
                if(currentGovernment.getBuildingByNameForGoverment("َEngineerGuild") == null){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "KillingPit":
                return null;
            case "Hovel":
                return null;
            case "Church":
                return null;
            case "Cathedral":
                if(currentGovernment.getBuildingByNameForGoverment("َCathedral") == null){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "Armourer":
                if(currentGovernment.getBuildingByNameForGoverment("Armourer") == null){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "Blacksmith":
                return null;
            case "Fletcher":
                return null;
            case "Poleturner":
                return null;
            case "Tunnel":
                if(currentBlock.isThereIsTunnel())
                    return "there is already a tunnel!";
                currentBlock.setThereIsTunnel(true);
                return null;
            case "Stockpile":
                if(currentGovernment.getBuildingByNameForGovernment("Stockpile") == null)
                    return null;
                else{
                    try {
                        if(EditMap.map.getBlockByPosition(x,y+1).getThisBlockStructure().getBuildingType().getType().equals("Stockpile")) {
                           currentGovernment.getResources().maximumCapacity+=500;
                            return null;
                        }
                        if(EditMap.map.getBlockByPosition(x,y-1).getThisBlockStructure().getBuildingType().getType().equals("Stockpile")) {
                            currentGovernment.getResources().maximumCapacity+=500;
                            return null;
                        }
                        if(EditMap.map.getBlockByPosition(x+1,y).getThisBlockStructure().getBuildingType().getType().equals("Stockpile")) {
                            currentGovernment.getResources().maximumCapacity+=500;
                            return null;
                        }
                        if(EditMap.map.getBlockByPosition(x-1,y).getThisBlockStructure().getBuildingType().getType().equals("Stockpile")) {
                            currentGovernment.getResources().maximumCapacity+=500;
                            return null;
                        }
                    }catch (Exception exception){
                    }
                    return "you have already a stockpile in your city.you should put the new stockpile near it!";
                }
            case "Quarry":
                if(!currentBlock.getType().equals("Stone"))
                    return "This Block type is not suitable for this structure!";
                resources.getResource("Stone").ProductionRate+=50;
                return null;

            case "PitchRig":
                if(!currentBlock.getType().equals("Meadow"))
                    return "This Block type is not suitable for this structure!";
                resources.getResource("Pitch").ProductionRate+=40;
                return null;
            case "Mill":
                resources.getResource("Wheat").ProductionRate-=20;
                resources.getResource("Flour").ProductionRate+=30;
                return null;
            case "Inn":
                resources.getResource("Wine").ProductionRate-=60;
                return null;
            case "Ditch":
                return null;
            case "MercenaryPost":
                return null;
            case "PitchDitch":
                return null;
            case "CagedWarDogs":
                return null;
            case "SiegeTent":
                return null;
            case "Stable":
                resources.getResource("Horse").ProductionRate+=10;
                return null;
            case "Store":
                if(currentGovernment.getBuildingByNameForGoverment("َStore").equals(null)){
                    return null;
                }
                else{
                    return "you have already placed this building in your city!";
                }
            case "OilSmelter":
                resources.getResource("Pitch").ProductionRate-=20;
                resources.getResource("Oil").ProductionRate+=15;
                return null;
            case "IronMine":
                if(!currentBlock.getType().equals("Iron"))
                    return "This Block type is not suitable for this structure!";
                resources.getResource("Iron").ProductionRate+=22;
                return null;
            case "WoodCutter":
                resources.getResource("Wood").ProductionRate+=resources.getResource("Wood").ProductionRate*25/100;
                return null;
            case "AppleFarm":
                if(!currentBlock.getType().equals("Dense Meadow"))
                    return "This Block type is not suitable for this structure!";
                resources.getResource("Apple").ProductionRate+=50;
                return null;

        }
        return null;
    }
    public static boolean checkForEnoughWorkingPeople(String type){
        BuildingType buildingType= Types.getBuildingTypeByType(type);
        int d=buildingType.getRequiredPeopleToWork();
        if(d==0)
            return true;
        int count=0;
        for(People normalPeople:currentGovernment.getPeoples()){
            if(normalPeople instanceof NormalPeople && ((NormalPeople) normalPeople).isEmployed()==false){
                count++;
                if(d==count)
                    return true;
            }
        }
        return false;
    }
    public static void employment(BuildingType buildingType){
        int cou=buildingType.getRequiredPeopleToWork();
        int d=0;
        for(People normalPeople:currentGovernment.getPeoples()){
            if(normalPeople instanceof NormalPeople && ((NormalPeople) normalPeople).isEmployed()==false){
                ((NormalPeople) normalPeople).setEmployed(true);
                d++;
                if(d==cou)
                    return;
            }
        }
    }
    public static  boolean checkForEnoughResources(int Wood,int Stone,int gold){
        if(currentGovernment.getCoins()<gold)
            return true;
        if(currentGovernment.getAmountOfResource("Wood")<Wood || currentGovernment.getAmountOfResource("Stone")<Stone)
            return true;

        return false;
    }
    public static void CostPay(String BuildingType){
        BuildingType buildingType=Types.getBuildingTypeByType(BuildingType);
        int Wood=buildingType.getWoodCost();
        int Stone= buildingType.getStoneCost();
        int gold=buildingType.getGoldCost();
        currentGovernment.getResources().changeResourceAmount("Wood",Wood);
        currentGovernment.getResources().changeResourceAmount("Stone",Stone);
        currentGovernment.changeCoins((-1)*gold);
    }

    public static String selectBuilding(Matcher matcher){
         x=Integer.parseInt(matcher.group("x"));
         y=Integer.parseInt(matcher.group("y"));
        Block block;
        try {
            block=game.getMap().getBlockByPosition(x,y);
        }catch (Exception e){
            return "Invalid Cordinates!";
        }
        if(block.getThisBlockStructure()==null)
            return "there is no building!";
        currentStructure=block.getThisBlockStructure();
        if(!currentStructure.getGovernment().equals(currentGovernment))
            return "this building doesn't belong to you!";
        return "you have selected a building at "+x+" "+y+" :"+currentStructure.getBuildingType().getType();
    }

    public static String unSelectBuilding(Matcher matcher){
        if (currentStructure.equals(null))
             return "you haven't selected any building to be unselected!";
        currentStructure=null;
        return "you have unselected your working building succesfully!";
    }

    public static String repair(Matcher matcher){
        if (currentStructure.equals(null))
            return "you haven't selected any building yet!";
        if( currentBlock.myEnemies(currentGovernment) != null && currentBlock.myEnemies(currentGovernment).size()>0)
            return "you can't repair buildings while they are under enemy fire!";
        int requiredStone=10+currentStructure.getBuildingType().getStoneCost()/2;
        if(currentGovernment.getResources().getResource("Stone").getCount()>requiredStone)
            return "you don't have enough resources to repair this building!\nCost :"+10+currentStructure.getBuildingType().getStoneCost()/2
                    +"\nYour Stone Count :"+currentGovernment.getResources().getResource("Stone").getCount();
        currentGovernment.getResources().getResource("Stone").changeCount((-1)*requiredStone);
        currentStructure.setHP(500);
        return "building at "+currentBlock.getX()+" "+currentBlock.getY()+" repaired succesfully!\nCost :"+10+currentStructure.getBuildingType().getStoneCost()/2;
    }

    public static String clearBlock(Matcher matcher){
        currentBlock=ApplicationManager.getCurrentGame().getMap().getBlockByPosition(Integer.parseInt(matcher.group("x")),Integer.parseInt(matcher.group("y")));
        currentBlock.setType("Dirt");
        if((currentStructure=currentBlock.getThisBlockStructure()) != null){
            BuildingType buildingType=currentBlock.getThisBlockStructure().getBuildingType();
            Structure structure=currentGovernment.getBuildingByNameForGovernment(buildingType.getType());
            for(int i=0;i<currentGovernment.getStructures().size();i++){
                if(structure.getBuildingType().equals(currentGovernment.getStructures().get(i))){
                    currentGovernment.getStructures().remove(i);
                    break;
                }

            }
            switch (buildingType.getType()){
                case "Stockpile":
                    currentGovernment.getResources().maximumCapacity-=500;
                    break;
                case"Quarry":
                    currentGovernment.getResources().getResource("Stone").ProductionRate-=50;
                    break;
                case "PitchRig":
                    currentGovernment.getResources().getResource("Pitch").ProductionRate-=40;
                    break;
                case "Mill":
                    currentGovernment.getResources().getResource("Wheat").ProductionRate+=20;
                    currentGovernment.getResources().getResource("Flour").ProductionRate+=30;
                    break;
                case "Inn":
                    currentGovernment.getResources().getResource("Wine").ProductionRate+=60;
                    break;
                case "OilSmelter":
                    currentGovernment.getResources().getResource("Pitch").ProductionRate+=20;
                    currentGovernment.getResources().getResource("Oil").ProductionRate-=15;
                    break;
                case "WoodCutter":
                    currentGovernment.getResources().getResource("Wood").ProductionRate-=currentGovernment.getResources().getResource("Wood").ProductionRate*25/100;
                    break;
                case "IronMine":
                    currentGovernment.getResources().getResource("Iron").ProductionRate-=22;
                    break;
                case "AppleFarm":
                    currentGovernment.getResources().getResource("Apple").ProductionRate-=50;
                    break;
            }
        }
        return "block in "+x+" "+y+" is now cleaned.";
    }

    // Mohammad



    ///////////////////// ALI

    public static String createUnit(String type, int count){
        PeopleType peopleType = Types.getPeopleTypeByType(type);
        if (currentStructure==null || !currentStructure.getName().equals(peopleType.requiredStructure)){
            return "Error: An appropriate building has not been selected!";
        }
        People people;
        for (int i = 0; i < count; i++) {
            Block block = currentStructure.getBlock();
            if (peopleType.category.equals("launchers")){
                people = new Launcher(peopleType,currentGovernment,block);
            }
            else if (peopleType.category.equals("fightingForce")){
                people = new FightingForce(peopleType,currentGovernment,block);
            }
            else {
                people = new NormalPeople(peopleType,currentGovernment,block);
            }
            currentGovernment.addPeople(people);
            block.addPeople(people);
        }
        return "The soldier(s) has been successfully created!";
    }

    public static String selectUnit(String type){
        ArrayList<People> selectedPeoples = new ArrayList<>();
        Block block = currentBlock;
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
        int x=Integer.parseInt(matcher.group("x"));
        int y=Integer.parseInt(matcher.group("y"));
        currentBlock=ApplicationManager.getCurrentGame().getMap().getBlockByPosition(x,y);
        if(currentBlock.isThereIsTunnel())
            return "You can't dig a tunnel here!";
        currentBlock.setThereIsTunnel(true);
        return "you digged a tunnel at "+x+" "+y;
    }

    public static String disbandUnit(Matcher matcher){
        return "...";
    }


    public static EventHandler<? super MouseEvent> nextTurn() {
        game.nextTurn();
        currentStructure = null;
        System.out.println(currentGovernment.getOwner().getUsername()+" playing!");
        return null;
    }


    ///////////////////// ALI


    public static Game getGame() {
        return game;
    }

    public static void endGame() {

    }

    public static void setCurrentGovernment(Government currentGovernment) {
        GameController.currentGovernment = currentGovernment;
    }
}
