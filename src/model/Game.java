package model;

import java.util.ArrayList;

public class Game {
    private User CurrentUser;
    private ArrayList<Government> governments;
    private Map map;
    private boolean condition;

    public Map getMap() {
        return map;
    }

    Game(ArrayList<Government> governments,Map map){
        this.governments = governments;
        this.map = map;
        condition = true;
    }

    public void nextTurn(){
        mapNextTurn();
        governmentsNextTurn();
    }

    private void governmentsNextTurn(){
        for (Government government : governments) {
            government.nextTurn();
        }
    }

    private void mapNextTurn(){
        map.nextTurn();
    }

    public boolean checkEnd(){
        return false; // ...
    }

    public Government getGovernmentByUser(User user){
        for (Government government : governments) {
            if (government.getOwner().equals(user)) {
                return government;
            }
        }
        return null;
    }
}
