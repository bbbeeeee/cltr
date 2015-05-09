package com.brandontruong.cltr;

import java.util.ArrayList;

/**
 * Created by btru on 5/6/15.
 */
public class Block {
    /**
     * Coordinates of block. Easier if everything in the game readily knows its coordinates.
     */
    public int x, y;

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

    /**
     *  Factor that determines how attractive type iBlock is towards this block. Negative means type iBlock will move away from this block.
     */
    public double attractiveness;

    public double getAttractiveness() {
        return attractiveness;
    }

    public void setAttractiveness(double attractiveness) {
        this.attractiveness = attractiveness;
    }

    /**
     * Factor that determines how the block can live in symbiosis with another.
     * 0: Dominant and will not live with another. If it grows onto a BlockSpace, it will replace what is there.
     *    If another block grows onto its blockspace, it will reject it. Lives with nothing else.
     * 1: Can live with other blocks. Can live with 1s, growing onto or being grown onto.
     */
    public int symbiosis;

    public int getSymbiosis() {
        return symbiosis;
    }

    public void setSymbiosis(int symbiosis) {
        this.symbiosis = symbiosis;
    }

}
