package model.Peoples;

import model.Block;
import model.Government;
import model.Peoples.Militia;
import model.Peoples.PeopleType;

public class Launcher extends Militia {

    private int launchRadius;

    Launcher(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
        launchRadius = peopleType.launchRadius;
    }

    public void launch(Block block){

    }

    private boolean checkBeingInRadius (){
        return true;
    }

}
