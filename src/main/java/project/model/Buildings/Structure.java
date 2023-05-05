package project.model.Buildings;

import project.model.Block;
import project.model.Government;
import project.model.Peoples.Militia;
import project.model.Peoples.NormalPeople;

import java.util.ArrayList;

public  class Structure {
    Block block;
    int HP;
    Government government;
    public ArrayList<Militia> militias;
    public ArrayList<NormalPeople> normalPeople;

    public BuildingType getBuildingType() {
        return buildingType;
    }
     BuildingType buildingType;


    public int getHitPoint() {
        return HP;
    }
}
