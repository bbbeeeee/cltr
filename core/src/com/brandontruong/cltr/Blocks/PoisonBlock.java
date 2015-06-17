package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by brandon on 6/17/15.
 */
public class PoisonBlock extends Block {
    /**
     * Standard ObstacleBlock constructor. Default attractiveness.
     * @param x X position
     * @param y Y position
     */
    public PoisonBlock(int x, int y){
        setColor(new Color(.5f, 0f, .5f, 1));
        setX(x);
        setY(y);
        setType(POISONBLOCK);
        setSymbiosis(-1);
    }
}
