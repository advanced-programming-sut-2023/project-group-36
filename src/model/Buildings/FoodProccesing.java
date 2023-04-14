package model.Buildings;

import model.Block;
import model.Peoples.NormalPeople;

import java.util.ArrayList;

public class FoodProccesing extends Structure {
    String[] FoodProccesingStrucures={"Mill","Inn","Bakery","brewery","hinting post"};
    ArrayList<NormalPeople> workers;
    int rate;
    Object inputMaterial;
    Object outputMaterial;

    public FoodProccesing(int rate, Object inputMaterial, Object outputMaterial, Block block) {
        this.rate = rate;
        this.inputMaterial = inputMaterial;
        this.outputMaterial = outputMaterial;
        super.block=block;
    }
}
