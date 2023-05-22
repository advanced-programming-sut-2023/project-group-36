package model.Peoples;

import controller.GameController;
import model.Block;
import model.Game;
import model.Government;
import model.Tools;

import java.util.ArrayList;

public class Launcher extends Militia {

    private int launchRadius;

    public Launcher(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
        launchRadius = peopleType.launchRadius;
    }
    public void setLaunchRadius(int increase){
        launchRadius+=increase;
    }

    public void resetLaunchRadius(){
        this.launchRadius = this.getPeopleType().launchRadius;
    }

    public void launch(Block block){

    }


    public boolean checkInRange(int x, int y) {
        return true; //......
    }


    public void stateTask(){
        Game game = GameController.getGame();
        int radius = launchRadius;
        ArrayList<Block> blocks = Tools.getBlacksInRadius(game.getMap().getSize(),this.getBlock().getX(),this.getBlock().getY(),radius,game.getMap());
        for (Block block : blocks) {
            if (block.myEnemies(this.getGovernment()) != null) {
                launch(block);
            }
        }
    }

}
