package model.Peoples;

import model.Block;
import model.Government;
import model.Peoples.Militia;
import model.Peoples.PeopleType;

public class FightingForce extends Militia {

    FightingForce(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
    }

    public void attack(Block block){

    }
}
