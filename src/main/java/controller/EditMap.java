package controller;

import model.*;
import model.Buildings.Structure;

public class EditMap {

    public static Map map;
    public static int number;

    public static String setGovernment(Block block, Government government) {
        if (block.getThisBlockStructure()!=null){
            return "Error: This position has already been selected for another government!";
        }
        Structure centralCastle = new Structure(100);
        block.setThisBlockStructure(centralCastle);
        government.setCentralCastle(centralCastle);
        number++;
        return null;
    }

    public static void setTexture(Block block, String type) {
        block.setType(type);
    }








}
