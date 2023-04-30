package project.model.Buildings;

import project.model.Block;
import project.model.Government;
import project.model.Peoples.Launcher;
import project.model.Peoples.Militia;

public class DefenciveStructure extends Structure {
    String[] DefenciveStructures = {"Small stone gatehouse", "big stone gatehouse", "Drawbridge", "lookout tower", "perimeter tower",
                                    "turret","defence tower","square tower","circle tower"};
    int fire_range;
    int defence_range;

    public DefenciveStructure(Block block, int HP, int fire_range, int defence_range, Government government) {
        super.block=block;
        super.HP=HP;
        this.defence_range=defence_range;
        this.fire_range=fire_range;
        super.government=government;
    }
    public void AttackRadiusIncrease(){
        for(Militia x : super.militias){
            if(x instanceof Launcher)
                ((Launcher) x).setLaunchRadius(fire_range);
        }
    }
}