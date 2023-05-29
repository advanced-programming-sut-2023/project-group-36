package model;

import model.Peoples.Militia;
import model.Peoples.NormalPeople;

import java.util.ArrayList;

public class Structure {
    Block block;
    int HP;
    Government government;
    private ArrayList<Militia> militias;
    private ArrayList<NormalPeople> normalPeoples;
    BuildingType buildingType;

    public Structure(int HP) {
        this.HP = HP;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public String getName() {
        return buildingType.getType();
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Structure(Block block, Government government, ArrayList<Militia> militias, ArrayList<NormalPeople> normalPeoples, BuildingType buildingType) {
        this.block = block;
        this.government = government;
        this.militias = militias;
        this.normalPeoples = normalPeoples;
        this.buildingType = buildingType;
        block.setThisBlockStructure(this);
        this.HP=500;
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

    public Government getGovernment() {
        return government;
    }
}
