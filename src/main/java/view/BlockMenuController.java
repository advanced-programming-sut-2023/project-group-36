package view;

import controller.GameController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class BlockMenuController {

    public ImageView Knight;
    public ImageView Archer;
    public ImageView Assassins;
    public ImageView Engineer;
    public ImageView HorseArchers;
    public ImageView Slaves;
    public ImageView Swordsmen;
    public Button select;
    public Button clear;


    public void menuInitialize() {
        clear.setOnMouseClicked(mouseEvent -> clear());
        select.setOnMouseClicked(mouseEvent -> select());

    }


    public void clear() {
        GameController.currentBlock = null;
        BlockMenu.stage.close();
    }


    public void clickUnit(MouseEvent mouseEvent) {

        ImageView imageView = (ImageView) mouseEvent.getSource();
        String type = null;
        if (imageView.equals(Knight)){
            type = "Knight";
        }
        else if (imageView.equals(Slaves)){
            type = "Slaves";
        }
        else if (imageView.equals(Swordsmen)){
            type = "Swordsmen";
        }
        else if (imageView.equals(Archer)){
            type = "Archer";
        }
        else if (imageView.equals(Assassins)){
            type = "Assassins";
        }
        else if (imageView.equals(Engineer)){
            type = "Engineer";
        }
        else if (imageView.equals(HorseArchers)){
            type = "HorseArchers";
        }
        GameController.inDropUnit = type;
    }


    public void select(){
        String result;
        if (GameController.inDropUnit!=null){
            result = GameController.selectUnit(GameController.inDropUnit);
            BlockMenu.stage.close();
            GameController.currentBlock = null;
            GameController.currentGovernment.showUnits();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,result);
            alert.showAndWait();
        }

    }
}
