package project.model.Buildings;

public class Trap extends Structure {
    private int damage;
    String[] TrapStructures={"killing pit","pitch ditch","Caged War Dogs"};


    public Trap(int damage,BuildingType buildingType) {
        super(buildingType.getHP());
        this.damage = damage;
    }
}