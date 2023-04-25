package project.model;

import java.util.ArrayList;

public class Map implements Cloneable{
    private int size;
    private String name;

    private int capacity = 0;
    private ArrayList<Block> blocks = new ArrayList<>();


    public Map(int size, String name){
        this.size = size;
        this.name = name;
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
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).nextTurn();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public void createBlacks(){
        Block block;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                block = new Block(i,j);
                block.setType("land");
                blocks.add(block);
            }
        }
    }
}
