package view;

import controller.CreateNewGame;
import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.util.ArrayList;

public class TradeMenu extends Application {
    VBox NewRequest=new VBox();
    VBox History=new VBox();
    VBox MyRequests=new VBox();
    ArrayList <VBox> requestsToAccept =new ArrayList<>();
    SplitMenuButton menu = new SplitMenuButton();

    Timeline timeline;
    @Override
    public void start(Stage stage) throws Exception {
        HBox pane=new HBox();
        pane.setStyle("-fx-font-family: Arial; -fx-font-size: 18px; -fx-text-fill: #100000");
        BackgroundImage myBI1= new BackgroundImage(new Image(LoginMenu.class.getResource("/images/LoginMenuBackground.jpg").openStream(),1000,600,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI1));
        pane.setSpacing(20);
        pane.setMinHeight(600);
        pane.setMinWidth(900);
        NewRequest.setSpacing(20);
        History.setSpacing(20);
        MyRequests.setSpacing(20);
        NewRequest.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        History.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        MyRequests.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        NewRequest.setAlignment(Pos.TOP_CENTER);
        History.setAlignment(Pos.TOP_CENTER);
        MyRequests.setAlignment(Pos.TOP_CENTER);
        NewRequest.setMinWidth(300);
        History.setMinWidth(300);
        MyRequests.setMinWidth(300);
        Label NewRequestHead=new Label("New Request");
        Label HistoryHead=new Label("History");
        Label MyREquestsHead=new Label("My Requests");
        MyRequests.getChildren().add(MyREquestsHead);
        NewRequest.getChildren().add(NewRequestHead);
        History.getChildren().add(HistoryHead);
        pane.getChildren().add(History);
        HistoryValidation();
        menu.setText("Select User");
        menu.setMaxWidth(200);
        menu.setMnemonicParsing(true);
        NewRequest.getChildren().add(menu);
        UsersList();
        MyRequestsValidation();
        SplitMenuButton type=new SplitMenuButton();
        type.getItems().addAll(new CheckMenuItem("Stone"),new CheckMenuItem("Iron"),new CheckMenuItem("Wood"),new CheckMenuItem("Wheat"),new CheckMenuItem("Wine"),new CheckMenuItem("Hop")
        ,new CheckMenuItem("Pitch"),new CheckMenuItem("Flour"),new CheckMenuItem("Oil"));
        type.setText("select item to donate");
        type.setMaxWidth(200);
        NewRequest.getChildren().add(type);
        Label notImportant=new Label("Count of item:");
        NewRequest.getChildren().add(notImportant);
        Label Counter=new Label("0");
        final int[] couter = {0};
        Label up=new Label("+");
        Label down=new Label("-");
        up.setFont(Font.font("Ariel", FontWeight.BOLD, 50));
        down.setFont(Font.font("Ariel", FontWeight.BOLD, 70));
        Counter.setFont(Font.font("Ariel", FontWeight.BOLD, 70));
        Counter.setTextFill(Color.WHITE);
        up.setTextFill(Color.GREEN);
        down.setTextFill(Color.RED);
        HBox count=new HBox(up,Counter,down);
        count.setAlignment(Pos.TOP_CENTER);
        count.setSpacing(60);
        NewRequest.getChildren().add(count);
        RadioButton doanteButt=new RadioButton("Doante");
        RadioButton requestButt=new RadioButton("Request");
        ToggleGroup to=new ToggleGroup();
        doanteButt.setSelected(true);
        doanteButt.setToggleGroup(to);
        requestButt.setToggleGroup(to);
        HBox requestType=new HBox(doanteButt,requestButt);
        requestType.setAlignment(Pos.TOP_CENTER);
        requestType.setSpacing(30);
        NewRequest.getChildren().add(requestType);
        TextField price=new TextField();
        price.setVisible(false);
        price.setMaxWidth(100);
        Button finalAccept=new Button("Send");
        NewRequest.getChildren().add(price);
        NewRequest.getChildren().add(finalAccept);
        pane.getChildren().add(NewRequest);
        pane.getChildren().add(MyRequests);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            return;
        });
        timeline=new Timeline(new KeyFrame(Duration.millis(16),actionEvent -> {
            requestsToAccept =new ArrayList<>();
            History.getChildren().clear();
            History.getChildren().add(HistoryHead);
            HistoryValidation();
            MyRequests.getChildren().clear();
            MyRequests.getChildren().add(MyREquestsHead);
            MyRequestsValidation();
        }));
        timeline.setCycleCount(1);
        timeline.play();
        for(MenuItem menuItem:menu.getItems()){
            menuItem.setOnAction(actionEvent -> {
                String name=menuItem.getText();
                System.out.println(name);
                menu.setText(name);
            });
        }
        for(MenuItem menuItem:type.getItems()){
            menuItem.setOnAction(actionEvent -> {
                String name=menuItem.getText();
                type.setText(name);
            });
        }
        down.setOnMouseClicked(mouseEvent -> {
            if(couter[0] ==0)
                return;
            else{
                couter[0]--;
                Counter.setText(""+ couter[0]);
            }
        });
        up.setOnMouseClicked(mouseEvent -> {
            couter[0]++;
            Counter.setText(""+ couter[0]);
        });
        requestButt.setOnAction(actionEvent -> {
            price.setVisible(true);
        });
        doanteButt.setOnAction(actionEvent -> {
            price.setVisible(false);
        });
        stage.setOnCloseRequest(windowEvent -> {
            timeline.stop();
        });
        finalAccept.setOnMouseClicked(mouseEvent -> {
            Alert alert=new Alert(Alert.AlertType.ERROR);

            if(menu.getText().equals("Select User") || type.getText().equals("select item to donate") || couter[0]==0){
                alert.setContentText("you haven't filled all texts!");
                alert.showAndWait();
            }
            else if(requestButt.isSelected() && !price.getText().matches("[0-9]+")){
                alert.setContentText("incorrect format for price!");
                alert.showAndWait();
            }
            else if(GameController.getCurrentGovernment() != null){
                if(couter[0]>GameController.getCurrentGovernment().getResources().getResourceAmount(type.getText())){
                    System.out.println(GameController.getCurrentGovernment().getResources().getResourceAmount(type.getText())+"\n"+type.getText());
                    alert.setContentText("you don't have enough resources to make this request!");
                    alert.showAndWait();
                }
                else {
                    int pricelist=(requestButt.isSelected() ? Integer.parseInt(price.getText()):0);
                    Government requested=GameController.getGame().getGovernmentByUser(ApplicationManager.getUserByUsername(menu.getText()));
                    Trade trade=new Trade(Game.getCurrentGovernment(),requested,type.getText(),pricelist,couter[0],"");
                    TradeMessage tradeMessage = new TradeMessage("", Game.getCurrentGovernment(), requested, trade);
                    requested.addTradeMessage(tradeMessage);
                    GameController.getCurrentGovernment().AddTradeMessage(tradeMessage);
                    GameController.getCurrentGovernment().addTrade(trade);
                    GameController.getCurrentGovernment().getThisGovermentMessageTrades().add(tradeMessage);
                    GameController.getCurrentGovernment().getResources().getResource(type.getText()).changeCount(pricelist);
                    System.out.println(requested.getTradeMessages());
                    MyRequests.getChildren().add(new VBox(new Label((GameController.getCurrentGovernment().getThisGovermentMessageTrades().size() + 1) + ". " + tradeMessage.getTrade().getRequested().getOwner().getUsername()
                            + "--->" + tradeMessage.getTrade().getType() + "\n  " + tradeMessage.getTrade().getAmount()
                            + "   " + tradeMessage.getTrade().getPrice() + "$  " + (tradeMessage.showCondition() ? "accepted" : "waiting"))));
                    requested.addTrade(trade);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("new trade started with "+requested.getOwner().getUsername());
                    alert.show();
                }
            }
        });
        for(TradeMessage tradeMessage:GameController.getCurrentGovernment().getTradeMessages()){
            tradeMessage.acceptButton.setOnMouseClicked(mouseEvent -> {
                tradeMessage.acceptButton.dicide();
            });
            tradeMessage.rejectButton.setOnMouseClicked(mouseEvent -> {
                tradeMessage.rejectButton.dicide();
            });
        }
    }
    public void HistoryValidation(){
        Government government = Game.getCurrentGovernment();
        if(government.getTradeMessages()==null || government.getTrades().size()==0){
            return;
        }
        VBox message;
        for(int i=0;i<government.getTradeMessages().size();i++){
            message=new VBox(government.getTradeMessages().get(i).tradeRequestToShow());
            requestsToAccept.add(0,message);
            message.setAlignment(Pos.TOP_CENTER);
            requestsToAccept.get(0).setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(1),new BorderWidths(2))));
            History.getChildren().add(requestsToAccept.get(0));
        }

    }
    public void MyRequestsValidation(){
        Government government=GameController.getCurrentGovernment();
        if(government==null){
           MyRequests.getChildren().add(new VBox(new Label("You haven't send away any requests yet!")));
            return;
        }

        if(government.getThisGovermentMessageTrades().size()==0){
           MyRequests.getChildren().add(new VBox(new Label("You haven't send away any requests yet!")));
           return;
        }
        try {
            for (int i = 0; i < government.getThisGovermentMessageTrades().size(); i++) {
                TradeMessage tradeMessage = government.getTradeMessages().get(i);
                MyRequests.getChildren().add(new VBox(new Label((i + 1) + ". " + tradeMessage.getTrade().getRequested().getOwner().getUsername()
                        + "--->" + tradeMessage.getTrade().getType() + "\n  " + tradeMessage.getTrade().getAmount()
                        + "   " + tradeMessage.getTrade().getPrice() + "$  " + (tradeMessage.showCondition() ? "accepted" : "waiting"))));

            }
        }catch (Exception e){

        }


    }
    public void UsersList(){
        if(CreateNewGame.governments ==null) {
            System.out.println("null game");
            return;
        }
        if(CreateNewGame.governments.size()==0 || CreateNewGame.governments==null) {
            System.out.println("empty govers");
            return;
        }
        for(Government government:CreateNewGame.governments){
            if(!government.getOwner().equals(GameController.getCurrentGovernment().getOwner()))
                 menu.getItems().add(new MenuItem(government.getOwner().getUsername()));
        }
    }

}
