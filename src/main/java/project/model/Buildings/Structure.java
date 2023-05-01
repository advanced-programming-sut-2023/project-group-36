package project.model.Buildings;

import project.model.Block;
import project.model.Government;
import project.model.Peoples.Militia;
import project.model.Peoples.NormalPeople;

import java.util.ArrayList;

public  class Structure {
    public String name;
    Block block;
    int HP;
    Government government;
    public int GoldCost;
    public int WoodCost;
    public int StoneCost;
    public String category;
    public int damage;
    public int popularityChange;
    public int rate;
    public int fireRange;
    public int defenceRange;
    public int capacity;
    public ArrayList<Militia> militias;
    ArrayList<NormalPeople> normalPeople;

    public BuildingType getBuildingType() {
        return buildingType;
    }
    private BuildingType buildingType;




}
