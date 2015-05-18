package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by btroo on 5/10/15.
 */
public class EmptyBlock extends Block{
    public static final double attractiveness = 0.0;
    public static final Color color = new Color(255,255, 255, 1);
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
