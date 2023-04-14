package model.Buildings;

public class Industrial extends Structure {
    String[] IndustrialStuctures={"Market","Ox tether","Stockpile","Woodcutter"};
    int rate;
    int capacity;

    public Industrial(String[] industrialStuctures, int rate, int capacity) {
        IndustrialStuctures = industrialStuctures;
        this.rate = rate;
        this.capacity = capacity;
    }
}
