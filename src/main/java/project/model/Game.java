package project.model;

import java.util.ArrayList;

public class Game {

    private Government currentGovernment;
    private final ArrayList<Government> governments;
    private final Map map;
    private boolean condition;

    private int numberOfPlayers;


    public Map getMap() {
        return map;
    }

    public Game(Map map, ArrayList<Government> governments){
        this.governments = governments;
        this.map = map;
        condition = true;
        currentGovernment = this.governments.get(0);
        numberOfPlayers = governments.size();
    }

    public void nextTurn(){
        map.nextTurn();
        governmentsNextTurn();
        nextGovernment();
    }

    private void governmentsNextTurn(){
        for (Government government : governments) {
            government.nextTurn();
        }
    }

    private void nextGovernment(){
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
