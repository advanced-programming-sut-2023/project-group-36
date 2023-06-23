package model;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TradeMessage extends Application {
    public class myButton extends Button{
        public myButton(String s) {
            super(s);
        }

        public void dicide(){
         if(this.getText().contains("accept")){
                if(receiver.getCoins()<trade.getPrice()){

                }
                else{
                    receiver.changeCoins((-1)*trade.getPrice());
                    receiver.getResources().getResource(trade.getType()).changeCount(trade.getAmount());
                    sender.changeCoins(trade.getPrice());
                    acceptButton.setText("accepted");
                    rejectButton.setText("...");

                }
         }
         else if(this.getText().contains("reject")){
                rejectButton.setText("rejected");
                acceptButton.setText("...");
                sender.getResources().getResource(trade.getType()).changeCount(trade.getAmount());
         }
        }
    }
    private String message;
    private Government sender;
    private Government receiver;
    myButton acceptButton=new myButton("accept?");
    myButton rejectButton=new myButton("reject");
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
        resualt.getChildren().add(new VBox(acceptButton,rejectButton));
        return resualt;
    }

    @Override
    public void start(Stage stage) throws Exception {
        acceptButton.setOnMouseClicked(mouseEvent -> {
            acceptButton.setText("okay");
            System.out.println("yes");
        });
    }



}
