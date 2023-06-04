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
        this.setFill(Color.BLUE);
        this.changeAble = changeAble;
        setTexture();
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

        setMouseEvents(true);
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

    public void enter(){
        this.setFill(Color.GREEN);
    }

    public void exit(){
        this.setFill(Color.BLUE);
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
        texture.setX(this.getX());
        texture.setY(this.getY());
        texture.setFitWidth(50);
        texture.setFitHeight(50);
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
            this.texture.setOnMouseEntered(event -> enter());
            this.texture.setOnMouseExited(event -> exit());
            this.texture.setOnMouseClicked(event -> {
                try {
                    click();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
        else {
            this.texture.setOnMouseEntered(null);
            this.texture.setOnMouseExited(null);
            this.texture.setOnMouseClicked(null);
        }
    }


    public Block getBlock() {
        return block;
    }
}
