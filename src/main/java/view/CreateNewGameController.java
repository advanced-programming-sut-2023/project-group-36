package view;

import controller.CreateNewGame;
import controller.EditMap;
import controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Game;

public class CreateNewGameController {
    public TextField username;
    @FXML
    private Label message;

    String[] colors = {"red","blue","green","yellow","orange"};


    public void menuInitialize() {
        message.setText("Create new game");
        ////
    }

    public void create(MouseEvent mouseEvent) throws Exception {
        if (CreateNewGame.governments.size()<2){
            message.setText("Please choose least 2 player!");
            return;
        }
        EditMap.setMap();
        Game game = new Game(EditMap.map,CreateNewGame.governments);
        GameController.setGame(game);
        new EditMapMenu().start(CreateNewGameMenu.stage);
    }

    public void addUser(MouseEvent mouseEvent) {
        if (CreateNewGame.addUser(username.getText(),colors[CreateNewGame.governments.size()])==null){
            message.setText("User "+username.getText()+" added successfully.");
            return;
        }
        message.setText(CreateNewGame.addUser(username.getText(),colors[CreateNewGame.governments.size()]));
    }

}
