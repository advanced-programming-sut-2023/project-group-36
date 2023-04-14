package model;

public class People {

    private int speed;
    private int type;
    private int defencePower;
    private Government government;
    private int price;
    private int hitPoint;
    private Block block;
    private boolean inMove;
    private boolean employed;
    private Block destination;
    public void startMove(Block block){
        destination = block;
        inMove = true;
    }

    public void endMove(){
        destination = null;
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





}
