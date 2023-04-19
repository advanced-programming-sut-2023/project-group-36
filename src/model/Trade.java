package model;

public class Trade {
    private int amount;
    private Government requester;
    private Government requested;
    private int type;
    private int price;
    private boolean IsAccepted;

    public Trade (Government requester, Government requested, int type,int price, int amount,String message){
        this.requester = requester;
        this.requested = requested;
        this.price = price;
        this.type = type;
        this.amount = amount;
        IsAccepted = false;
        requester.addTrade(this);
        requested.addTrade(this);
        TradeMessage tradeMessage = new TradeMessage(message, requester, requested, this);
        requested.addTradeMessage(tradeMessage);
    }

    public void accept(){
        IsAccepted = true;
    }

    public int getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }


    public int getAmount() {
        return amount;
    }
}
