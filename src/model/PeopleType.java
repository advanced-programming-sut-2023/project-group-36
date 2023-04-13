package model;

import java.util.ArrayList;

public class PeopleType {
    public String type;

    public String category;
    public int price;
    public int attackPower;
    public int defencePower;
    public int speed;
    public int launchRadius;

    PeopleType(String type ,String category , int price ,int attackPower ,int defencePower ,int speed ,int launchRadius){
        this.type = type;
        this.category = category;
        this.price = price;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed=speed;
        this.launchRadius=launchRadius;
    }


}
