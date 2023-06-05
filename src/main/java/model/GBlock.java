package model;
import controller.EditMap;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.ChangeBlockMenu;
import view.EditMapMenu;
import view.GameMenu;
import view.GameMenuController;

public class GBlock extends Rectangle {
    private Block block;
    private Boolean changeAble;
    private ImageView texture = new ImageView();
    private ImageView building = new ImageView();
    private Label label;

    public Rectangle information;

    public static Image Iron = new Image("C:/Users/m/Desktop/GameMenu/src/main/resources/images/textures/Iron.png");
    public static Image Grass = new Image("C:/Users/m/Desktop/GameMenu/src/main/resources/images/textures/Grass.png");
    public static Image Gravel = new Image("C:/Users/m/Desktop/GameMenu/src/main/resources/images/textures/Gravel.png");
    public static Image Boulder = new Image("C:/Users/m/Desktop/GameMenu/src/main/resources/images/textures/Boulder.png");
    public static Image Dirt = new Image("C:/Users/m/Desktop/GameMenu/src/main/resources/images/textures/Dirt.png");
    public static Image Meadow = new Image("C:/Users/m/Desktop/GameMenu/src/main/resources/images/textures/Meadow.png");
    public static Image DenseMeadow = new Image("C:/Users/m/Desktop/GameMenu/src/main/resources/images/textures/Dense Meadow.png");
    public static Image Stone = new Image("C:/Users/m/Desktop/GameMenu/src/main/resources/images/textures/Stone.png");



    public GBlock(Block block, Boolean changeAble){
        super(50,50);
        this.block = block;
        this.setX(50*(block.getX()-1));
        this.setY(50*(block.getY()-1));
        this.setFill(Color.rgb(100,80,60));
        this.changeAble = changeAble;
        setTexture();

        information = new Rectangle(130,70);
        information.setFill(Color.BROWN);
        information.setX(this.getX()+50);
        information.setY(this.getY()-50);


        //setBuilding();
        if (changeAble){
            EditMapMenu.controller.getMapPane().getChildren().add(this);
            EditMapMenu.controller.getMapPane().getChildren().add(texture);

        }
        else {
            GameMenu.controller.getMapPane().getChildren().add(this);
            GameMenu.controller.getMapPane().getChildren().add(texture);

        }

        //Game.pane.getChildren().add(building);

        setMouseEvents(changeAble);
    }

    private void click() throws Exception {
        if (changeAble){
            if (ChangeBlockMenu.stage == null){
                ChangeBlockMenu.gBlock = this;
                new ChangeBlockMenu().start(new Stage());
                System.out.println(getX()+","+getY());
            }
        }
        else {
            this.setFill(Color.YELLOW);
            image();
        }
    }

    public void image(){
        ImageView imageView = new ImageView("C:/Users/m/Desktop/GameMenu/src/main/resources/images/suggested/images.png");
        imageView.setX(this.getX());
        imageView.setY(this.getY());
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        GameMenu.controller.getMapPane().getChildren().add(imageView);
    }

    public void setBuilding(){

        this.label = new Label(block.getThisBlockStructure().getName());
        label.setLayoutX(50*(block.getX()-1));
        label.setLayoutY(50*(block.getY()-1));
        label.setStyle("-fx-font-size: 10");
        //Game.mapPane.getChildren().add(label);
    }

    public void setTexture(){
        switch (block.getType()) {
            case "Iron" -> texture.setImage(GBlock.Iron);
            case "Grass" -> texture.setImage(GBlock.Grass);
            case "Dirt" -> texture.setImage(GBlock.Dirt);
            case "Meadow" -> texture.setImage(GBlock.Meadow);
            case "Dense Meadow" -> texture.setImage(GBlock.DenseMeadow);
            case "Gravel" -> texture.setImage(GBlock.Gravel);
            case "Stone" -> texture.setImage(GBlock.Stone);
            default -> texture.setImage(GBlock.Boulder);
        }
        texture.setX(this.getX()+1);
        texture.setY(this.getY()+1);
        texture.setFitWidth(48);
        texture.setFitHeight(48);
    }

    public void update(){
        setTexture();
        setBuilding();
    }

    public void shift(int x , int y){
        //this.block = Game.getBlockByPosition(block.getX()+x, block.getY()+y);
        update();
    }

    public void setMouseEvents(Boolean on){
        if (on){
            this.texture.setOnMouseClicked(event -> {
                try {
                    click();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
        else {
            this.texture.setOnMouseClicked(null);
            this.texture.setOnMouseEntered(event -> showInformation());
            this.texture.setOnMouseExited(event -> unShowInformation());

        }
    }


    public Block getBlock() {
        return block;
    }


    public void showInformation(){
        GameMenu.controller.getMapPane().getChildren().add(information);
    }

    public void unShowInformation(){
        GameMenu.controller.getMapPane().getChildren().remove(information);
    }

}
