package view;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Block;
import model.GBlock;

import java.util.ArrayList;

public class EditMapController {


    private static ArrayList<Block> blocks;
    private static ArrayList<GBlock> gBlocks;

    @FXML
    private Pane mapPane = new Pane();

    public void menuInitialize() {

        blocks = new ArrayList<>();
        gBlocks = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                blocks.add(new Block(i,j));
            }
        }


        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                gBlocks.add(new GBlock(getBlockByPosition(i,j),true));
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

    public void back(MouseEvent mouseEvent) throws Exception {
        new CreateNewGameMenu().start(EditMapMenu.stage);
    }

    public void startGame(MouseEvent mouseEvent) throws Exception {
        new GameMenu().start(EditMapMenu.stage);
    }

}
