package project.model.Buildings;

import project.model.Block;
import project.model.Government;
import project.model.Peoples.Militia;

import java.util.ArrayList;

public abstract class Structure {
    Block block;
    int HP;
    Government government;
    int GoldCost;
    int WoodCost;
    int StoneCost;
    ArrayList<Militia> militias;


}
