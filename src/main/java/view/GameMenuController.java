package view;
import controller.EditMap;
import controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Block;
import model.CurrentGovernmentBox;
import model.GBlock;

import java.util.ArrayList;

public class GameMenuController {


    private static ArrayList<GBlock> gBlocks;
    public Pane pane;
    @FXML
    private Pane mapPane = new Pane();

    private CurrentGovernmentBox currentGovernmentBox;



    public void gameInitialize(){

        gBlocks = new ArrayList<>();

        currentGovernmentBox = new CurrentGovernmentBox(700,40,pane);

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                gBlocks.add(new GBlock(getBlockByPosition(i,j),false));
            }
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
        System.out.println("hi");
        GameController.nextTurn();
        currentGovernmentBox.nextGovernment();
    }
}
