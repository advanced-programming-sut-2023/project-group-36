package model;

public class TradeMessage {
    private String message;
    private Government sender;
    private Government receiver;

    private Trade trade;

    private boolean isShowed;
    public TradeMessage(String message, Government sender, Government receiver, Trade trade){
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
        this.trade = trade;
        isShowed = false;
    }

    public void show (int id){
        System.out.println(id+". "+sender.getOwner().getUsername()+": "+message+"\n"+"type: "+this.Type(trade.getType())+" price: "+trade.getPrice()+" amount: "+trade.getAmount());
        isShowed = true;
    }

    public boolean showCondition(){
        return isShowed;
    }

    private String Type(int type) {
        return "type";
    }

    public void accept() {
        trade.accept();
    }
}
