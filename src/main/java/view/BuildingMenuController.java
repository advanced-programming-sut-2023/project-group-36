package view;

import controller.CreateNewGame;
import controller.EditMap;
import controller.GameController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Buildings.Structure;
import model.GBlock;

public class BuildingMenuController {

    public ImageView Knight;
    public ImageView Archer;
    public ImageView Assassins;
    public ImageView Engineer;
    public ImageView HorseArchers;
    public ImageView Slaves;
    public ImageView Swordsmen;
    public Text number;
    public Button remove;
    public Button add;
    public Button create;
    public Button destroy;
    public Button repair;
    private int count = 0;


    public void menuInitialize() {
        add.setOnMouseClicked(mouseEvent -> add());
        remove.setOnMouseClicked(mouseEvent -> remove());
        repair.setOnMouseClicked(mouseEvent -> repair());
        destroy.setOnMouseClicked(mouseEvent -> destroy());
        create.setOnMouseClicked(mouseEvent -> dropUnit());

    }


    public void repair() {
        BuildingMenu.stage.close();
    }

    private void destroy() {
        BuildingMenu.stage.close();
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
        System.out.println(type);
        GameController.inDropUnit = type;
    }

    public void add(){
        if (count<8){
            count++;
            number.setText(count+"");
        }
    }

    public void remove(){
        if (count>0){
            count--;
            number.setText(count+"");
        }
    }

    public void dropUnit(){
        String result;
        if (GameController.inDropUnit!=null && count>0){
            result = GameController.createUnit(GameController.inDropUnit,count);
            BuildingMenu.stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,result);
            alert.showAndWait();
        }

    }
}
