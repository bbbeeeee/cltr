package com.brandontruong.cltr;

import com.badlogic.gdx.graphics.Color;
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
    public int x;
    public int y;

    public double attractiveness;

    public BlockSpace(int x, int y){
        setX(x);
        setY(y);
    }

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
        if(type == "Blaze")
            return new BlazeBlock(x, y);
        else if(type == "Empty")
            return new EmptyBlock(x, y);
        else if(type == "i")
            return new iBlock(x, y);
        else if(type == "Light")
            return new LightBlock(x, y);
        else if(type == "Void")
            return new VoidBlock(x, y);
        else if(type == "Water")
            return new WaterBlock(x, y);
        else
            return new iBlock(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getAttractiveness() {
        return attractiveness;
    }

    public void setAttractiveness(double attractiveness) {
        this.attractiveness = attractiveness;
    }
}
