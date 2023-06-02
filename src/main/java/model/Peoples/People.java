package model.Peoples;

import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class People {
    private PeopleType peopleType;
    private Government government;
    private double hitPoint;
    private Block block;
    private boolean inMove;

    private boolean inPatrol;
    private Block destination1;
    private Block destination2;

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
}
