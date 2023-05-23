package project.model.Peoples;

import project.controller.GameController;
import project.model.*;

public class Engineer extends Militia{

    private boolean oil;

    Engineer(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
    }

    public void pourOil(String direction, Map map){
        oil = false;
        int x = this.getBlock().getX();
        int y = this.getBlock().getY();
        switch (direction) {
            case "n":
                map.getBlockByPosition(x, y + 1).burn();
                map.getBlockByPosition(x, y + 2).burn();
                map.getBlockByPosition(x, y + 3).burn();
                break;
            case "s":
                map.getBlockByPosition(x, y - 1).burn();
                map.getBlockByPosition(x, y - 2).burn();
                map.getBlockByPosition(x, y - 3).burn();
                break;
            case "l":
                map.getBlockByPosition(x - 1, y).burn();
                map.getBlockByPosition(x - 2, y).burn();
                map.getBlockByPosition(x - 3, y).burn();
                break;
            case "r":
                map.getBlockByPosition(x + 1, y).burn();
                map.getBlockByPosition(x + 2, y).burn();
                map.getBlockByPosition(x + 3, y).burn();
                break;
        }

    }

    public void stateTask(){
        Game game = GameController.getGame();
        Map map = game.getMap();
        int required = 0;
        if (this.getState().equals("defensive")){
            required = 3;
        }
        else if (this.getState().equals("offensive")){
            required = 1;
        }
        if (numberOfEnemies("n")>=required){
            pourOil("n",map);
        }
        if (numberOfEnemies("s")>=required){
            pourOil("s",map);
        }
        if (numberOfEnemies("l")>=required){
            pourOil("l",map);
        }
        if (numberOfEnemies("r")>=required){
            pourOil("r",map);
        }
    }

    private int numberOfEnemies(String direction){
        Map map = GameController.getGame().getMap();
        int x = this.getBlock().getX();
        int y = this.getBlock().getY();
        int number = 0;
        Block block;
        if (direction.equals("n")){
            block = map.getBlockByPosition(x,y+1);
            number += block.myEnemies(this.getGovernment()).size();
            block = map.getBlockByPosition(x,y+2);
            number += block.myEnemies(this.getGovernment()).size();
            block = map.getBlockByPosition(x,y+3);
            number += block.myEnemies(this.getGovernment()).size();
        }
        else if (direction.equals("s")){
            block = map.getBlockByPosition(x,y-1);
            number += block.myEnemies(this.getGovernment()).size();
            block = map.getBlockByPosition(x,y-2);
            number += block.myEnemies(this.getGovernment()).size();
            block = map.getBlockByPosition(x,y-3);
            number += block.myEnemies(this.getGovernment()).size();
        }
        else if (direction.equals("l")){
            block = map.getBlockByPosition(x-1,y);
            number += block.myEnemies(this.getGovernment()).size();
            block = map.getBlockByPosition(x-2,y);
            number += block.myEnemies(this.getGovernment()).size();
            block = map.getBlockByPosition(x-3,y);
            number += block.myEnemies(this.getGovernment()).size();
        }
        else if (direction.equals("r")){
            block = map.getBlockByPosition(x+1,y);
            number += block.myEnemies(this.getGovernment()).size();
            block = map.getBlockByPosition(x+2,y);
            number += block.myEnemies(this.getGovernment()).size();
            block = map.getBlockByPosition(x+3,y);
            number += block.myEnemies(this.getGovernment()).size();
        }
        return number;
    }





}
