package project.model.Peoples;

import project.model.Buildings.Structure;

public class PeopleType {
    public String type;

    public String category;
    public int price;
    public int attackPower;
    public int defencePower;
    public int speed;
    public int launchRadius;

    public String[] equipment;
    public String requiredStructure;

    public PeopleType(String type, String category, int price, int attackPower, int defencePower, int speed, int launchRadius, String[] equipment){
        this.type = type;
        this.category = category;
        this.price = price;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed=speed;
        this.launchRadius=launchRadius;
        this.equipment = equipment;
    }


}
