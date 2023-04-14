package model.Peoples;

import model.Block;
import model.Government;

public class NormalPeople extends People {
    private boolean employed;
    NormalPeople(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
        employed = false;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }


}
