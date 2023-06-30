package controller;

import model.*;
import model.Buildings.Structure;

import java.util.ArrayList;

public class EditMap {

    public static Map map;
    public static int number;

    public static ArrayList<Block> blocks;


    public static void setMap() {
        map = new Map(50);
        blocks = map.getBlocks();
    }

    public static String setGovernment(Block block, Government government) {
        if (block.getThisBlockStructure()!=null){
            return "Error: This position has already been selected for another government!";
        }
        Structure centralCastle = new Structure(government,block);
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
