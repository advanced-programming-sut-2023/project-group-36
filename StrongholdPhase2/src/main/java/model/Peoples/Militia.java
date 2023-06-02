package model.Peoples;

import model.Block;
import model.Government;

public class Militia extends People {
    private String state;

    // standing
    // defensive
    // offensive


    Militia(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
        state = "standing";
    }


    public void damage(Block block){

    }

    public double getAttackPower() {
        return getPeopleType().attackPower;
    }

    public int getDefencePower(){
        return getPeopleType().defencePower;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void stateTasks(){
        if (this instanceof FightingForce){
            ((FightingForce) this).stateTask();
        }
        else if (this instanceof Launcher){
            ((Launcher) this).stateTask();
        }
        else if (this instanceof Engineer){
            ((Engineer) this).stateTask();
        }
    }
}
