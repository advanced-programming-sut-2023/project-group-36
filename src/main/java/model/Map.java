package model;

import java.util.ArrayList;

public class Map implements Cloneable{
    private int size;

    private int capacity;
    private ArrayList<Block> blocks = new ArrayList<>();


    public Map(int size){
        this.size = size;
        capacity = 0;
        createBlacks();
    }
    public Block getBlockByPosition(int x, int y){
        for (Block block : blocks) {
            if (block.getX() == x && block.getY() == y) {
                return block;
            }
        }
        return null;
    }

    public void nextTurn(){
        for (Block block : blocks) {
            block.nextTurn();
        }
    }

    public Map clone(){
        Map CMap;
        try {
            CMap = (Map) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        CMap.blocks = new ArrayList<>(this.blocks);
        return CMap;
    }

    public void addGovernment(int capacity) {
        capacity += 1;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void createBlacks(){
        Block block;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                block = new Block(i,j);
                block.setType("Dirt");
                blocks.add(block);
            }
        }
    }
}
