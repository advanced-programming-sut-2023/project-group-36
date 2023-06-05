package controller;

import model.*;
import model.Buildings.Structure;

import java.util.ArrayList;

public class EditMap {

    public static Map map;
    public static int number;

    public static ArrayList<Block> blocks = new ArrayList<>();


    public static void setMap() {
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                blocks.add(new Block(i,j));
            }
        }
    }

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


    public static void clear(Block block){
        block.setType("Dirt");
    }






}
