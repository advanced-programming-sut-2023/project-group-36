package model;

public class Trade {
    private Government requester;
    private Government requested;
    private String type;
    private int price;
    private boolean IsAccepted;

    Trade (Government requester, Government requested, String type,int price, String message){
        this.requester = requester;
        this.requested = requested;
        this.price = price;
        this.type = type;
        IsAccepted = false;
        requester.addTrade(this);
        requested.addTrade(this);
        TradeMessage tradeMessage = new TradeMessage(message, requester, requested);
        requested.addTradeMessage(tradeMessage);
    }

    public void accept(){
        IsAccepted = true;
    }

}
