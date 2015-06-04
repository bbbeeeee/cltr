package com.brandontruong.cltr;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Blocks.BlazeBlock;
import com.brandontruong.cltr.Blocks.EmptyBlock;
import com.brandontruong.cltr.Blocks.LightBlock;
import com.brandontruong.cltr.Blocks.VoidBlock;
import com.brandontruong.cltr.Blocks.WaterBlock;
import com.brandontruong.cltr.Blocks.iBlock;

/**
 * Block parent class. Need to update Toolbelt and BlockSpace when new Blocks are added.
 */
public class Block {
    public static final int BLAZEBLOCK = 1,
        EMPTYBLOCK = 2,
        GOALBLOCK = 3,
        IBLOCK = 4,
        LIGHTBLOCK = 5,
        VOIDBLOCK = 6,
        WATERBLOCK = 7,
        OBSTACLEBLOCK = 8;

    /**
     * Coordinates of block. Easier if everything in the game readily knows its coordinates.
     */
    public int x, y;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    public Color color;

    /**
     *  Factor that determines how attractive type iBlock is towards this block. Negative means type iBlock will move away from this block.
     */
    public double attractiveness;

    /**
     * Factor that determines how the block can live in symbiosis with another.
     * 0: Dominant and will not live with another. If it grows onto a BlockSpace, it will replace what is there.
     *    If another block grows onto its blockspace, it will reject it. Lives with nothing else.
     * 1: Can live with other blocks. Can live with 1s, growing onto or being grown onto.
     */
    public int symbiosis;

    public void move(){

    }

    public void grow(){

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getAttractiveness() {
        return attractiveness;
    }

    public void setAttractiveness(double attractiveness) {
        this.attractiveness = attractiveness;
    }

    public int getSymbiosis() {
        return symbiosis;
    }

    public void setSymbiosis(int symbiosis) {
        this.symbiosis = symbiosis;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
