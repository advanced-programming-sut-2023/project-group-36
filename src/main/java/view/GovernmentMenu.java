package view;

import controller.GameController;
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
import model.Block;

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
                taxImage = new Image(Objects.requireNonNull(getClass().getResource("slightly-frowning-face-apple.png")).openStream());
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
                taxImage = new Image(Objects.requireNonNull(getClass().getResource("neutral-face-apple.png")).openStream());
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

        {
            Label fearLabel = new Label();
            fearLabel.setLayoutX(153);
            fearLabel.setLayoutY(368);
            fearLabel.setText("Fear");
            pane.getChildren().add(fearLabel);

            Image fearImage;
            ImageView fearImageView;

            if (ApplicationManager.getCurrentGame().getCurrentGovernment().getFearRate() < 0) {
                fearImage = new Image(Objects.requireNonNull(getClass().getResource("slightly-frowning-face-apple.png")).openStream());
                fearImageView = new ImageView(fearImage);
                fearImageView.setFitWidth(20);
                fearImageView.setFitHeight(20);
//                taxImageView.setStyle("-fx-background-color: green;");
            } else if (ApplicationManager.getCurrentGame().getCurrentGovernment().getFearRate() > 0) {
                fearImage = new Image(Objects.requireNonNull(getClass().getResource("slightly-smiling-face-apple.png")).openStream());
                fearImageView = new ImageView(fearImage);
                fearImageView.setFitWidth(20);
                fearImageView.setFitHeight(20);
            } else {
                fearImage = new Image(Objects.requireNonNull(getClass().getResource("neutral-face-apple.png")).openStream());
                fearImageView = new ImageView(fearImage);
                fearImageView.setFitWidth(20);
                fearImageView.setFitHeight(20);
            }

            fearImageView.setLayoutX(132);
            fearImageView.setLayoutY(368);
            pane.getChildren().add(fearImageView);

            Button fearButton = new Button();
            fearButton.setText("1");
            fearButton.setOnMouseClicked(e -> {
                int tmp = Integer.parseInt(fearButton.getText())+1;
                if (tmp > 5) {
                    tmp = tmp % 5 - 6;
                    // set image
                }
                // set image
                fearButton.setText(String.valueOf(tmp));
            });
            fearButton.setLayoutX(104);
            fearButton.setLayoutY(364);
            pane.getChildren().add(fearButton);

        }

        {
            Label religionLabel = new Label();
            religionLabel.setLayoutX(323);
            religionLabel.setLayoutY(368);
            religionLabel.setText("Religion");
            pane.getChildren().add(religionLabel);

            Image religionImage;
            ImageView religionImageView;

            int religionFactor = 0;
            for (Block block : GameController.getGame().getMap().getBlocks()) {
                if (block.getThisBlockStructure().getBuildingType().getType().equals("Church") || block.getThisBlockStructure().getBuildingType().getType().equals("Cathedral")){
                    religionFactor ++;
                }
            }

            if (religionFactor > 0) {
                religionImage = new Image(Objects.requireNonNull(getClass().getResource("slightly-smiling-face-apple.png")).openStream());
                religionImageView = new ImageView(religionImage);
                religionImageView.setFitWidth(20);
                religionImageView.setFitHeight(20);
            } else {
                religionImage = new Image(Objects.requireNonNull(getClass().getResource("neutral-face-apple.png")).openStream());
                religionImageView = new ImageView(religionImage);
                religionImageView.setFitWidth(20);
                religionImageView.setFitHeight(20);
            }
            religionImageView.setLayoutX(301);
            religionImageView.setLayoutY(368);
            pane.getChildren().add(religionImageView);
        }



        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }
}
