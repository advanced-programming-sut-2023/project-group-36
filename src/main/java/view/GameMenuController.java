package view;
import controller.EditMap;
import controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;

public class GameMenuController {


    private static ArrayList<GBlock> gBlocks;
    public Pane pane;
    public Label government;
    public ImageView exit;
    public ScrollPane scrollPane;
    public Pane miniMap;
    @FXML
    private Pane mapPane = new Pane();

    public double newScale = 1.0;




    public void gameInitialize(){

        double minScale = 0.7;
        double maxScale = 1.5;

        Button zoomInButton = new Button("+");
        Button zoomOutButton = new Button("-");
        zoomInButton.setLayoutX(10);
        zoomOutButton.setLayoutX(40);

        zoomInButton.setOnAction(event -> {
            newScale = newScale * 1.2;
            if (newScale <= maxScale) {
                mapPane.setLayoutX(mapPane.getLayoutX()*1.2);
                mapPane.setLayoutY(mapPane.getLayoutY()*1.2);
                blocksZoom(1.2);
            }
            else {
                newScale /= 1.2;
            }
        });

        zoomOutButton.setOnAction(event -> {
            newScale = newScale * 0.9;
            if (newScale >= minScale) {
                mapPane.setLayoutX(mapPane.getLayoutX()*0.9);
                mapPane.setLayoutY(mapPane.getLayoutY()*0.9);

                blocksZoom(0.9);
            }
            else {
                newScale /= 0.9;
            }
        });

        pane.getChildren().add(zoomOutButton);
        pane.getChildren().add(zoomInButton);


        gBlocks = new ArrayList<>();
        government.setText(GameController.currentGovernment.getOwner().getUsername());
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                gBlocks.add(new GBlock(getBlockByPosition(i,j),false));
            }
        }
        Label label = new Label("");
        label.setLayoutX(100);
        label.setLayoutY(170);
        label.setStyle("-fx-font-size: 20;-fx-text-fill: #6fe000");
        miniMap.getChildren().add(label);

        ArrayList<Government> governments = Game.getGovernments();
        int x,y;
        for (Government value : governments) {
            x = value.getCentralCastle().getBlock().getX();
            y = value.getCentralCastle().getBlock().getY();
            Circle circle = new Circle();
            circle.setCenterX(4*x+10);
            circle.setCenterY(4*y+10);
            System.out.println(circle.getCenterX()+","+circle.getCenterY());
            circle.setOnMouseEntered(mouseEvent -> {
                label.setText(value.getOwner().getUsername());
                System.out.println(value.getOwner().getUsername());
            });
            circle.setRadius(10);
            miniMap.getChildren().add(circle);
        }

    }

    private void blocksZoom(double scale) {
        for (int i = 0; i < gBlocks.size(); i++) {
            gBlocks.get(i).zoom(scale);
        }
    }


    public static Block getBlockByPosition ( int x, int y){
        for (Block block : EditMap.blocks) {
            if (block.getX() == x && block.getY() == y) {
                return block;
            }
        }
        return null;
    }


    public Pane getMapPane() {
        return mapPane;
    }

    public void nextTurn(MouseEvent mouseEvent) {
        GameController.nextTurn();
        government.setText(GameController.currentGovernment.getOwner().getUsername());
        for (GBlock gBlock : gBlocks) {
            gBlock.update();
        }
    }

    public void exit(MouseEvent mouseEvent) throws Exception {
        new CreateNewGameMenu().start(CreateNewGameMenu.stage);
    }

    public void dropBuilding(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        Cursor cursor = Cursor.cursor(imageView.getImage().getUrl());
        GameMenu.root.setCursor(cursor);
        String packageAddress = "file:/C:/Users/m/Desktop/StrongholdPhase2/target/classes/images/Buildings/";
        String name = imageView.getImage().getUrl().replace(packageAddress,"");
        name = name.replace(".png","");
        System.out.println(name);
        GameController.inDropBuilding = name;
    }

    public void government(MouseEvent mouseEvent) throws Exception {
        GovernmentMenu governmentMenu = new GovernmentMenu();
        governmentMenu.start(new Stage());
    }

    public void shop(MouseEvent mouseEvent) throws Exception {
        ShopMenu shopMenu = new ShopMenu();
        shopMenu.start(new Stage());
    }

    public void trade(MouseEvent mouseEvent) throws Exception {
        TradeMenu tradeMenu = new TradeMenu();
        tradeMenu.start(new Stage());
    }
}
