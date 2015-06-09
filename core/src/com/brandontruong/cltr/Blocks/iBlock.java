package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/6/15.
 */
public class iBlock extends Block {
    public final double attractiveness = 0.2;
    //public static final Color color = new Color(0.4f, 0.8f, 0.4f, 1);
    /**
     * Standard iBlock constructor.
     * @param x X position
     * @param y Y position
     */
    public iBlock(int x, int y){
        setColor(new Color(0.4f, 0.8f, 0.4f, 1));
        setX(x);
        setY(y);
        setType(IBLOCK);
        setSymbiosis(1);
    }

    public int prevX, prevY;

    public void setPreviousCoordinates(int x, int y){
        prevX = x;
        prevY = y;
    }

    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }
}
