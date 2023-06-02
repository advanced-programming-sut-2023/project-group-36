package view;

import model.Block;
import model.GBlock;

import java.util.ArrayList;

public class EditMapController {


    private static ArrayList<Block> blocks;
    private static ArrayList<GBlock> gBlocks;

    public void menuInitialize() {

        blocks = new ArrayList<>();
        gBlocks = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                blocks.add(new Block(i,j));
            }
        }


        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                gBlocks.add(new GBlock(getBlockByPosition(i,j),true));
            }
        }

    }

    public static Block getBlockByPosition ( int x, int y){
        for (Block block : blocks) {
            if (block.getX() == x && block.getY() == y) {
                return block;
            }
        }
        return null;
    }

}
