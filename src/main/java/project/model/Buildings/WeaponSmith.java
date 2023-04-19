package project.model.Buildings;

import model.Block;
import model.Government;
import model.Peoples.People;

public class WeaponSmith extends Structure {
    String[] WeaponSmithStructures={"armourer","blacksmith","Fletcher","Poleturner"};
    People output;
    public WeaponSmith(int HP, Government government, Block block,People people) {
        super.block=block;
        super.HP=HP;
        super.government=government;
        this.output=people;
    }
}
