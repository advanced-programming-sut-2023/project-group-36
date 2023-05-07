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
    public ArrayList<Militia> militias;
    public ArrayList<NormalPeople> normalPeople;
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
}
