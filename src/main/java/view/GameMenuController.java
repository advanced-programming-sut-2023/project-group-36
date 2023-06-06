package view;
import controller.EditMap;
import controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Block;
import model.CurrentGovernmentBox;
import model.GBlock;

import java.util.ArrayList;

public class GameMenuController {


    private static ArrayList<GBlock> gBlocks;
    public Pane pane;
    public Label government;
    public ImageView exit;
    @FXML
    private Pane mapPane = new Pane();




    public void gameInitialize(){

        gBlocks = new ArrayList<>();
        government.setText(GameController.currentGovernment.getOwner().getUsername());
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
        GameController.nextTurn();
        government.setText(GameController.currentGovernment.getOwner().getUsername());
    }

    public void exit(MouseEvent mouseEvent) throws Exception {
        new CreateNewGameMenu().start(CreateNewGameMenu.stage);
    }

    public void dropBuilding(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        String packageAddress = "file:/C:/Users/m/Desktop/StrongholdPhase2/target/classes/images/Buildings/";
        String name = imageView.getImage().getUrl().replace(packageAddress,"");
        name = name.replace(".png","");
        System.out.println(name);
        GameController.inDropBuilding = name;

        //GameController.dropBuilding();

        /*/
        GBlock gBlock = ChangeBlockMenu.gBlock;
        if (imageView.equals(Dirt)){
            EditMap.setTexture(gBlock.getBlock(),"Dirt");
        }
        else if (imageView.equals(Grass)){
            EditMap.setTexture(gBlock.getBlock(),"Grass");
        }
        else if (imageView.equals(Gravel)){
            EditMap.setTexture(gBlock.getBlock(),"Gravel");
        }
        else if (imageView.equals(Meadow)){
            EditMap.setTexture(gBlock.getBlock(),"Meadow");
        }
        else if (imageView.equals(Iron)){
            EditMap.setTexture(gBlock.getBlock(),"Iron");
        }
        else if (imageView.equals(Stone)){
            EditMap.setTexture(gBlock.getBlock(),"Stone");
        }
        else if (imageView.equals(Boulder)){
            EditMap.setTexture(gBlock.getBlock(),"Boulder");
        }
        else {
            EditMap.setTexture(gBlock.getBlock(),"Dense Meadow");
        }

         */

    }
}
