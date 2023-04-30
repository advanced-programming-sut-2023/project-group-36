package project.model.Peoples;

import project.model.Block;
import project.model.Government;
import project.model.Peoples.Militia;
import project.model.Peoples.PeopleType;

public class Launcher extends Militia {

    private int launchRadius;

    public Launcher(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
        launchRadius = peopleType.launchRadius;
    }
    public void setLaunchRadius(int increase){
        launchRadius+=increase;
    }

    public void launch(Block block){

    }


    public boolean checkInRange(int x, int y) {
        return true; //......
    }

}
