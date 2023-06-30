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

import java.util.Random;

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

    public static Image Sickness = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/textures/Sickness.png")));
    public static Image Fire = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/textures/Fire.png")));


    public static Image Armoury = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/Armoury.png")));

    public static Image Barrack = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/Barrack.png")));

    public static Image Cathedral = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/Cathedral.png")));
    public static Image IronMine = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/IronMine.png")));
    public static Image Hovel = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/Hovel.png")));
    public static Image EngineerGuild = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/EngineerGuild.png")));
    public static Image Church = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/Church.png")));
    public static Image LookoutTower = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/LookoutTower.png")));
    public static Image Market = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/Market.png")));
    public static Image MercenaryPost = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/MercenaryPost.png")));
    public static Image Quarry = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/Quarry.png")));
    public static Image Store = new Image(String.valueOf(CreateNewGameMenu.class.getResource("/images/Buildings/Store.png")));




    private boolean insideBuilding;
    private boolean insideBlock;

    public Rectangle information;

    private final Rectangle buildingInformation = new Rectangle(130,70);

    Text name = new Text();
    Text government = new Text();
    Text HP = new Text();


    Text textOfStructure = new Text();
    Text textOfSoldiers = new Text();

    Text textOfTexture = new Text();



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

        setBlockInformation();



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


    private void setBlockInformation(){


        textOfTexture.setText("Texture: "+block.getType());
        textOfTexture.setX(information.getX() + 10);
        textOfTexture.setY(information.getY() + 60);

        //soldiers
        int numberOfSoldiers = 0;
        for (People people : this.block.getPeoples()) {
            if (!(people instanceof NormalPeople)) {
                numberOfSoldiers++;
            }
        }
        String string = "Soldiers : " + numberOfSoldiers;
        textOfSoldiers.setText(string);
        textOfSoldiers.setX(information.getX() + 10);
        textOfSoldiers.setY(information.getY() + 40);

        //structures
        if (this.getBlock().getThisBlockStructure()!=null){
            String typeOfStructure = this.getBlock().getThisBlockStructure().getName();
            typeOfStructure += " : "+this.getBlock().getThisBlockStructure().getGovernment().getOwner().getUsername();
            textOfStructure.setText(typeOfStructure);
        }
        else {
            textOfStructure.setText("No Building!");
        }
        textOfStructure.setX(information.getX() + 10);
        textOfStructure.setY(information.getY() + 20);

    }

    private void setBuildingInformation(boolean show) {
        if (block.getThisBlockStructure()!=null){
            name.setText("name: "+block.getThisBlockStructure().getName());
            government.setText("government: "+block.getThisBlockStructure().getGovernment().getOwner().getUsername());
            HP.setText("HP: "+block.getThisBlockStructure().getHitPoint());
        }

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
        else if (GameController.inDropBuilding!=null){
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
        else {
            GameController.currentBlock = block;
            BlockMenu blockMenu = new BlockMenu();
            blockMenu.start(new Stage());
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
            return;
        }
        switch (block.getThisBlockStructure().getName()) {
            case "Store" -> building.setImage(GBlock.Store);
            case "IronMine" -> building.setImage(GBlock.IronMine);
            case "Market" -> building.setImage(GBlock.Market);
            case "Cathedral" -> building.setImage(GBlock.Cathedral);
            case "Church" -> building.setImage(GBlock.Church);
            case "Barrack" -> building.setImage(GBlock.Barrack);
            case "Armoury" -> building.setImage(GBlock.Armoury);
            case "Hovel" -> building.setImage(GBlock.Hovel);
            case "EngineerGuild" -> building.setImage(GBlock.EngineerGuild);
            case "Quarry" -> building.setImage(GBlock.Quarry);
            case "LookoutTower" -> building.setImage(GBlock.LookoutTower);
            case "MercenaryPost" -> building.setImage(GBlock.MercenaryPost);

            default -> building.setImage(GBlock.MiddleCastle);
        }
        building.setFitWidth(40);
        building.setFitHeight(40);
    }

    public void setTexture(){
        switch (block.getType()) {
            case "Iron" -> texture.setImage(GBlock.Iron);
            case "Grass" -> texture.setImage(GBlock.Grass);
            case "Boulder" -> texture.setImage(GBlock.Boulder);
            case "Meadow" -> texture.setImage(GBlock.Meadow);
            case "Dense Meadow" -> texture.setImage(GBlock.DenseMeadow);
            case "Gravel" -> texture.setImage(GBlock.Gravel);
            case "Stone" -> texture.setImage(GBlock.Stone);
            default -> texture.setImage(GBlock.Dirt);
        }
        texture.setX(this.getX()+1);
        texture.setY(this.getY()+1);
        texture.setFitWidth(50);
        texture.setFitHeight(50);
    }




    public void update(){
        setBuilding();
        addSicknessSign();
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
            setBlockInformation();
            GameMenu.controller.getMapPane().getChildren().addAll(information,textOfStructure,textOfSoldiers,textOfTexture);
        }
    }

    public void unShowInformation(){
        if (!insideBlock){
            GameMenu.controller.getMapPane().getChildren().removeAll(information,textOfStructure,textOfSoldiers,textOfTexture);
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
        Random random = new Random();
        int ranNum = random.nextInt(100);
        if (ranNum==20){
            texture.setImage(GBlock.Sickness);
            block.sickness();
            setBuilding();
        } else if (ranNum==24) {
            texture.setImage(GBlock.Fire);
            block.fire();
            setBuilding();

        } else{
            setTexture();
            setBuilding();
        }
    }
}
