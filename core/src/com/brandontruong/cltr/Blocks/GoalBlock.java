package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/20/15.
 */
public class GoalBlock extends Block {
    public final double attractiveness = 0.0;
    //public static final Color color = new Color(.9f, .9f, .9f, 1);
    /**
     * Standard GoalBlock constructor. Default attractiveness.
     * @param x X position
     * @param y Y position
     */
    public GoalBlock(int x, int y){
        setColor(new Color(.5f, .5f, .5f, 1));
        setX(x);
        setY(y);
        setType(GOALBLOCK);
        setSymbiosis(0);
    }
}
