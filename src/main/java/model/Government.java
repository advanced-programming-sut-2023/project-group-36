package model;

import model.Peoples.NormalPeople;
import model.Peoples.People;
import model.Peoples.PeopleType;

import java.util.ArrayList;

public class Government {
 /*   private final Structure centralCastle;
    private final User owner;
    private ArrayList<People> peoples = new ArrayList<>();

    private ArrayList<Structure> structures = new ArrayList<>();

    public ArrayList<Structure> getStructures() {
        return structures;
    }

 //   private ArrayList<TradeMessage> tradeMessages = new ArrayList<>();
    private ArrayList<Trade> trades = new ArrayList<>();
    private String color;
    private Resources resources;
    private final String[] foodType;
    private int[]  foodAmount;

    private int popularity;
    private int taxRate;
    private int fearRate;
    private int feedRate;
    private int coins;
    private ArrayList<People> selectedPeoples = new ArrayList<>();


    public Government(User user, String color, Structure centralCastle){
        this.owner = user;
        this.coins = 1000;
        this.color = color;
        this.centralCastle = centralCastle;
        foodType = new String[]{"bread", "meat", "apple", "rice"};
        resources = new Resources();
        foodAmount = new int[]{10, 10, 10, 10};
    }


    public void addTrade(Trade trade){
        trades.add(trade);
    }

    public void addTradeMessage(TradeMessage tradeMessage){
        tradeMessages.add(tradeMessage);
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<People> getPeoples() {
        return peoples;
    }

    public void addPeople(People people){
        peoples.add(people);
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public ArrayList<TradeMessage> getTradeMessages() {
        return tradeMessages;
    }

    public int getPopularity() {
        return popularity;
    }

    public void changePopularity(int amount) {
        popularity += amount;
    }

    public void delShowedMessages(){
        for (int i = 0; i < tradeMessages.size(); i++) {
            if (tradeMessages.get(i).showCondition()){
                tradeMessages.remove(tradeMessages.get(i));
                i--;
            }
        }
    }

    public boolean checkGameOver(){
        return centralCastle.getHitPoint() <= 0;
    }

    public void nextTurn(){
        // food & ...
        getResources().changeOfResourcesAtTheEndOfTurn();
        // Feed +
        checkTheFoodFactor();

        // Tax +
        //initialization for taxRate = 0
        checkTheTaxFactor();

        // Religion +
        checkTheReligionFactor();

        // Fear +
        checkTheFearFactor();

        // change population
        changePopulation();

        // game over
        if (checkGameOver()){
            Game game = GameController.getGame();
            game.removeGovernment(this);
            User user = this.getOwner();
            user.addScore(game.getScore());

        }
    }




    public int getAmountOfResource(String type){
        return resources.getResourceAmount(type);
    }
    public void changeAmountOfResource(String type, int amount){
        resources.changeResourceAmount(type,amount);
    }

    public int getAmountOfFoods(String type){
        return foodAmount[getIndexOfFood(type)];
    }

    public void changeAmountOfFoods(String type, int amount){
        foodAmount[getIndexOfFood(type)]+=amount;
    }

    // Food
    public void setFeedRate(int foodRate) { this.feedRate = foodRate; }

    public int amountOfFoods() {
        int amount = 0;
        for (int i : foodAmount) {
            amount += i;
        }

        return amount;
    }
    public int getFeedRate() { return feedRate; }

    // Tax
    public void setTaxRate(int taxRate) { this.taxRate = taxRate; }

    public int getTaxRate() {
        return taxRate;
    }
    public int getCoins() {
        return coins;
    }

    // Fear
    public int getFearRate() { return fearRate; }
    public void setFearRate(int fearRate) { this.fearRate = fearRate; }

    public void changeCoins(int count) {
        coins+=count;
    }


    public void setSelectedPeoples(ArrayList<People> peoples) {
        selectedPeoples = peoples;
    }

    public ArrayList<People> getSelectedPeoples() {
        return selectedPeoples;
    }


    public Resources getResources() {
        return resources;
    }

    public void removePeople(People people) {
        peoples.remove(people);
    }






    //next turn function
    //food
    private void checkTheFoodFactor() {
        changePopularity(getAmountOfAllTypesOfFoods() - 1);

        double foodForEachPerson = (double) feedRate / 2 + 1;
        int foodsToBeConsuming = (int) (foodForEachPerson * peoples.size() + 1); // +1 is to be int

        if (amountOfFoods() <= foodsToBeConsuming) {
            Arrays.fill(foodAmount, 0);
            feedRate = -2;
        }
        else {
            int eachFoodToBeConsuming = foodsToBeConsuming / 4;
            for (int i = 0; i < foodAmount.length; i++) {
                foodAmount[i] -= eachFoodToBeConsuming;
            }
            while (foodsToBeConsuming % 4 != 0) {
                foodAmount[getIndexOfMaxAmountOfFoods()]--;
                foodsToBeConsuming--;
            }
        }

        changePopularity(feedRate * 2);
    }

    //tax
    private void checkTheTaxFactor() {
        double taxOfEachPerson = 0;
        int taxesToBeCollected = 0;
        int changeOfPopularity = 1;

        if (taxRate < 0) {
            taxOfEachPerson = -0.2 * taxRate - 0.4;
            taxesToBeCollected = (int) (taxOfEachPerson * peoples.size()) - 1;
            changeOfPopularity = taxRate * -2 + 1;
        }
        else if(taxRate > 0) {
            taxOfEachPerson = 0.2 * taxRate + 0.4;
            taxesToBeCollected = (int) (taxOfEachPerson * peoples.size());
            if (taxRate <= 4)
                changeOfPopularity = taxRate * 2;
            else
                changeOfPopularity = taxRate * 4 - 8;
        }

        coins += taxesToBeCollected;
        changePopularity(changeOfPopularity);
    }

    //religion
    private void checkTheReligionFactor() {
        for (Block block : GameController.getGame().getMap().getBlocks()) {
            if (block.getThisBlockStructure()==null){
                continue;
            }
            if (block.getThisBlockStructure().getBuildingType().getType().equals("Church") || block.getThisBlockStructure().getBuildingType().getType().equals("Cathedral")) {
                popularity += 2;
            }
        }
    }

    //fear
    private void checkTheFearFactor() {
        changePopularity(fearRate);

        for (People people : peoples) {
            if (!people.getPeopleType().category.equals("Unemployed")) {
                double tmp = (double) fearRate * -1 + 20.0;
                people.getPeopleType().attackPower *= tmp / 20.0;
            }
        }
    }

    // change population

    private void changePopulation() {
        BuildingType buildingType;
        PeopleType peopleType;

        if (peoples.size()==0){
            return;
        }
        int amountOfPeopleToBeAdded = getAmountOfAllTypesOfFoods() / peoples.size();
        for (Structure structure : structures) {
            buildingType = structure.getBuildingType();
            peopleType = new PeopleType(buildingType.getType(),"Unemployed", 0, 0, 0, 0, 0, null, null);

            for (int i = 0; i < amountOfPeopleToBeAdded; i++) {
                if ((buildingType.getType().equals("Hovel")  || buildingType.getType().equals("SmallGateHouse") || buildingType.getType().equals("BigGateHouse")) && buildingType.getNormalPeopleCapacity() > structure.getNormalPeople().size())  {
                    NormalPeople normalPeople = new NormalPeople(peopleType, this, structure.getBlock());
                    peoples.add(normalPeople); // nothing another ????
                    structure.addNormalPeople(normalPeople);
                    structure.getBlock().addPeople(normalPeople);
                }
            }
        }

        // Population death + ?
        int amountOfPeopleWhoMostToReduced = peoples.size() / getAmountOfAllTypesOfFoods();
        for (Structure structure : structures) {
            buildingType = structure.getBuildingType();

            for (int i = 0; i < amountOfPeopleWhoMostToReduced; i++) {
                if ((buildingType.getType().equals("Hovel")  || buildingType.getType().equals("SmallGateHouse") || buildingType.getType().equals("BigGateHouse")) && structure.getNormalPeople().size() != 0)  {
                    for (People people : peoples) {
                        if (people instanceof NormalPeople && peoples.contains(people)) {
                            peoples.remove(people);
                            structure.removeNormalPeople((NormalPeople) people);
                            //structure.getBlock().removePeople(people);
                        }
                    }
                }
            }
        }
    }


    // *Auxiliary functions* //

    // Food functions
    private int getAmountOfAllTypesOfFoods() {
        int amountOfTypesOfFoods = 0;

        for (int i : foodAmount) {
            amountOfTypesOfFoods++;
        }

        return amountOfTypesOfFoods;
    }

    private int getIndexOfMaxAmountOfFoods() {
        int indexOfMax = 0;
        for (int i = 0; i < foodAmount.length; i++) {
            if (foodAmount[i] > indexOfMax)
                indexOfMax = i;
        }

        return indexOfMax;
    }
    // functions of religion


    public Integer getIndexOfFood(String type){
        for (int i = 0; i < foodType.length; i++) {
            if (foodType[i].equals(type)){
                return i;
            }
        }
        return null;
    }
    public  Structure getBuildingByNameForGovernment(String type){
        for(Structure structure:structures){
            if(structure.getBuildingType().getType().equals(type))
                return structure;
        }
        return null;
    }

    public BuildingType getBuildingTypeByNameForGovernment(String type) {
        for(Structure structure:structures){
            if(structure.getBuildingType().getType().equals(type))
                return structure.getBuildingType();
        }
        return null;
    }

    public BuildingType getBuildingByNameForGoverment(String str) {
        for(Structure structure:structures){
            if(structure.getBuildingType().getType().equals(str))
                return structure.getBuildingType();
        }
        return null;
    }*/

}
