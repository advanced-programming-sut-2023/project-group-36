package model;

public class Launcher extends Militia{

    private int launchRadius;

    Launcher(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
        launchRadius = peopleType.launchRadius;
    }

    public void launch(Block block){

    }

    private boolean checkBeingInRadius (){
        return true;
    }

}
