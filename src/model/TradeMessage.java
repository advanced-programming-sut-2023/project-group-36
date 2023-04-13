package model;

public class TradeMessage {
    private String message;
    private Government sender;
    private Government receiver;

    private boolean isShowed;
    TradeMessage(String message,Government sender,Government receiver){
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
        isShowed = false;
    }

    public void show (){
        isShowed = true;
    }

}
