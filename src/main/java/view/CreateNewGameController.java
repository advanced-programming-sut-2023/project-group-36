package view;

import controller.CreateNewGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CreateNewGameController {
    public TextField username;
    @FXML
    private Label message;

    public void menuInitialize() {
        message.setText("");
    }

    public void create(MouseEvent mouseEvent) throws Exception {
        new GameMenu().start(CreateNewGameMenu.stage);
    }

    public void addUser(MouseEvent mouseEvent) {
        if (CreateNewGame.addUser(username.getText())==null){
            message.setText("User "+username.getText()+"added successfully.");
            return;
        }
        message.setText(CreateNewGame.addUser(username.getText()));
    }



}
