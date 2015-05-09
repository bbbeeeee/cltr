package com.brandontruong.cltr.Blocks;

import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/6/15.
 */
public class iBlock extends Block {
    private final double attractiveness = 0.2;

    /**
     * Standard iBlock constructor.
     * @param x X position
     * @param y Y position
     */
    public iBlock(int x, int y){
        setX(x);
        setY(y);
        setSymbiosis(1);
    }

}
