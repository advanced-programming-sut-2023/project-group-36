package model.Peoples;

import controller.GameController;
import model.Block;
import model.Game;
import model.Government;
import model.Tools;

import java.util.ArrayList;

public class FightingForce extends Militia {

    public FightingForce(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
    }

    public void attack(Block block){
        this.startMove(block);
    }

    public void stateTask(){
        Game game = GameController.getGame();
        int radius = 0;
        if (this.getState().equals("standing")){
            return;
        }
        if (this.getState().equals("defensive")){
            radius = 2;
        }
        else if (this.getState().equals("offensive")){
            radius = 5;
        }
        ArrayList<Block> blocks = Tools.getBlacksInRadius(game.getMap().getSize(),this.getBlock().getX(),this.getBlock().getY(),radius,game.getMap());
        for (Block block : blocks) {
            if (block.myEnemies(this.getGovernment()) != null) {
                attack(block);
            }
        }
    }
}
