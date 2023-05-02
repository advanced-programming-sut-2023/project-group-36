package project.model.Buildings;

public class BuildingType {

    public String type;
    public String category;
    public int HP;
    public int damage;
    public int popularityChange;
    public int rate;
    public int fireRange;
    public int defenceRange;
    public int capacity;

    public BuildingType(String type, String category, int HP, int damage, int popularityChange, int rate
            , int fireRange, int defenceRange, int capacity) {
        this.type = type;
        this.category = category;
        this.HP = HP;
        this.damage = damage;
        this.popularityChange = popularityChange;
        this.rate = rate;
        this.fireRange = fireRange;
        this.defenceRange = defenceRange;
        this.capacity = capacity;
    }
}
