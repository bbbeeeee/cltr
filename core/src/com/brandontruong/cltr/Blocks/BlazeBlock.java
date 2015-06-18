package com.brandontruong.cltr.Blocks;

import com.brandontruong.cltr.Block;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by btru on 5/8/15.
 */
public class BlazeBlock extends Block {
    public final double attractiveness = .9;

    /**
     * Standard BlazeBlock constructor. Default attractiveness.
     * @param x X position
     * @param y Y position
     */
    public BlazeBlock(int x, int y){
        setColor(new Color(204/255f, 102/255f, 154/255f, 1));
        setX(x);
        setY(y);
        setType(BLAZEBLOCK);
        setGrowthFactor(5.5f);
    }

}
