package project.model.Peoples;

import project.model.Block;
import project.model.Government;
import project.model.Peoples.Militia;
import project.model.Peoples.PeopleType;

public class FightingForce extends Militia {

    public FightingForce(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
    }

    public void attack(Block block){

    }
}
