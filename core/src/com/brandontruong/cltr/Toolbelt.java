package com.brandontruong.cltr;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.brandontruong.cltr.Blocks.BlazeBlock;
import com.brandontruong.cltr.Blocks.EmptyBlock;
import com.brandontruong.cltr.Blocks.LightBlock;
import com.brandontruong.cltr.Blocks.VoidBlock;
import com.brandontruong.cltr.Blocks.WaterBlock;
import com.brandontruong.cltr.Blocks.iBlock;

/**
 * Created by btru on 5/10/15.
 */
public class Toolbelt{
    private int blazeNum,
                emptyNum,
                iNum,
                lightNum,
                voidNum,
                waterNum;

    /**
     * Standard toolbelt constructor.
     * @param b Number of blaze blocks
     * @param i Number of i blocks
     * @param l Number of light blocks
     * @param v Number of void blocks
     * @param w number of water blocks
     */
    public Toolbelt(int b, int e, int i, int l, int v, int w){
        blazeNum = b;
        emptyNum = e;
        iNum = i;
        lightNum = l;
        voidNum = v;
        waterNum = w;
    }

    /**
     * Add a block to the toolbelt. Should be called either when a block is acquired or returned from grid.
     * @param type Type of block to be added
     */
    public void add(String type){
        if(type == "Blaze")
            blazeNum++;
        else if(type == "Empty")
            emptyNum++;
        else if(type == "i")
            iNum++;
        else if(type == "Light")
            lightNum++;
        else if(type == "Void")
            voidNum++;
        else if(type == "Water")
            waterNum++;
        else
            iNum++;
    }

    public void add(String type, int count){
        if(type == "Blaze")
            blazeNum += count;
        else if(type == "Empty")
            emptyNum += count;
        else if(type == "i")
            iNum += count;
        else if(type == "Light")
            lightNum += count;
        else if(type == "Void")
            voidNum += count;
        else if(type == "Water")
            waterNum += count;
        else
            iNum += count;
    }

    /**
     * Use a block from the toolbelt. Should be called when a block is lost.
     * @param type
     */
    public void remove(String type){
        if(type == "Blaze"){
            if(blazeNum >= 1)
                blazeNum--;
        }
        else if(type == "Empty") {
            if(emptyNum >= 1)
                emptyNum--;
        }
        else if(type == "i"){
            if(iNum >= 1)
                iNum--;
        }
        else if(type == "Light"){
            if(lightNum >= 1)
                lightNum--;
        }
        else if(type == "Void"){
            if(voidNum >= 1)
                voidNum--;
        }
        else if(type == "Water"){
            if(waterNum >= 1)
                waterNum--;
        }
        else {
            if (iNum >= 1)
                iNum--;
        }
    }

    public void remove(String type, int count){
        if(type == "Blaze")
            blazeNum -= count;
        else if(type == "Empty")
            emptyNum -= count;
        else if(type == "i")
            iNum -= count;
        else if(type == "Light")
            lightNum -= count;
        else if(type == "Void")
            voidNum -= count;
        else if(type == "Water")
            waterNum -= count;
        else
            iNum -= count;
    }

    public int getBlazeNum() {
        return blazeNum;
    }

    public void setBlazeNum(int blazeNum) {
        this.blazeNum = blazeNum;
    }

    public int getiNum() {
        return iNum;
    }

    public void setiNum(int iNum) {
        this.iNum = iNum;
    }

    public int getLightNum() {
        return lightNum;
    }

    public void setLightNum(int lightNum) {
        this.lightNum = lightNum;
    }

    public int getVoidNum() {
        return voidNum;
    }

    public void setVoidNum(int voidNum) {
        this.voidNum = voidNum;
    }

    public int getWaterNum() {
        return waterNum;
    }

    public void setWaterNum(int waterNum) {
        this.waterNum = waterNum;
    }

}
