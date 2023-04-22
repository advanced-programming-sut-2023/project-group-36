package project.model.Peoples;

import project.model.Block;
import project.model.Government;
import project.model.Peoples.Militia;
import project.model.Peoples.PeopleType;

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