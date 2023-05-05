package project.model.Buildings;

import project.model.Block;
import project.model.Government;

public class TownBuilding extends Structure {
    String[] TownBuildingStructures={"Hovel","Church","Cathedral"};
    int popularityChange;

    public TownBuilding(int popularityChange, Block block, Government government,int HP,BuildingType buildingType) {
        this.popularityChange = popularityChange;
        super.block=block;
        super.HP=HP;
        super.government=government;
        super.buildingType=buildingType;
    }
}

