package model;

public class Trade {
    private int amount;
    private Government requester;
    private Government requested;
    private String type;
    private int price;
    private boolean IsAccepted;

    public Trade (Government requester, Government requested, String type,int price, int amount,String message){
        this.requester = requester;
        this.requested = requested;
        this.price = price;
        this.type = type;
        this.amount = amount;
        IsAccepted = false;
    }

    public void accept(){
        IsAccepted = true;
        requested.changeCoins(price);
        requested.changeAmountOfResource(type,-amount);
        requester.changeCoins(-price);
        requester.changeAmountOfResource(type,amount);
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }


    public int getAmount() {
        return amount;
    }

    public String check() {
        if (price> requester.getCoins()){
            return "Requester coins are insufficient for this trade.";
        }
        if (amount> requested.getAmountOfResource(type)){
            return "The amount of "+type+" recourse of requested government is insufficient.";
        }
        return null;
    }

    public void show(int number) {
        System.out.print(number+") requester: "+requester.getOwner().getUsername()+", requested: "+requested.getOwner().getUsername()+", type: "+type+", amount: "+amount+", price: "+price);
        if (IsAccepted){
            System.out.println(" (accepted!)");
            return;
        }
        System.out.println();
    }
}
