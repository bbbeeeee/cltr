package com.brandontruong.cltr.Blocks;

import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/6/15.
 */
public class Light extends Block {

    /**
     * Standard light constructor. Default attractiveness.
     * @param x X position
     * @param y Y position
     */
    public Light(int x, int y){
        // Default attractiveness
        this.setAttractiveness(1.0);
    }

}
