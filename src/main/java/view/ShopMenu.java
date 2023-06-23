package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SpotLight;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;

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
    private int sample=0;

    @Override
    public void start(Stage stage) throws Exception {
        VBox shop=new VBox();
        shop.setStyle("-fx-font-family: Vivaldi; -fx-font-size: 24px;-fx-text-fill: black");
        shop.setSpacing(30);
        shop.setAlignment(Pos.TOP_CENTER);
        shop.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        shop.setMinWidth(1080);
        shop.setMinHeight(720);
        BackgroundImage myBI1= new BackgroundImage(new Image(LoginMenu.class.getResource("/images/1237368.jpg").openStream(),1080,720,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        shop.setBackground(new Background(myBI1));
        Label Counter=new Label("0");
        final int[] couter = {0};
        Label up=new Label("+");
        Label down=new Label("-");
        up.setFont(Font.font("Ariel", FontWeight.BOLD, 50));
        down.setFont(Font.font("Ariel", FontWeight.BOLD, 70));
        Counter.setFont(Font.font("Ariel", FontWeight.BOLD, 70));
        Counter.setTextFill(Color.BLACK);
        up.setTextFill(Color.GREEN);
        down.setTextFill(Color.RED);
        HBox count=new HBox(up,Counter,down);
        count.setAlignment(Pos.TOP_CENTER);
        count.setSpacing(60);
        shop.getChildren().add(new Label("Shop Menu"));
        SplitMenuButton tradetype=new SplitMenuButton(new CheckMenuItem("Buy"),new CheckMenuItem("Sell"));
        tradetype.setMinWidth(70);
        tradetype.setText("Buy");
        shop.getChildren().add(new Label("select type :"));
        shop.getChildren().add(tradetype);
        SplitMenuButton materialType=new SplitMenuButton(new CheckMenuItem("Stone"),new CheckMenuItem("Iron"),new CheckMenuItem("Wood"),new CheckMenuItem("Wheat"),new CheckMenuItem("Wine"),new CheckMenuItem("Hop")
        ,new CheckMenuItem("Pitch"),new CheckMenuItem("Flour"),new CheckMenuItem("Oil"));
        materialType.setText("materail to trade");
        shop.getChildren().add(new Label("Select material :"));
        shop.getChildren().add(materialType);
        shop.getChildren().add(count);
        Label finalPrice=new Label("0");
        Label BrforeFinal=new Label("Final Price : ");
        HBox pricelist=new HBox(BrforeFinal,finalPrice);
        BrforeFinal.setTextFill(Color.RED);
        finalPrice.setTextFill(Color.RED);
        pricelist.setAlignment(Pos.TOP_CENTER);
        shop.getChildren().add(pricelist);
        Button button=new Button("Buy");
        Label coins=new Label("\nyour coins : "+ (Game.getCurrentGovernment()==null ? 0:Game.getCurrentGovernment().getCoins()));
        shop.getChildren().add(button);
        shop.getChildren().add(coins);
        coins.setTextFill(Color.BLUEVIOLET);
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
        for(MenuItem menuItem:tradetype.getItems()){
            menuItem.setOnAction(actionEvent -> {
                ((CheckMenuItem) menuItem).setSelected(false);
                tradetype.setText(menuItem.getText());
                button.setText(tradetype.getText());
            });
        }
        for(MenuItem menuItem:materialType.getItems()){
            menuItem.setOnAction(actionEvent -> {
                materialType.setText(menuItem.getText());
                ((CheckMenuItem) menuItem).setSelected(false);
                switch (menuItem.getText()){
                    case "Wood":
                        sample=woodC;
                        break;
                    case "Iron":
                        sample=ironC;
                        break;
                    case "Stone":
                        sample=stoneC;
                        break;
                    case "Wheat":
                        sample=wheatC;
                        break;
                    case "Wine":
                        sample=wineC;
                        break;
                    case "Hop":
                        sample=hopC;
                        break;
                    case "Pitch":
                        sample=pitchC;
                        break;
                    case "Flour":
                        sample=flourC;
                        break;
                    case "Oil":
                        sample=oilC;
                        break;
                }
            });
        }
        up.setOnMouseClicked(mouseEvent -> {
            couter[0]++;
            Counter.setText(""+ couter[0]);
        });
        button.setOnMouseClicked(mouseEvent -> {
            if(materialType.getText().equals("materail to trade") || couter[0]==0){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("invalid materil or count!");
                alert.show();
            }
            else if(couter[0]*sample>(Game.getCurrentGovernment()==null ? 0:Game.getCurrentGovernment().getCoins())){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you don't have enough coins!");
                alert.show();
            }
            else{
                Game.getCurrentGovernment().changeCoins(couter[0]*sample*(tradetype.getText().equals("Buy") ? -1:1));
                Game.getCurrentGovernment().getResources().getResource(materialType.getText()).changeCount(couter[0]*(tradetype.getText().equals("Buy") ? 1:-1));
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(tradetype.getText()+" succesfull\n"+"count :"+couter[0]+"\nmaterial :"+materialType.getText()+"\ncost :"+sample*couter[0]);
            }
        });
        timeline=new Timeline(new KeyFrame(Duration.millis(16),actionEvent -> {
            finalPrice.setText(""+couter[0]*sample);
            coins.setText("\nyour coins : "+ (Game.getCurrentGovernment()==null ? 0:Game.getCurrentGovernment().getCoins()));
        }));
        timeline.setCycleCount(-1);
        timeline.play();
    }
}
