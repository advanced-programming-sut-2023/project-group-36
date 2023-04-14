package model;

public class Militia extends People{
    private int attackPower;

    Militia(PeopleType peopleType, Government government, Block block) {
        super(peopleType, government, block);
        attackPower = peopleType.attackPower;
    }


    public void damage(Block block){

    }

    public int getAttackPower() {
        return attackPower;
    }

}
