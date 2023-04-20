package project.model.Buildings;

import project.model.Block;
import project.model.Government;
import project.model.Peoples.NormalPeople;

import java.util.ArrayList;

public class Farm_Extraction extends Structure{
    String[] Farm_ExtractionStructures={""};
    ArrayList<NormalPeople> normalPeople;
    int rate;
    public Farm_Extraction(Block block, Government government,int rate,int HP) {
        this.rate=rate;
        super.block=block;
        super.HP=HP;
        super.government=government;
    }
}
