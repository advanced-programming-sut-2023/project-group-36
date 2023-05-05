package project.model.Buildings;

public class BuildingType {

    private String type;
    private String category;
    private int HP;
    private int damage;
    private int popularityChange;
    private int rate;
    private int fireRange;
    private int defenceRange;
    private int NormalPeopleCapacity;
    private int MilitiaCapacity;

    public int getNormalPeopleCapacity() {
        return NormalPeopleCapacity;
    }

    public int getMilitiaCapacity() {
        return MilitiaCapacity;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public int getHP() {
        return HP;
    }

    public int getDamage() {
        return damage;
    }

    public int getPopularityChange() {
        return popularityChange;
    }

    public int getRate() {
        return rate;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefenceRange() {
        return defenceRange;
    }

    public int getCapacity() {
        return capacity;
    }

    private int capacity;

    public BuildingType(String type, String category, int HP, int damage, int popularityChange, int rate
            , int fireRange, int defenceRange,int normalPeopleCapacity,int militiaCapacity) {
        this.type = type;
        this.category = category;
        this.HP = HP;
        this.damage = damage;
        this.popularityChange = popularityChange;
        this.rate = rate;
        this.fireRange = fireRange;
        this.defenceRange = defenceRange;
        this.NormalPeopleCapacity=normalPeopleCapacity;
        this.MilitiaCapacity=normalPeopleCapacity;

    }
}
