package com.brandontruong.cltr;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.brandontruong.cltr.Blocks.BlazeBlock;
import com.brandontruong.cltr.Blocks.EmptyBlock;
import com.brandontruong.cltr.Blocks.FoodBlock;
import com.brandontruong.cltr.Blocks.GoalBlock;
import com.brandontruong.cltr.Blocks.LightBlock;
import com.brandontruong.cltr.Blocks.ObstacleBlock;
import com.brandontruong.cltr.Blocks.PoisonBlock;
import com.brandontruong.cltr.Blocks.VoidBlock;
import com.brandontruong.cltr.Blocks.WaterBlock;
import com.brandontruong.cltr.Blocks.iBlock;

import java.util.ArrayList;

/**
 * Created by btru on 5/8/15.
 */
public class BlockSpace extends ArrayList<Block>{
    public int x;
    public int y;
    public int[] potentials;

    public double attractiveness;

    public BlockSpace(int x, int y){
        setX(x);
        setY(y);
        potentials = new int[9];
    }

    public void replace(Block b){
        this.clear();
        this.add(b);
    }

    public void replace(int type){
        this.clear();
        this.add(newBlock(type, x, y));
    }

    public void destroy(Block b){
        this.remove(b);
    }

    public void makeVoid(){
        this.replace(new VoidBlock(x, y));
    }

    public void applyForce(double factor){
        potentials[Block.IBLOCK] += factor;
    }

    public static Block newBlock(int type, int x, int y){
        switch(type){
            case Block.BLAZEBLOCK:
                return new BlazeBlock(x, y);
            case Block.EMPTYBLOCK:
                return new EmptyBlock(x, y);
            case Block.GOALBLOCK:
                return new GoalBlock(x, y);
            case Block.IBLOCK:
                return new iBlock(x, y);
            case Block.LIGHTBLOCK:
                return new LightBlock(x, y);
            case Block.VOIDBLOCK:
                return new VoidBlock(x, y);
            case Block.WATERBLOCK:
                return new WaterBlock(x, y);
            case Block.OBSTACLEBLOCK:
                return new ObstacleBlock(x, y);
            case Block.FOODBLOCK:
                return new FoodBlock(x, y);
            case Block.POISONBLOCK:
                return new PoisonBlock(x, y);
            default:
                return new EmptyBlock(x, y);
        }
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
