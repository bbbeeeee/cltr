package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/6/15.
 */
public class LightBlock extends Block {
    public static final double attractiveness = .9;
    public static final Color color = new Color(255, 204, 102, 1);

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
