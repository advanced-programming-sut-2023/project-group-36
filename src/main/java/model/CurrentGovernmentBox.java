package model;

import controller.GameController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CurrentGovernmentBox extends Rectangle {
    private Label username = new Label();
    private Label text = new Label("بازیکن فعلی");

    public CurrentGovernmentBox(int x, int y, Pane pane){
        super(100,60);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setFill(Color.AQUA);

        username.setText(GameController.currentGovernment.getOwner().getUsername());
        username.setLayoutX(this.getLayoutX()+20);
        username.setLayoutY(this.getLayoutY()+20);
        text.setLayoutX(this.getLayoutX()+5);
        text.setLayoutY(this.getLayoutY()+5);
        pane.getChildren().addAll(this,username,text);
    }

    public void nextGovernment(){
        username.setText(GameController.currentGovernment.getOwner().getUsername());
    }

}
