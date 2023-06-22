package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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

public class TradeMenu extends Application {
    VBox NewRequest=new VBox();
    VBox History=new VBox();
    SplitMenuButton menu = new SplitMenuButton();

    Timeline timeline;
    @Override
    public void start(Stage stage) throws Exception {
        HBox pane=new HBox();
        pane.setStyle("-fx-font-family: Arial; -fx-font-size: 16px");
        BackgroundImage myBI1= new BackgroundImage(new Image(LoginMenu.class.getResource("/images/LoginMenuBackground.jpg").openStream(),900,600,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI1));
        pane.setSpacing(20);
        pane.setMinHeight(600);
        pane.setMinWidth(900);
        NewRequest.setSpacing(20);
        History.setSpacing(20);
        NewRequest.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        History.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        NewRequest.setAlignment(Pos.TOP_CENTER);
        History.setAlignment(Pos.TOP_CENTER);
        NewRequest.setMinWidth(300);
        History.setMinWidth(300);
        Label NewRequestHead=new Label("New Request");
        Label HistoryHead=new Label("History");
        NewRequest.getChildren().add(NewRequestHead);
        History.getChildren().add(HistoryHead);
        pane.getChildren().add(History);
        HistoryValidation();
        //Setting text to the SplitMenuButton
        menu.setText("Select User");
        menu.setMaxWidth(200);
        menu.setMnemonicParsing(true);
        NewRequest.getChildren().add(menu);
        UsersList();
        SplitMenuButton type=new SplitMenuButton();
        type.getItems().addAll(new CheckMenuItem("Stone"),new CheckMenuItem("Iron"),new CheckMenuItem(""),new CheckMenuItem(""),new CheckMenuItem(""));
        type.setText("select item to donate");
        type.setMaxWidth(200);
        NewRequest.getChildren().add(type);
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
        pane.getChildren().add(NewRequest);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
        timeline=new Timeline(new KeyFrame(Duration.seconds(10),actionEvent -> {
            History.getChildren().clear();
            History.getChildren().add(HistoryHead);
            HistoryValidation();
        }));
        timeline.setCycleCount(-1);
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
    }
    public void HistoryValidation(){

        History.getChildren().add(new VBox(new Label("dgfdg")));
        if(ApplicationManager.getCurrentGame()==null)
            return;
        if(ApplicationManager.getCurrentGame().getCurrentGovernment()==null)
            return;
        Government government = ApplicationManager.getCurrentGame().getCurrentGovernment();
        if(government.getTradeMessages()==null || government.getTrades().size()==0){
            History.getChildren().add(new Label("NO Request has been made yet!"));
            return;
        }
        VBox message;
        for(int i=0;i<government.getTradeMessages().size();i++){
            message=new VBox(government.getTradeMessages().get(i).tradeRequestToShow());
            message.setAlignment(Pos.TOP_CENTER);
            message.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
            History.getChildren().add(message);
        }

    }
    public synchronized void UsersList(){
        if(ApplicationManager.getCurrentGame()==null)
            return;
        if(Game.getGovernments().size()==0 || ApplicationManager.getCurrentGame().getGovernments()==null)
            return;
        for(Government government:Game.getGovernments()){
            menu.getItems().add(new CheckMenuItem(government.getOwner().getUsername()));
        }
    }
}
