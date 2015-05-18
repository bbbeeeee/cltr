package com.brandontruong.cltr;

import com.brandontruong.cltr.Blocks.BlazeBlock;
import com.brandontruong.cltr.Blocks.EmptyBlock;
import com.brandontruong.cltr.Blocks.LightBlock;
import com.brandontruong.cltr.Blocks.VoidBlock;
import com.brandontruong.cltr.Blocks.WaterBlock;
import com.brandontruong.cltr.Blocks.iBlock;

import java.util.ArrayList;

/**
 * Created by btru on 5/8/15.
 */
public class BlockSpace extends ArrayList<Block> {
    public static int x;
    public static int y;

    public double attractiveness;

    public void replace(Block b){
        this.clear();
        this.add(b);
    }

    public void replace(String type){
        this.clear();
        this.add(newBlock(type, x, y));
    }

    public void destroy(Block b){
        this.remove(b);
    }

    public void makeVoid(){
        this.clear();
        VoidBlock v = new VoidBlock(x, y);
        this.add(new VoidBlock(x, y));
    }

    public static Block newBlock(String type, int x, int y){
        switch (type){
            case "Blaze":
                return new BlazeBlock(x, y);
            case "Empty":
                return new EmptyBlock(x, y);
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

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        BlockSpace.x = x;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        BlockSpace.y = y;
    }

    public double getAttractiveness() {
        return attractiveness;
    }

    public void setAttractiveness(double attractiveness) {
        this.attractiveness = attractiveness;
    }
}
