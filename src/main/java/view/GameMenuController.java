package view;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import model.Block;
import model.GBlock;

import java.util.ArrayList;

public class GameMenuController {


    private static ArrayList<Block> blocks;
    private static ArrayList<GBlock> gBlocks;
    @FXML
    private  Pane pane = new Pane();
    @FXML
    private Pane mapPane = new Pane();


    public void gameInitialize(){

        blocks = new ArrayList<>();
        gBlocks = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                blocks.add(new Block(i,j));
            }
        }


        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                gBlocks.add(new GBlock(getBlockByPosition(i,j),false));
            }
        }





    }

    public static Block getBlockByPosition ( int x, int y){
        for (Block block : blocks) {
            if (block.getX() == x && block.getY() == y) {
                return block;
            }
        }
        return null;
    }


    public Pane getMapPane() {
        return mapPane;
    }
}
