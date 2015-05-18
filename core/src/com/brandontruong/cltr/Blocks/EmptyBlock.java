package com.brandontruong.cltr.Blocks;

import com.brandontruong.cltr.Block;

/**
 * Created by btroo on 5/10/15.
 */
public class EmptyBlock extends Block{

    /**
     * Standard BlazeBlock constructor. Default attractiveness.
     * @param x X position
     * @param y Y position
     */
    public EmptyBlock(int x, int y){
        setX(x);
        setY(y);
        setSymbiosis(0);
    }

}
