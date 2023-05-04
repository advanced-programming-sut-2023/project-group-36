package project.model.Peoples;

import project.controller.GameController;
import project.model.Block;
import project.model.Game;
import project.model.Government;
import project.model.Tools;
import project.view.GameMenu;

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
