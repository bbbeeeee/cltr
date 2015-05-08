package com.brandontruong.cltr;

/**
 * Created by btru on 5/6/15.
 */
public class Block {
    public int x, y;

    /**
     *  Factor that determines how attractive type i blocks are towards this block. Negative means type i will move away from this block.
     */
    public double attractiveness;

    public double getAttractiveness() {
        return attractiveness;
    }

    public void setAttractiveness(double attractiveness) {
        this.attractiveness = attractiveness;
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


}
