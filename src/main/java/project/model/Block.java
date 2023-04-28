package project.model;

import project.model.Buildings.Structure;
import project.model.Peoples.People;

import java.util.ArrayList;

public class Block {
    private int x;
    private int y;
    private String type;
    private Structure thisBlockStructure;
    private int governmentId = 0;
    private ArrayList<People> peoples;
    private String color;

    private String tree;

    public Block(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void nextTurn(){
        for (People people : peoples) {
            people.nextTurn();
        }
    }

    public void addPeople(People people){
        peoples.add(people);
    }

    public void removePeople(People people){
        peoples.remove(people);
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return (type);
    }

    public void underAttack(Government government){

    }

    public int getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(int governmentId) {
        this.governmentId = governmentId;
    }

    public Structure getThisBlockStructure() {
        return thisBlockStructure;
    }

    public void setThisBlockStructure(Structure thisBlockStructure) {
        this.thisBlockStructure = thisBlockStructure;
    }

    public ArrayList<People> getPeoples() {
        return peoples;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean suitableTypeForUnit(String type) {
        if (type.equals("x") || type.equals("y") || type.equals("z")){
            return false;
        }
        return true;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    public boolean suitableTypeForTree() {
        if (type.equals("x") || type.equals("y") || type.equals("z")){
            return false;
        }
        return true;
    }

    public void burn() {
        for (int i = 0; i < peoples.size(); i++) {
            peoples.get(i).hitPointReduce(10);
        }
    }
}
