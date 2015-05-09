package com.brandontruong.cltr;

import com.brandontruong.cltr.Blocks.VoidBlock;

import java.util.ArrayList;

/**
 * Created by btru on 5/8/15.
 */
public class BlockSpace extends ArrayList<Block> {
    public int x, y;
    public double attractiveness;

    public void replace(Block b){
        this.clear();
        this.add(b);
    }

    public void destroy(Block b){
        this.remove(b);
    }

    public void makeVoid(){
        this.clear();
        VoidBlock v = new VoidBlock(x, y);
        this.add(new VoidBlock(x, y));
    }


}
