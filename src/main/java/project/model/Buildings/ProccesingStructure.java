package project.model.Buildings;

import project.model.Block;
import project.model.Government;

public class ProccesingStructure extends Structure{
    public ProccesingStructure(Block block, int HP, Government government, int goldCost, int woodCost, int stoneCost, String category
            , int popularityChange, int rate, int capacity) {
       super.block = block;
        super.HP = HP;
        super.government = government;
        super.GoldCost = goldCost;
        super.WoodCost = woodCost;
        super.StoneCost = stoneCost;
        super.category = category;
        super.popularityChange = popularityChange;
        super.rate = rate;
        super.capacity = capacity;

    }
}
