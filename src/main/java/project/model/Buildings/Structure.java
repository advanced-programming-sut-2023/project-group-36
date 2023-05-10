package project.model.Buildings;

import project.model.Block;
import project.model.Government;
import project.model.Peoples.Militia;
import project.model.Peoples.NormalPeople;

import java.util.ArrayList;

public  class Structure {
    private String name;
    Block block;
    int HP;
    Government government;
    private ArrayList<Militia> militias;
    private ArrayList<NormalPeople> normalPeoples;
    BuildingType buildingType;

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public String getName() {
        return name;
    }

    public Block getBlock() {
        return block;
    }

    public int getHitPoint() {
        return HP;
    }



    public ArrayList<NormalPeople> getNormalPeople() {
        return normalPeoples;
    }

    public void setNormalPeople(ArrayList<NormalPeople> normalPeople) {
        this.normalPeoples = normalPeople;
    }

    public void removeNormalPeople(NormalPeople normalPeople) {
        normalPeoples.remove(normalPeople);
    }

    public void addNormalPeople(NormalPeople normalPeople) {
        normalPeoples.add(normalPeople);
    }



    public ArrayList<Militia> getMilitias() {
        return militias;
    }

    public void setMilitias(ArrayList<Militia> militias) {
        this.militias = militias;
    }
}
