package model;

import model.Peoples.People;

import java.util.ArrayList;

public class Government{
    private User owner;
    private ArrayList<People> peoples;
    private ArrayList<TradeMessage> tradeMessages;
    private ArrayList<Trade> trades;
    private int popularity;
    private int tax;
    private int fear;
    private int foodRate;
    private int food1Amount;
    private int food2Amount;
    private int food3Amount;
    private int food4Amount;
    private int religiousPeople;
    private Map map;

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

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public ArrayList<TradeMessage> getTradeMessages() {
        return tradeMessages;
    }

    public int getFear() {
        return fear;
    }

    public int getFood1Amount() {
        return food1Amount;
    }

    public int getFood2Amount() {
        return food2Amount;
    }

    public int getFood3Amount() {
        return food3Amount;
    }

    public int getFood4Amount() {
        return food4Amount;
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

    public void removeTradMessage(TradeMessage tradeMessage){

    }

    public boolean checkGameOver(){
        return false; //
    }

    public void nextTurn(){

    }

}
