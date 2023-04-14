package model.Buildings;

import model.Block;

import java.util.ArrayList;

public class Storge extends Structure{
    String[] StorgeStructures={"stable","stockpile","armoury","Mercenary Post","engineer guild","tunneler guild"};
    ArrayList<Object> objects;

    public Storge(Block block) {
        this.objects = new ArrayList<>();
        super.block=block;
    }
}
