package com.brandontruong.cltr;

import com.brandontruong.cltr.Blocks.BlazeBlock;
import com.brandontruong.cltr.Blocks.LightBlock;
import com.brandontruong.cltr.Blocks.VoidBlock;
import com.brandontruong.cltr.Blocks.WaterBlock;
import com.brandontruong.cltr.Blocks.iBlock;

/**
 * Created by btroo on 5/10/15.
 */
public class BlockCreator {

    public BlockCreator(){

    }

    public static Block newBlock(String type, int x, int y){
        switch (type){
            case "Blaze":
                return new BlazeBlock(x, y);
            case "i":
                return new iBlock(x, y);
            case "Light":
                return new LightBlock(x, y);
            case "Void":
                return new VoidBlock(x, y);
            case "Water":
                return new WaterBlock(x, y);
            default:
                return new iBlock(x, y);
        }
    }
}
