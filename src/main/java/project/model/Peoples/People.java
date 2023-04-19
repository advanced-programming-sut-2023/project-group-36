package project.model.Peoples;

import project.model.ApplicationManager;
import project.model.Block;
import project.model.Government;

public class People {

    private PeopleType peopleType;
    private Government government;
    private int hitPoint;
    private Block block;
    private boolean inMove;
    private Block destination;
    public void startMove(Block block){
        destination = block;
        inMove = true;
    }


    People (PeopleType peopleType, Government government, Block block){
        this.peopleType = peopleType;
        this.government = government;
        inMove = false;
        hitPoint = 25;
        // ... for example
    }




    public void endMove(){
        destination = null;
        inMove = false;
    }

    public void thisTurnMove(){
        if (block.equals(destination)){
            inMove = false;
            endMove();
            return;
        }
        block = ApplicationManager.getCurrentGame().getMap().getBlockByPosition(block.getX(), block.getY());
        // No changed...
    }

    public boolean checkIsDead(){
        return hitPoint <= 0;
    }

    public void nextTurn(){
        thisTurnMove();
        checkIsDead();
    }

    public void hitPointReduce(int amount){
        hitPoint -= Math.max(0,(amount - peopleType.defencePower));
    }


    public int getHitPoint() {
        return hitPoint;
    }

    public Government getGovernment() {
        return government;
    }

}
