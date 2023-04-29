package project.model;

import project.model.Peoples.People;

import java.util.ArrayList;

public class Government{
    private User owner;
    private ArrayList<People> peoples = new ArrayList<>();
    private ArrayList<TradeMessage> tradeMessages = new ArrayList<>();
    private ArrayList<Trade> trades = new ArrayList<>();
    private String color;
    private Resources resources;
    private final String[] foodType;
    private int[]  foodAmount;

    private int popularity;
    private int tax;
    private int fear;
    private int foodRate;
    private int religiousPeople;
    private Map map;

    private int coins;
    private People selectedPeople;


    public Government(User user, String color){
        this.owner = user;
        this.coins = 1000;
        this.color = color;
        foodType = new String[]{"bread", "meat", "apple", "type4"}; // 1: bread, 2: meat, 3: apple, 4: meat
        foodAmount = new int[]{0, 0, 0, 0};
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

    public int getFear() {
        return fear;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getTax() {
        return tax;
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
        return false; //
    }

    public void nextTurn(){
        // food & ...

    }

    public Integer getIndexOfFood(String type){
        for (int i = 0; i < foodType.length; i++) {
            if (foodType[i].equals(type)){
                return i;
            }
        }
        return null;
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



    public int getCoins() {
        return coins;
    }

    public void changeCoins(int count) {
        coins+=count;
    }



    public void setSelectedPeople(People people) {
        selectedPeople = people;
    }

    public People getSelectedPeople() {
        return selectedPeople;
    }


    public Resources getResources() {
        return resources;
    }
}
