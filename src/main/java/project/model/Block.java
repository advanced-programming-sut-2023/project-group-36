package project.model;

import project.model.Buildings.Structure;
import project.model.Peoples.People;

import java.util.ArrayList;

public class Block {
    private int x;
    private int y;
    private String type;
    private Structure thisBlockStructure;
    private ArrayList<People> peoples;
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
        // ...
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
}
