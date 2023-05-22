package model.Peoples;

import project.model.Block;
import project.model.Government;

public class NormalPeople extends People {
    private boolean employed;
    public NormalPeople(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
        employed = false;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public boolean isEmployed() {
        return employed;
    }
}
