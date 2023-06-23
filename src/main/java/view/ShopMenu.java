package view;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SpotLight;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ShopMenu extends Application {
    Timeline timeline;
    private int woodC=1;
    private int ironC=20;
    private int stoneC=2;
    private int wheatC=5;
    private int wineC=3;
    private int hopC=6;
    private int pitchC=15;
    private int oilC=13;
    private int flourC=9;

    @Override
    public void start(Stage stage) throws Exception {
        VBox shop=new VBox();
        shop.setAlignment(Pos.TOP_CENTER);
        shop.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        shop.setMinWidth(400);
        shop.setMinHeight(600);
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
        shop.getChildren().add(new Label("Shop Menu"));
        SplitMenuButton tradetype=new SplitMenuButton(new CheckMenuItem("Buy"),new CheckMenuItem("Sell"));
        tradetype.setMinWidth(100);
        shop.getChildren().add(new Label("select type :"));
        shop.getChildren().add(tradetype);
        Scene scene=new Scene(shop);
        stage.setScene(scene);
        stage.show();
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
}
