package com.brandontruong.cltr.Blocks;

import com.brandontruong.cltr.Block;

/**
 * Void Block. Has no attractiveness. Other blocks cannot interact with this other than,
 * when the other block gets onto the same block space, it is destroyed/it won't grow onto it.
 */
public class VoidBlock extends Block{
    private final double attractiveness = 0;

    /**
     * Standard void constructor.
     * @param x
     * @param y
     */
    public VoidBlock(int x, int y){
        setX(x);
        setY(y);
        setSymbiosis(0);
    }
}
