package model;

public class People {

    private int speed;
    private int type;
    private int defencePower;
    private Government government;
    private int price;
    private Block block;
    private boolean inMove;
    private boolean employed;
    private Block destination;
    public void startMove(Block block){
        destination = block;
    }

    public void thisTurnMove(){
        if (block.equals(destination)){
            inMove = false;
        }
    }



}
