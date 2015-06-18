package com.brandontruong.cltr;

/**
 * Created by btru on 5/10/15.
 */
public class Toolbelt {

    int[] blocks;
    int selected = 0;

    public Toolbelt(){
        blocks = new int[Block.totalBlocks];
    }

    public void use(int type){
        blocks[type] -= 1;
    }

    public void add(int type){
        blocks[type] += 1;
    }

    public void add(int type, int count){
        blocks[type] += count;
    }

    public int get(int type){
        return blocks[type];
    }
}
