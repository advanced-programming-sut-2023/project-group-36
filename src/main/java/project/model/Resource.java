package project.model;

public class Resource {

    private final String name;
    private int amount;
    public int ProductionRate=0;
    public Resource(String name, int amount) {
        this.amount = amount;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return amount;
    }

    public void changeCount(int amount){
        this.amount += amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
