package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by btroo on 5/10/15.
 */
public class EmptyBlock extends Block{
    public final double attractiveness = 0.0;

    /**
     * Standard EmptyBlock constructor. Default attractiveness.
     * @param x X position
     * @param y Y position
     */
    public EmptyBlock(int x, int y){
        setColor(new Color(.9f, .9f, .9f, 1));
        setX(x);
        setY(y);
        setType(EMPTYBLOCK);
        setSymbiosis(0);
    }

}
