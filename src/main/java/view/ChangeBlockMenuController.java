package view;

import controller.CreateNewGame;
import controller.EditMap;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.GBlock;

public class ChangeBlockMenuController {

    public ImageView Dirt;
    public ImageView Boulder;
    public ImageView Grass;
    public ImageView Gravel;
    public ImageView Meadow;
    public ImageView Stone;
    public ImageView Iron;
    public ImageView Dense_Meadow;
    public Button setGovernment;
    public Label government;

    public void menuInitialize() {
        government.setText(CreateNewGame.governments.get(EditMap.number).getOwner().getUsername());
    }

    public void Back(MouseEvent mouseEvent) {
        ChangeBlockMenu.stage.close();
        ChangeBlockMenu.stage = null;
        ChangeBlockMenu.gBlock.setTexture();
    }

    public void setGovernment(MouseEvent mouseEvent) {
        GBlock gBlock = ChangeBlockMenu.gBlock;
        EditMap.setGovernment(gBlock.getBlock(), CreateNewGame.governments.get(EditMap.number));
        government.setText(CreateNewGame.governments.get(EditMap.number-1).getOwner().getUsername());
        government.setText("");
        if (EditMap.number==CreateNewGame.governments.size()){
            setGovernment.setOnMouseClicked(null);
        }
    }

    public void clear(MouseEvent mouseEvent) {
        //ChangeBlockMenu.
    }

    public void changeTexture(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
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
    }
}
