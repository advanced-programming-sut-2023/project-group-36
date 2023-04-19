package project.model.Buildings;

import model.Block;
import model.Government;

public class TownBuilding extends Structure {
    String[] TownBuildingStructures={"Hovel","Church","Cathedral"};
    int popularityChange;

    public TownBuilding(int popularityChange, Block block, Government government,int HP) {
        this.popularityChange = popularityChange;
        super.block=block;
        super.HP=HP;
        super.government=government;
    }
}

