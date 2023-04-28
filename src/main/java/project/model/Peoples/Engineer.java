package project.model.Peoples;

import project.model.Block;
import project.model.Government;
import project.model.Map;

public class Engineer extends People{

    private boolean oil;

    Engineer(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
    }

    public void pourOil(String direction, Map map){
        oil = false;
        int x = this.getBlock().getX();
        int y = this.getBlock().getY();
        if (direction.equals("n")){
            map.getBlockByPosition(x,y+1).burn();
            map.getBlockByPosition(x,y+2).burn();
            map.getBlockByPosition(x,y+3).burn();
        }
        else if (direction.equals("s")){
            map.getBlockByPosition(x,y-1).burn();
            map.getBlockByPosition(x,y-2).burn();
            map.getBlockByPosition(x,y-3).burn();
        }
        else if (direction.equals("l")){
            map.getBlockByPosition(x-1,y).burn();
            map.getBlockByPosition(x-2,y).burn();
            map.getBlockByPosition(x-3,y).burn();
        }
        else if (direction.equals("r")){
            map.getBlockByPosition(x+1,y).burn();
            map.getBlockByPosition(x+2,y).burn();
            map.getBlockByPosition(x+3,y).burn();
        }

    }




}
