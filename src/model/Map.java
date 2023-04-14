package model;

import java.util.ArrayList;

public class Map {
    private Game game;
    private int size;
    private ArrayList<Block> blocks;

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
}
