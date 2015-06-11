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

/**
 * Created by btru on 5/10/15.
 */
public class Toolbelt extends ArrayList<Integer> {

    public Toolbelt(){
        // this.add
    }

    public void use(int type){
        this.set(type, this.get(type) - 1);
    }

    public void add(int type){
        this.add(type, this.get(type) + 1);
    }
}
