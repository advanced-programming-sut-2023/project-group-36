package model.Peoples;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.*;
import view.GameMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


public class People {
    private PeopleType peopleType;
    private Government government;
    private double hitPoint;
    private Block block;
    private boolean inMove;

    private boolean inPatrol;
    private Block destination1;
    private Block destination2;

    private final ImageView imageView = new ImageView();

    Text name = new Text();
    Text governmentName = new Text();
    Text HP = new Text();

    private boolean inside;

    private final Rectangle information = new Rectangle(130,70);








    private boolean selected;
    public void startMove(Block block){
        destination1 = block;
        inMove = true;
    }

    public void startPatrol(Block block1 , Block block2){
        destination1 = block1;
        destination2 = block2;
        inPatrol = true;
        startMove(block1);
    }


    People (PeopleType peopleType, Government government, Block block){
        this.peopleType = peopleType;
        this.government = government;
        this.block = block;
        inMove = false;
        hitPoint = 25;
        selected = false;
        switch (this.getPeopleType().type) {
            case "Knight" -> imageView.setImage(GBlock.Knight);
            case "Slaves" -> imageView.setImage(GBlock.Slaves);
            case "Archer" -> imageView.setImage(GBlock.Archer);
            case "Engineer" -> imageView.setImage(GBlock.Engineer);
            case "Swordsmen" -> imageView.setImage(GBlock.Swordsmen);
            case "Assassins" -> imageView.setImage(GBlock.Assassins);
            default -> imageView.setImage(GBlock.HorseArchers);
        }
        setImageMouse();
    }

    public String isInMove() {
        if (inMove)
            return "M";
        return "S";
    }
    public void endMove(){
        if (inPatrol){
            Block temp = destination1;
            destination1 = destination2;
            destination2 = temp;
            startMove(destination1);
            return;
        }
        destination1 = null;
        inMove = false;
    }

    public void thisTurnMove(){
        int num = 0;
        ArrayList<Block> path;
        while (!block.equals(destination1)  && destination1!=null){
            path = findPath(ApplicationManager.getCurrentGame().getMap(), block.getX(), block.getY(), destination1.getX(), destination1.getY());
            if (path==null){
                destination1 = null;
                destination2 = null;
                inMove = false;
                return;
            }
            if (num>peopleType.speed*20){
                return;
            }
            /*
                 System.out.println(block.getX()+","+block.getY());
             */
            block.removePeople(this);
            block = path.get(path.size()-1);
            block.addPeople(this);
            num ++;
        }
        if (block.equals(destination1)){
            inMove = false;
            System.out.println("end move!");
            endMove();
        }
    }

    public boolean checkIsDead(){
        return (hitPoint <= 0);
    }

    public void nextTurn(){
        thisTurnMove();
        if(checkIsDead()){
            death();
        }
        if (this instanceof Militia){
            ((Militia) this).stateTasks();
        }
    }

    private void death() {
        government.removePeople(this);
        block.removePeople(this);
    }

    public void hitPointReduce(double amount){
        hitPoint -= Math.max(0,(amount - peopleType.defencePower));
        System.out.println(hitPoint);
    }


    public double getHitPoint() {
        return hitPoint;
    }

    public Government getGovernment() {
        return government;
    }

    public void select(){
        selected = true;
    }

    public void reSelect(){
        selected = false;
    }

    public boolean IsSelected() {
        return selected;
    }

    public void stop() {
        inMove = false;
        inPatrol = false;
        destination1 = null;
        destination2 = null;
    }

    public Block getBlock() {
        return block;
    }

    private ArrayList<Block> findPath(Map map, int startX, int startY, int endX, int endY) {
        ArrayList<Block> path = new ArrayList<>();
        return path;
    }

    private ArrayList<Block> getNeighbors(int x , int y, Map map) {
        return Tools.getBlacksInRadius(map.getSize(), x, y , 1 ,map);
    }


    public PeopleType getPeopleType() {
        return peopleType;
    }


    public void show(){
        //if (block.getThisBlockStructure()!=null){
            //return;
        //}
        Random random = new Random();
        imageView.setFitHeight(35);
        imageView.setFitWidth(20);
        imageView.setX((block.getX()-1)*50+random.nextInt(50));
        imageView.setY((block.getY()-1)*50+random.nextInt(50));
        if (!GameMenu.controller.getMapPane().getChildren().contains(imageView)){
            GameMenu.controller.getMapPane().getChildren().add(imageView);
        }
    }

    public void unShow(){
        GameMenu.controller.getMapPane().getChildren().remove(imageView);
    }




    public void setImageMouse(){
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> showInformation());
        imageView.setOnMouseEntered(e -> {
            inside = true;
            pause.playFromStart();
        });
        imageView.setOnMouseExited(e -> {
            inside = false;
            unShowInformation();
        });
    }



    private void setBuildingInformation(boolean show) {
        name.setText("name: "+peopleType.type);
        governmentName.setText("government: "+block.getThisBlockStructure().getGovernment().getOwner().getUsername());
        HP.setText("HP: "+hitPoint);

        information.setFill(Color.GREENYELLOW);
        information.setX(imageView.getX()+50);
        information.setY(imageView.getY()-50);

        name.setX(imageView.getX()+55);
        name.setY(imageView.getY()-30);

        governmentName.setX(imageView.getX()+55);
        governmentName.setY(imageView.getY()-10);

        HP.setX(imageView.getX()+55);
        HP.setY(imageView.getY()+10);

        if (block.getThisBlockStructure()!=null && show){
            GameMenu.controller.getMapPane().getChildren().addAll(information,name,governmentName,HP);
        }
        else {
            GameMenu.controller.getMapPane().getChildren().removeAll(information,name,governmentName,HP);
        }
    }



    public void showInformation(){
        if (inside){
            setBuildingInformation(true);
        }
    }

    public void unShowInformation(){
        if (!inside){
            setBuildingInformation(false);
        }

    }

    public void zoom(double scale) {
        imageView.setFitHeight(35*scale);
        imageView.setFitWidth(20*scale);
        imageView.setX(imageView.getX()*scale);
        imageView.setY(imageView.getY()*scale);
    }

}
