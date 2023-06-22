package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Trade;
import model.TradeMessage;

public class TradeMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        HBox pane=new HBox();
        pane.setSpacing(20);
        pane.setMinHeight(600);
        pane.setMinWidth(900);
        VBox NewRequest=new VBox();
        VBox History=new VBox();
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
        pane.getChildren().add(NewRequest);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

}
