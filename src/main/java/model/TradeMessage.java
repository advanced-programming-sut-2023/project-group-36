package model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TradeMessage {
    private String message;
    private Government sender;
    private Government receiver;
    Button acceptButton=new Button("accept?");
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
        System.out.println(id+". "+sender.getOwner().getUsername()+": "+message+"\n"+"type: "+trade.getType()+" price: "+trade.getPrice()+" amount: "+trade.getAmount());
        isShowed = true;
    }

    public boolean showCondition(){
        return isShowed;
    }

    public void accept() {
        trade.accept();
        message+=" (is accepted!)";
    }

    public Trade getTrade() {
        return trade;
    }
    public VBox tradeRequestToShow(){
        HBox hBox=new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(new Label(trade.getRequester().getOwner().getUsername()),new Label(trade.getRequested().getOwner().getUsername()),new Label(trade.getType())
                ,new Label(trade.getAmount()+""),new Label(trade.getPrice()+""),new Label(this.isShowed ? "new" : "   " ));
        VBox resualt=new VBox();
        resualt.getChildren().add(hBox);
        resualt.getChildren().add(acceptButton);
        return resualt;
    }
}
