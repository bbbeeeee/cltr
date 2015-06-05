package com.brandontruong.cltr;

import java.util.Random;

/**
 * Created by btru on 5/22/15.
 */
public class Sentinel {
    private int x;

    private int y;

    public int blocktype;
    public Sentinel(int type, int _x, int _y){
        blocktype = type;
        x = _x;
        y = _y;
    }

    public static double chance(double probability){
        Random random = new Random();
        return random.nextDouble() * probability;
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

    public int getBlocktype() {
        return blocktype;
    }

    public void setBlocktype(int blocktype) {
        this.blocktype = blocktype;
    }
}
