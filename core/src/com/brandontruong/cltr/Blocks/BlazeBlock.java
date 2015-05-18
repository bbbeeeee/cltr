package com.brandontruong.cltr.Blocks;

import com.brandontruong.cltr.Block;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by btru on 5/8/15.
 */
public class BlazeBlock extends Block {
    public final double attractiveness = .9;
    public final Color color = new Color(204, 102, 154, 1);

    /**
     * Standard BlazeBlock constructor. Default attractiveness.
     * @param x X position
     * @param y Y position
     */
    public BlazeBlock(int x, int y){
        setX(x);
        setY(y);
        setSymbiosis(0);
    }

}
