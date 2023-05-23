package project.model.Buildings;

public class BuildingType {

    private final String type;
    private final String category;
    private int HP;
    private final int damage;
    private final int popularityChange;
    private final int rate;
    private final int fireRange;
    private final int defenceRange;
    private final int requiredPeopleToWork;

    public int getGoldCost() {
        return goldCost;
    }

    public int getStoneCost() {
        return stoneCost;
    }

    public int getWoodCost() {
        return woodCost;
    }

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


    public void changeNormalPeopleCapacity(int amount) {
        this.NormalPeopleCapacity += amount;
    }

    private int goldCost,stoneCost,woodCost;

    public int getRequiredPeopleToWork() {
        return requiredPeopleToWork;
    }

    public BuildingType(String type, String category, int woodCost, int stoneCost, int goldCost, int damage, int popularityChange, int rate
            , int fireRange, int defenceRange, int normalPeopleCapacity, int militiaCapacity, int requiredPeopleToWork) {
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
        this.goldCost=goldCost;
        this.woodCost=woodCost;
        this.stoneCost=stoneCost;
        this.requiredPeopleToWork=requiredPeopleToWork;
    }
}
