package model;
import controller.GameController;
import javafx.animation.PauseTransition;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Buildings.Structure;
import model.Peoples.NormalPeople;
import model.Peoples.People;
import view.*;

public class GBlock extends Rectangle {
    private Block block;
    private Boolean changeAble;
    private ImageView texture = new ImageView();
    private ImageView building = new ImageView();



    public static Image Iron = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/textures/Iron.png")));

    public static Image Grass = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/textures/Grass.png")));
    public static Image Gravel = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/textures/Gravel.png")));
    public static Image Boulder = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/textures/Boulder.png")));
    public static Image Dirt = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/textures/Dirt.png")));
    public static Image Meadow = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/textures/Meadow.png")));
    public static Image DenseMeadow = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/textures/Dense Meadow.png")));
    public static Image Stone = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/textures/Stone.png")));

    private static Image MiddleCastle = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/buildings/castle.png")));

    public static Image Knight = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Units/Knight.png")));
    public static Image Slaves = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Units/Slaves.png")));
    public static Image Archer = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Units/Archer.png")));
    public static Image Swordsmen = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Units/Swordsmen.png")));
    public static Image Engineer = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Units/Engineer.png")));
    public static Image HorseArchers = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Units/HorseArchers.png")));
    public static Image Assassins = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Units/Assassins.png")));



    private boolean insideBuilding;
    private boolean insideBlock;

    public Rectangle information;

    private final Rectangle buildingInformation = new Rectangle(130,70);

    Text name = new Text();
    Text government = new Text();
    Text HP = new Text();

    public GBlock(Block block, Boolean changeAble){
        super(50,50);
        this.block = block;
        this.setX(50*(block.getX()-1));
        this.setY(50*(block.getY()-1));
        this.setFill(Color.rgb(100,80,60));
        this.changeAble = changeAble;

        setTexture();
        setBuilding();

        information = new Rectangle(130,70);
        information.setFill(Color.BROWN);

        //set x
        if (information.getX() > 50)
            information.setX(this.getX() - 50);
        else
            information.setX(this.getX()+50);

        //set y
        if (information.getY() > 50)
            information.setY(this.getY()-50);
        else
            information.setY(this.getY()+50);

        //soldiers
        int numberOfSoldiers = 0;
        for (People people : this.block.getPeoples()) {
            if (!(people instanceof NormalPeople)) {
                numberOfSoldiers++;
            }
        }
        String string = "Soldiers : " + numberOfSoldiers;
        Text textOfSoldiers = new Text(string);
        textOfSoldiers.setX(information.getX() + 20);
        textOfSoldiers.setY(information.getY() + 15);

        //structures
        String typeOfStructure = this.getBlock().getThisBlockStructure().getBuildingType().getType();
        Text textOfStructure = new Text();
        typeOfStructure += " : 1";
        textOfStructure.setText(typeOfStructure);
        textOfStructure.setX(information.getX() + textOfSoldiers.getLayoutBounds().getWidth() + 10);
        textOfStructure.setY(information.getY() + 15);



        if (changeAble){
            EditMapMenu.controller.getMapPane().getChildren().add(this);
            EditMapMenu.controller.getMapPane().getChildren().add(texture);

        }
        else {
            GameMenu.controller.getMapPane().getChildren().add(this);
            GameMenu.controller.getMapPane().getChildren().add(texture);
            GameMenu.controller.getMapPane().getChildren().add(building);


        }

        setMouseEvents(changeAble);
    }


    private void setBuildingInformation(boolean show) {
        name.setText("name: "+block.getThisBlockStructure().getName());
        government.setText("government: "+block.getThisBlockStructure().getGovernment().getOwner().getUsername());
        HP.setText("HP: "+block.getThisBlockStructure().getHitPoint());

        buildingInformation.setFill(Color.BLUE);
        buildingInformation.setX(this.getX()+50);
        buildingInformation.setY(this.getY()-50);

        name.setX(this.getX()+55);
        name.setY(this.getY()-30);

        government.setX(this.getX()+55);
        government.setY(this.getY()-10);

        HP.setX(this.getX()+55);
        HP.setY(this.getY()+10);

        if (block.getThisBlockStructure()!=null && show){
            GameMenu.controller.getMapPane().getChildren().addAll(buildingInformation,name,government,HP);
        }
        else {
            GameMenu.controller.getMapPane().getChildren().removeAll(buildingInformation,name,government,HP);
        }
    }

    private void textureClick() throws Exception {
        if (changeAble){
            if (ChangeBlockMenu.stage == null){
                ChangeBlockMenu.gBlock = this;
                new ChangeBlockMenu().start(new Stage());
                System.out.println(getX()+","+getY());
            }
        }
        else {
            GameMenu.root.setCursor(Cursor.DEFAULT);
            if (GameController.inDropBuilding==null || block.getThisBlockStructure()!=null){
                return;
            }
            String result = GameController.dropBuilding(GameController.inDropBuilding,this.block);
            if (!result.equals("drop building is done successfully.")){
                System.out.println(result);
                return;
            }
            Image image = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/"+ GameController.inDropBuilding +".png")));
            building.setImage(image);
            GameController.inDropBuilding = null;
            building.setFitWidth(40);
            building.setFitHeight(40);
        }
    }

    private void buildingClick() throws Exception {
        if (block.getThisBlockStructure().getName().equals("middleCastle") || !block.getThisBlockStructure().getGovernment().equals(GameController.currentGovernment)){
            return;
        }
        BuildingMenu buildingMenu = new BuildingMenu();
        buildingMenu.start(new Stage());
    }



    public void setBuilding(){
        building.setX(this.getX()+5);
        building.setY(this.getY()+5);
        building.setFitWidth(1);
        building.setFitHeight(1);
        building.setImage(GBlock.Iron);
        if (block.getThisBlockStructure()==null || block.getThisBlockStructure().getName()==null){
            System.out.println("null!");
            return;
        }
        switch (block.getThisBlockStructure().getName()) {
            case "Iron" -> building.setImage(GBlock.Iron);
            case "Grass" -> building.setImage(GBlock.Grass);
            case "Dirt" -> building.setImage(GBlock.Dirt);
            case "Meadow" -> building.setImage(GBlock.Meadow);
            case "Dense Meadow" -> building.setImage(GBlock.DenseMeadow);
            case "Gravel" -> building.setImage(GBlock.Gravel);
            case "Stone" -> building.setImage(GBlock.Stone);
            case "middleCastle" -> building.setImage(GBlock.MiddleCastle);
            default -> building.setImage(GBlock.Boulder);
        }
        building.setFitWidth(40);
        building.setFitHeight(40);
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
        texture.setFitWidth(50);
        texture.setFitHeight(50);
    }

    public void showUnits(){
        System.out.println(block.getPeoples().size());
        for (int i = 0; i < block.getPeoples().size(); i++) {
            block.getPeoples().get(i).show();
        }
    }



    public void update(){
        setTexture();
        setBuilding();
    }


    public void setMouseEvents(Boolean on){
        if (on){
            texture.setOnMouseClicked(event -> {
                try {
                    textureClick();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
        else {
            texture.setOnMouseClicked(event -> {
                try {
                    textureClick();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            building.setOnMouseClicked(event -> {
                try {
                    buildingClick();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            PauseTransition pause1 = new PauseTransition(Duration.seconds(2));
            PauseTransition pause2 = new PauseTransition(Duration.seconds(2));
            pause1.setOnFinished(event -> showInformation());
            pause2.setOnFinished(event -> showBuildingInformation());


            texture.setOnMouseEntered(e -> {
                insideBlock = true;
                pause1.playFromStart();
            });
            texture.setOnMouseExited(e -> {
                insideBlock = false;
                unShowInformation();
            });


            building.setOnMouseEntered(e -> {
                insideBuilding = true;
                pause2.playFromStart();
            });
            building.setOnMouseExited(e -> {
                insideBuilding = false;
                unShowBuildingInformation();
            });

        }
    }


    private void showBuildingInformation() {
        if (insideBuilding){
            setBuildingInformation(true);
        }
    }

    private void unShowBuildingInformation() {
        if (!insideBuilding){
            setBuildingInformation(false);

        }
    }


    public Block getBlock() {
        return block;
    }


    public void showInformation(){
        if (insideBlock){
            GameMenu.controller.getMapPane().getChildren().add(information);
        }
    }

    public void unShowInformation(){
        if (!insideBlock){
            GameMenu.controller.getMapPane().getChildren().remove(information);
        }
    }

    public void zoom(double scale) {
        building.setFitWidth(building.getFitWidth()*scale);
        building.setFitHeight(building.getFitHeight()*scale);
        texture.setFitWidth(texture.getFitWidth()*scale);
        texture.setFitHeight(texture.getFitHeight()*scale);
        this.prefHeight(this.getHeight()*scale);
        this.prefWidth(this.getWidth()*scale);

        this.setX(getX()*scale);
        this.setY(getY()*scale);
        building.setX(building.getX()*scale);
        building.setY(building.getY()*scale);
        texture.setX(texture.getX()*scale);
        texture.setY(texture.getY()*scale);
    }

    public void addSicknessSign() {

    }
}
