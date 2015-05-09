package com.brandontruong.cltr.Blocks;

import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/8/15.
 */
public class BlazeBlock extends Block {
    private final double attractiveness = .9;

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
