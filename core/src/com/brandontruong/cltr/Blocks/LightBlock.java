package com.brandontruong.cltr.Blocks;

import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/6/15.
 */
public class LightBlock extends Block {
    private final double attractiveness = .9;

    /**
     * Standard LightBlock constructor.
     * @param x X position
     * @param y Y position
     */
    public LightBlock(int x, int y){
        setX(x);
        setY(y);
        setSymbiosis(1);
    }

}
