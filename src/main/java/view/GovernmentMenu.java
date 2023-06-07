package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import model.ApplicationManager;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class GovernmentMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        pane.setMinWidth(600);
        pane.setMinHeight(400);

        Rectangle governmentMenu = new Rectangle();
        governmentMenu.setArcHeight(5.0);
        governmentMenu.setArcWidth(5.0);
        governmentMenu.setFill(Paint.valueOf("#994343"));
        governmentMenu.setHeight(73.0);
        governmentMenu.setLayoutX(99.0);
        governmentMenu.setLayoutY(320.0);
        governmentMenu.setStroke(Paint.valueOf("BLACK"));
        governmentMenu.setStrokeType(StrokeType.valueOf("INSIDE"));
        governmentMenu.setWidth(379.0);

        pane.getChildren().add(governmentMenu);

        {
            Label feedLabel = new Label();
            feedLabel.setLayoutX(153);
            feedLabel.setLayoutY(328);
            feedLabel.setText("Food");
            Image feedImage;
            ImageView feedImageView;

            if (ApplicationManager.getCurrentGame().getCurrentGovernment().getFeedRate() < 0) {
                feedImage = (new Image(Objects.requireNonNull(getClass().getResource("slightly-frowning-face-apple.png")).openStream()));
                feedImageView = new ImageView(feedImage);
                feedImageView.setFitWidth(20);
                feedImageView.setFitHeight(20);
//                feedImageView.setStyle("-fx-background-color: red;");
            } else if (ApplicationManager.getCurrentGame().getCurrentGovernment().getFeedRate() > 0) {
                feedImage = (new Image(Objects.requireNonNull(getClass().getResource("slightly-smiling-face-apple.png")).openStream()));
                feedImageView = new ImageView(feedImage);
                feedImageView.setFitWidth(20);
                feedImageView.setFitHeight(20);
//                feedImageView.setStyle("-fx-background-color: green;");
            } else {
                feedImage = (new Image(Objects.requireNonNull(getClass().getResource("neutral-face-apple.png")).openStream()));
                feedImageView = new ImageView(feedImage);
                feedImageView.setFitWidth(20);
                feedImageView.setFitHeight(20);
//                feedImageView.setStyle("-fx-background-color: yellow;");
            }
            feedImageView.setLayoutX(132);
            feedImageView.setLayoutY(328);

            Button feedButton = new Button();
            feedButton.setText("1");
            feedButton.setOnMouseClicked(e -> {
                int newFeedRate = Integer.parseInt(feedButton.getText()) + 1;
                if (newFeedRate > 2) {
                    newFeedRate = newFeedRate % 2 - 3;
                    //set image
                }
                //set image
                feedButton.setText(String.valueOf(newFeedRate));
                ApplicationManager.getCurrentGame().getCurrentGovernment().setFeedRate(newFeedRate);
            });
            feedButton.setLayoutX(104);
            feedButton.setLayoutY(324);

            pane.getChildren().add(feedLabel);
            pane.getChildren().add(feedImageView);
            pane.getChildren().add(feedButton);
        }

        {
            Label taxLabel = new Label();
            taxLabel.setLayoutX(323);
            taxLabel.setLayoutY(328);
            taxLabel.setText("Tax");
            pane.getChildren().add(taxLabel);

            Image taxImage;
            ImageView taxImageView;

            if (ApplicationManager.getCurrentGame().getCurrentGovernment().getTaxRate() < 0) {
                taxImage = new Image(Objects.requireNonNull(getClass().getResource("slightly-smiling-face-apple.png")).openStream());
                taxImageView = new ImageView(taxImage);
                taxImageView.setFitWidth(20);
                taxImageView.setFitHeight(20);
//                taxImageView.setStyle("-fx-background-color: green;");
            } else if (ApplicationManager.getCurrentGame().getCurrentGovernment().getTaxRate() > 0) {
                taxImage = new Image(Objects.requireNonNull(getClass().getResource("slightly-smiling-face-apple.png")).openStream());
                taxImageView = new ImageView(taxImage);
                taxImageView.setFitWidth(20);
                taxImageView.setFitHeight(20);
            } else {
                taxImage = new Image(Objects.requireNonNull(getClass().getResource("slightly-smiling-face-apple.png")).openStream());
                taxImageView = new ImageView(taxImage);
                taxImageView.setFitWidth(20);
                taxImageView.setFitHeight(20);
            }
            taxImageView.setLayoutX(301);
            taxImageView.setLayoutY(328);
            pane.getChildren().add(taxImageView);

            Button taxButton = new Button();
            taxButton.setText("1");
            taxButton.setOnMouseClicked(e -> {
                int newTaxRate = Integer.parseInt(taxButton.getText())+1;
                if (newTaxRate > 8) {
                    newTaxRate = newTaxRate % 8 - 4;
                    //set image
                }
                //set image
                taxButton.setText(String.valueOf(newTaxRate));
            });
            taxButton.setLayoutX(274);
            taxButton.setLayoutY(324);
            pane.getChildren().add(taxButton);
        }




        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }
}
