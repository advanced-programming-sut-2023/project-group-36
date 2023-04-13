package model;

public class People {

    private String speed;
    private String type;
    private String defencePower;
    private Government government;
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
