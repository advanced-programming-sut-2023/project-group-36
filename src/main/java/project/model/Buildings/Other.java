package project.model.Buildings;

import model.Block;
import model.Government;

public class Other extends Structure{
    public Other(Block block, Government government,int HP) {
        super.block=block;
        super.HP=HP;
        super.government=government;
    }
}
