package project.model;

import project.controller.GameController;

import java.util.ArrayList;

public class Game {

    private Government currentGovernment;
    private final ArrayList<Government> governments;
    private final Map map;
    private boolean condition;

    private final int numberOfPlayers;

    private int number;


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
        checkEnd();
    }

    private void governmentsNextTurn(){
        for (Government government : governments) {
            government.nextTurn();
        }
    }

    private void nextGovernment(){
        number+=1;
        currentGovernment = governments.get((number%governments.size()));
    }

    public void checkEnd(){
        if (governments.size()==1){
            condition = false;
            governments.get(0).getOwner().addScore(getScore());
            GameController.endGame();
        }
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


    public void removeGovernment(Government government){
        governments.remove(government);
    }


    public int getScore(){
        return numberOfPlayers * (numberOfPlayers-governments.size()-1);
    }
}
