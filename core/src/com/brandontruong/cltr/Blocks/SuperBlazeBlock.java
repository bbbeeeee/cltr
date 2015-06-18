package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by brandon on 6/18/15.
 */
public class SuperBlazeBlock extends Block {
    //public static final Color color = new Color(204/255f, 102/255f, 154/255f, 1);

    /**
     * Standard BlazeBlock constructor. Default attractiveness.
     * @param x X position
     * @param y Y position
     */
    public SuperBlazeBlock(int x, int y){
        setColor(new Color(255/255f, 102/255f, 154/255f, 1));
        setX(x);
        setY(y);
        setType(BLAZEBLOCK);
        setSymbiosis(0);
        setGrowthFactor(5.1f);
    }
}
