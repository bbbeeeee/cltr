package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/6/15.
 */
public class ElectricityBlock extends Block {
    public final double attractiveness = .9;
//    public static final Color color = new Color(1, 204/255f, 102/255f, 1);

    /**
     * Standard ElectricityBlock constructor.
     * @param x X position
     * @param y Y position
     */
    public ElectricityBlock(int x, int y){
        setColor(new Color(1, 204 / 255f, 102 / 255f, 1));
        setX(x);
        setY(y);
        setType(ELECTRICITYBLOCK);
    }

}
