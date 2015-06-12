package com.brandontruong.cltr;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.brandontruong.cltr.Blocks.BlazeBlock;
import com.brandontruong.cltr.Blocks.EmptyBlock;
import com.brandontruong.cltr.Blocks.LightBlock;
import com.brandontruong.cltr.Blocks.VoidBlock;
import com.brandontruong.cltr.Blocks.WaterBlock;
import com.brandontruong.cltr.Blocks.iBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by btru on 5/10/15.
 */
public class Toolbelt {

    int[] blocks;
    int selected = 0;

    public Toolbelt(){
        blocks = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    }

    public void use(int type){
        blocks[type] -= 1;
    }

    public void add(int type){
        blocks[type] += 1;
    }

    public int get(int type){
        return blocks[type];
    }
}
