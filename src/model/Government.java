package model;

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






}
