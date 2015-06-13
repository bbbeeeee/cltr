package com.brandontruong.cltr;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Blocks.BlazeBlock;
import com.brandontruong.cltr.Blocks.EmptyBlock;
import com.brandontruong.cltr.Blocks.LightBlock;
import com.brandontruong.cltr.Blocks.VoidBlock;
import com.brandontruong.cltr.Blocks.WaterBlock;
import com.brandontruong.cltr.Blocks.iBlock;

import java.util.HashMap;

/**
 * Block parent class. Need to update Toolbelt and BlockSpace when new Blocks are added.
 */
public class Block {
    public static final int SELECTEDBLOCK = -1;
    public static final int BLAZEBLOCK = 1;
    public static final int EMPTYBLOCK = 2;
    public static final int GOALBLOCK = 3;
    public static final int IBLOCK = 4;
    public static final int LIGHTBLOCK = 5;
    public static final int VOIDBLOCK = 6;
    public static final int WATERBLOCK = 7;
    public static final int OBSTACLEBLOCK = 8;
    public static final int FOODBLOCK = 9;
    public static final int totalBlocks = 9;
    public static Color ICOLOR = new Color(0.4f, 0.8f, 0.4f, 1);
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
