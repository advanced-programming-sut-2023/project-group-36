package project.model;

import java.util.ArrayList;

public class Map {
    private int size;
    private int capacity;
    private String name;
    private ArrayList<Block> blocks;

    private ArrayList<Block> governmentBlocks;

    public Block getBlockByPosition(int x, int y){
        for (Block block : blocks) {
            if (block.getX() == x && block.getY() == y) {
                return block;
            }
        }
        return null;
    }

    public void nextTurn(){
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).nextTurn();
        }
    }

    public String getName() {
        return name;
    }

    public Map copyMap(){
        Map CMap = new Map(); //...
        return CMap;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addGovernmentBlock(Block block){
        governmentBlocks.add(block);
    }
}
