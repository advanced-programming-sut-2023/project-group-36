package model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    private Structure structure;
    private Paint thisBlockType;

    public Block(double v, double v1, double v2, double v3, Structure structure,Paint thisBlockType) {
        super(v, v1, v2, v3);
        this.structure = structure;
        this.thisBlockType=thisBlockType;
    }

    public void setThisBlockStructure(Structure structure) {
        this.structure=structure;
    }

    public Structure getStructure() {
        return structure;
    }



    public Paint getThisBlockType() {
        return thisBlockType;
    }

    public void setThisBlockType(Paint thisBlockType) {
        this.thisBlockType = thisBlockType;
    }
}
