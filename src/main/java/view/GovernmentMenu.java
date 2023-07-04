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

public class GovernmentMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        pane.setMinWidth(80);
        pane.setMinHeight(340);

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

        // total rate
        Label totalLabel = new Label();
        totalLabel.setLayoutX(188);
        totalLabel.setLayoutY(348);
        totalLabel.setText("Total");
        pane.getChildren().add(totalLabel);

        Image newTotalImage;
        ImageView newTotalImageView;

        int total = ApplicationManager.getCurrentGame().getCurrentGovernment().getFeedRate() + ApplicationManager.getCurrentGame().getCurrentGovernment().getFearRate() + ApplicationManager.getCurrentGame().getCurrentGovernment().getTaxRate() + GameController.getCurrentGovernment().getReligionFactor();

        if (total < 0) {
            newTotalImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/sad.png")));
            newTotalImageView = new ImageView(newTotalImage);
        } else if (total > 0) {
            newTotalImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/smile.png")));
            newTotalImageView = new ImageView(newTotalImage);
        } else {
            newTotalImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/poker.png")));
            newTotalImageView = new ImageView(newTotalImage);
        }

        newTotalImageView.setFitWidth(20);
        newTotalImageView.setFitHeight(20);
        newTotalImageView.setLayoutX(215);
        newTotalImageView.setLayoutY(348);
        pane.getChildren().add(newTotalImageView);



        {
            Label feedLabel = new Label();
            feedLabel.setLayoutX(153);
            feedLabel.setLayoutY(328);
            feedLabel.setText("Food");
            Image feedImage;
            ImageView feedImageView;

            if (ApplicationManager.getCurrentGame().getCurrentGovernment().getFeedRate() < 0) {
                feedImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/sad.png")));
                feedImageView = new ImageView(feedImage);
            } else if (ApplicationManager.getCurrentGame().getCurrentGovernment().getFeedRate() > 0) {
                feedImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/smile.png")));
                feedImageView = new ImageView(feedImage);
            } else {
                feedImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/poker.png")));
                feedImageView = new ImageView(feedImage);
}
            feedImageView.setFitWidth(20);
            feedImageView.setFitHeight(20);
            feedImageView.setLayoutX(132);
            feedImageView.setLayoutY(328);

            Button feedButton = new Button();
            feedButton.setText(GameController.getCurrentGovernment().getFeedRate()+"");
            feedButton.setOnMouseClicked(e -> {
                //feed
                int newFeedRate = Integer.parseInt(feedButton.getText()) + 1;
                if (newFeedRate > 2) {
                    newFeedRate = newFeedRate % 2 - 3;
                }
                //set image
                ImageView imageView = updateImage(newFeedRate);
                imageView.setLayoutX(132);
                imageView.setLayoutY(328);
                pane.getChildren().add(imageView);
                feedButton.setText(String.valueOf(newFeedRate));
                ApplicationManager.getCurrentGame().getCurrentGovernment().setFeedRate(newFeedRate);
                //total
                int newTotal = ApplicationManager.getCurrentGame().getCurrentGovernment().getFeedRate() + ApplicationManager.getCurrentGame().getCurrentGovernment().getFearRate() + ApplicationManager.getCurrentGame().getCurrentGovernment().getTaxRate() + GameController.getCurrentGovernment().getReligionFactor();

                ImageView imageView1 = updateImage(newTotal);

                imageView1.setFitWidth(20);
                imageView1.setFitHeight(20);
                imageView1.setLayoutX(215);
                imageView1.setLayoutY(348);
                pane.getChildren().add(imageView1);
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
                taxImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/sad.png")));
                taxImageView = new ImageView(taxImage);
            } else if (ApplicationManager.getCurrentGame().getCurrentGovernment().getTaxRate() > 0) {
                taxImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/smile.png")));
                taxImageView = new ImageView(taxImage);
            } else {
                taxImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/poker.png")));
                taxImageView = new ImageView(taxImage);
            }
            taxImageView.setFitWidth(20);
            taxImageView.setFitHeight(20);
            taxImageView.setLayoutX(301);
            taxImageView.setLayoutY(328);
            pane.getChildren().add(taxImageView);

            Button taxButton = new Button();
            taxButton.setText(GameController.getCurrentGovernment().getTaxRate()+"");
            taxButton.setOnMouseClicked(e -> {
                // tax
                int newTaxRate = Integer.parseInt(taxButton.getText())+1;
                if (newTaxRate > 8) {
                    newTaxRate = newTaxRate % 8 - 4;
                }
                //set image
                ImageView newimageView = updateImage(newTaxRate);
                newimageView.setLayoutX(301);
                newimageView.setLayoutY(328);
                pane.getChildren().add(newimageView);
                taxButton.setText(String.valueOf(newTaxRate));
                ApplicationManager.getCurrentGame().getCurrentGovernment().setTaxRate(newTaxRate);
                //total
                int newTotal = ApplicationManager.getCurrentGame().getCurrentGovernment().getFeedRate() + ApplicationManager.getCurrentGame().getCurrentGovernment().getFearRate() + ApplicationManager.getCurrentGame().getCurrentGovernment().getTaxRate() + GameController.getCurrentGovernment().getReligionFactor();

                ImageView imageView1 = updateImage(newTotal);

                imageView1.setFitWidth(20);
                imageView1.setFitHeight(20);
                imageView1.setLayoutX(215);
                imageView1.setLayoutY(348);
                pane.getChildren().add(imageView1);
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
                fearImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/sad.png")));
                fearImageView = new ImageView(fearImage);
            } else if (ApplicationManager.getCurrentGame().getCurrentGovernment().getFearRate() > 0) {
                fearImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/smile.png")));
                fearImageView = new ImageView(fearImage);
            } else {
                fearImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/poker.png")));
                fearImageView = new ImageView(fearImage);
            }

            fearImageView.setFitWidth(20);
            fearImageView.setFitHeight(20);
            fearImageView.setLayoutX(132);
            fearImageView.setLayoutY(368);
            pane.getChildren().add(fearImageView);

            //fear
            Button fearButton = new Button();
            fearButton.setText(GameController.getCurrentGovernment().getFearRate()+"");
            fearButton.setOnMouseClicked(e -> {
                int newfearRate = Integer.parseInt(fearButton.getText())+1;
                if (newfearRate > 5) {
                    newfearRate = newfearRate % 5 - 6;
                }
                // set image
                ImageView newImageView = updateImage(newfearRate);
                newImageView.setLayoutX(132);
                newImageView.setLayoutY(368);
                pane.getChildren().add(newImageView);
                fearButton.setText(String.valueOf(newfearRate));
                ApplicationManager.getCurrentGame().getCurrentGovernment().setFearRate(newfearRate);

                //total
                int newTotal = ApplicationManager.getCurrentGame().getCurrentGovernment().getFeedRate() + ApplicationManager.getCurrentGame().getCurrentGovernment().getFearRate() + ApplicationManager.getCurrentGame().getCurrentGovernment().getTaxRate() + GameController.getCurrentGovernment().getReligionFactor();

                ImageView imageView1 = updateImage(newTotal);

                imageView1.setFitWidth(20);
                imageView1.setFitHeight(20);
                imageView1.setLayoutX(215);
                imageView1.setLayoutY(348);
                pane.getChildren().add(imageView1);
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

            int religionFactor = GameController.getCurrentGovernment().getReligionFactor();

            if (religionFactor > 0) {
                religionImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/smile.png")));
                religionImageView = new ImageView(religionImage);
            } else {
                religionImage = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/poker.png")));
                religionImageView = new ImageView(religionImage);
            }
            religionImageView.setFitWidth(20);
            religionImageView.setFitHeight(20);
            religionImageView.setLayoutX(301);
            religionImageView.setLayoutY(368);
            pane.getChildren().add(religionImageView);
        }



        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    private ImageView updateImage(int newRate) {
        Image image;
        if (newRate < 0) {
            image = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/sad.png")));
        } else if (newRate > 0) {
            image = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/smile.png")));
        } else {
            image = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/faceEmoji/poker.png")));
        }
        ImageView newImageView = new ImageView(image);
        newImageView.setFitWidth(20);
        newImageView.setFitHeight(20);
        return newImageView;
    }
}
