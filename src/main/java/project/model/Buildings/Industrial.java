package project.model.Buildings;

import model.Block;
import model.Government;

public class Industrial extends Structure {
    String[] IndustrialStuctures={"Market","Ox tether","Woodcutter"};
    int rate;
    int capacity;

    public Industrial(Block block, Government government,int HP, int rate, int capacity) {
        super.block=block;
        super.HP=HP;
        super.government=government;
        this.rate = rate;
        this.capacity = capacity;
    }
}
