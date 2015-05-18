package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/6/15.
 */
public class iBlock extends Block {
    public static final double attractiveness = 0.2;
    public static final Color color = new Color(102, 204, 102, 1);
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
