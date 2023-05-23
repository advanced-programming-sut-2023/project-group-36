package project.model;

import project.model.Buildings.Structure;
import project.model.Peoples.Militia;
import project.model.Peoples.People;

import java.util.ArrayList;

public class Block {
    private int x;
    private int y;
    private boolean ThereIsTunnel;

    public boolean isThereIsTunnel() {
        return ThereIsTunnel;
    }

    public void setThereIsTunnel(boolean thereIsTunnel) {
        ThereIsTunnel = thereIsTunnel;
    }

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
        battles();
    }

    private void battles() {
        People people1;
        People people2;
        for (int i = 0; i < peoples.size(); i++) {
            people1 = peoples.get(i);
            for (int j = i; j < peoples.size(); j++) {
                people2 = peoples.get(i);
                if (!people1.getGovernment().equals(people2.getGovernment())){
                    battleTwoUnit(people1,people2);
                }
            }
        }
    }

    private void battleTwoUnit(People people1, People people2) {
        if (people1 instanceof Militia && people2 instanceof Militia) {
            people1.hitPointReduce(((Militia) people2).getAttackPower() - ((Militia) people1).getDefencePower());
            people2.hitPointReduce(((Militia) people1).getAttackPower() - ((Militia) people2).getDefencePower());
        }
        else if (people2 instanceof Militia) {
            people1.hitPointReduce(((Militia) people2).getAttackPower());
        }
        else if (people1 instanceof Militia) {
            people2.hitPointReduce(((Militia) people1).getAttackPower());
        }
    }

    public void addPeople(People people){
        if (peoples==null){
            peoples= new ArrayList<>();
        }
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
        for (People people : peoples) {
            people.hitPointReduce(10);
        }
    }

    public boolean isPassable(People people) {
        ArrayList<String> badTypes = new ArrayList<>();
        badTypes.add("type1");
        badTypes.add("type2");
        badTypes.add("type3");
        return !badTypes.contains(type); ///.....
    }

    public ArrayList<People> myEnemies(Government government) {
        ArrayList<People> enemies = new ArrayList<>();
        if(peoples == null)
            return null;
        for (People people : peoples) {
            if (!people.getGovernment().equals(government)) {
                enemies.add(people);
            }
        }
        return enemies;
    }
}
