package project.model.Peoples;

import project.model.Block;
import project.model.Government;

public class Militia extends People {
    private final int attackPower;
    private String state;

    // standing
    // defensive
    // offensive


    Militia(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
        attackPower = peopleType.attackPower;
        state = "standing";
    }


    public void damage(Block block){

    }

    public int getAttackPower() {
        return attackPower;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
