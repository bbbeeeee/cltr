package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Void Block. Has no attractiveness. Other blocks cannot interact with this other than,
 * when the other block gets onto the same block space, it is destroyed/it won't grow onto it.
 */
public class VoidBlock extends Block{
    public final String type = "Void";
    public final double attractiveness = 0;
//    public static final Color color = new Color(0, 0, 0, 1);

    /**
     * Standard void constructor.
     * @param x
     * @param y
     */
    public VoidBlock(int x, int y){
        setColor(new Color(0, 0, 0, 1));
        setX(x);
        setY(y);
        setSymbiosis(0);
    }
}
