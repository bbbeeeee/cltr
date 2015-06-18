package com.brandontruong.cltr;

import com.badlogic.gdx.graphics.Color;


/**
 * Block parent class. Need to update Toolbelt and BlockSpace when new Blocks are added.
 */
public class Block {
    /**
     * Negative indicates nonusable block.
     * Each block has it's own index as well as symbiosis rating (see symbiosis);
     * Need to update Block.getSymbiosis, Environment actions, BlockSpace.newBlock, and BlockActor constructor when you add one of these.
     */
    public static final int XBLOCK = -2;
    public static final int SELECTEDBLOCK = -1;

    public static final int BLAZEBLOCK = 1;
    public static final int BLAZEBLOCKSYMBIOSIS = 2;

    public static final int EMPTYBLOCK = 2;
    public static final int EMPTYBLOCKSYMBIOSIS = 0;

    public static final int GOALBLOCK = 3;
    public static final int GOALBLOCKSYMBIOSIS = 4;

    public static final int IBLOCK = 4;
    public static final int IBLOCKSYMBIOSIS = 4;

    public static final int ELECTRICITYBLOCK = 5;
    public static final int ELECTRICITYBLOCKSYMBIOSIS = 2;

    public static final int VOIDBLOCK = 6;
    public static final int VOIDBLOCKSYMBIOSIS = 6;

    public static final int WATERBLOCK = 7;
    public static final int WATERBLOCKSYMBIOSIS = 2;

    public static final int OBSTACLEBLOCK = 8;
    public static final int OBSTACLEBLOCKSYMBIOSIS = 5;

    public static final int FOODBLOCK = 9;
    public static final int FOODBLOCKSYMBIOSIS = 9;

    public static final int POISONBLOCK = 10;
    public static final int POISONBLOCKSYMBIOSIS = 2;

    public static final int SUPERBLAZEBLOCK = 11;
    public static final int SUPERBLAZEBLOCKSYMBIOSIS = 3;
    public static final int totalBlocks = 12;
    public static Color ICOLOR = new Color(0.4f, 0.8f, 0.4f, 1);
    
    /**
     * Coordinates of block. Easier if everything in the game readily knows its coordinates.
     */
    public int x, y;

    public static int getSymbiosis(int type){
        switch(type){
            case Block.BLAZEBLOCK:
                return BLAZEBLOCKSYMBIOSIS;
            case Block.EMPTYBLOCK:
                return EMPTYBLOCKSYMBIOSIS;
            case Block.GOALBLOCK:
                return GOALBLOCKSYMBIOSIS;
            case Block.IBLOCK:
                return IBLOCKSYMBIOSIS;
            case Block.ELECTRICITYBLOCK:
                return ELECTRICITYBLOCKSYMBIOSIS;
            case Block.VOIDBLOCK:
                return VOIDBLOCKSYMBIOSIS;
            case Block.WATERBLOCK:
                return WATERBLOCKSYMBIOSIS;
            case Block.OBSTACLEBLOCK:
                return OBSTACLEBLOCKSYMBIOSIS;
            case Block.FOODBLOCK:
                return FOODBLOCKSYMBIOSIS;
            case Block.POISONBLOCK:
                return POISONBLOCKSYMBIOSIS;
            case Block.SUPERBLAZEBLOCK:
                return SUPERBLAZEBLOCKSYMBIOSIS;
            default:
                return EMPTYBLOCKSYMBIOSIS;
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    public static float getGrowthFactor() {
        return growthFactor;
    }

    public static void setGrowthFactor(float g) {
        growthFactor = g;
    }

    private static float growthFactor;

    public Color color;

    /**
     *  Factor that determines how attractive type iBlock is towards this block. Negative means type iBlock will move away from this block.
     */
    public double attractiveness;

    /**
     * Factor that determines how the block can live in symbiosis with another.
     * Ratings for different blocks. Higher means higher precedence.
     * Consumables are below iBlock
     * obstacles and goal are highest
     *
     */
    public static int symbiosis;

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
