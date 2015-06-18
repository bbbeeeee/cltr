package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by btru on 6/3/15.
 */
public class ObstacleBlock extends Block {
    public final double attractiveness = 0.0;

    /**
     * Standard ObstacleBlock constructor. Default attractiveness.
     * @param x X position
     * @param y Y position
     */
    public ObstacleBlock(int x, int y){
        setColor(new Color(.1f, .1f, .1f, 1));
        setX(x);
        setY(y);
        setType(OBSTACLEBLOCK);
    }
}
