package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by brandon on 6/8/15.
 */
public class FoodBlock extends Block {
    public final double attractiveness = 0.0;
    //public static final Color color = new Color(.9f, .9f, .9f, 1);
    /**
     * Standard GoalBlock constructor. Default attractiveness.
     * @param x X position
     * @param y Y position
     */
    public FoodBlock(int x, int y){
        setColor(new Color(.5f, .5f, .5f, 1));
        setX(x);
        setY(y);
        setType(FOODBLOCK);
    }
}
