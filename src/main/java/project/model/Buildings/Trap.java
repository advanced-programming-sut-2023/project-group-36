package project.model.Buildings;

import project.model.Peoples.Militia;
import project.model.Peoples.People;
import project.model.Peoples.PeopleType;

public class Trap extends Structure {
    private int damage;


    public Trap(int damage,BuildingType buildingType) {
        super();
        this.damage = damage;
    }
}