package project.model;

import java.util.ArrayList;

public class Game {

    private Government currentGovernment;
    private final ArrayList<Government> governments;
    private final Map map;
    private boolean condition;

    public Map getMap() {
        return map;
    }

    Game(ArrayList<Government> governments,Map map){
        this.governments = governments;
        this.map = map;
        condition = true;
        currentGovernment = governments.get(0);
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

    private void changeCurrentUser(){
        int number = governments.indexOf(currentGovernment);
        currentGovernment = governments.get((number%governments.size()));
        //...
    }

    public boolean checkEnd(){
        condition = false;
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

    public Government getCurrentGovernment() {
        return currentGovernment;
    }

    private void giveScore(Government government){
        User user = government.getOwner();
        user.addScore(governments.size());
    }

    private void removeGovernment(Government government){
        governments.remove(government);
    }
}
