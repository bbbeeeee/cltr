package com.brandontruong.cltr;

/**
 * Created by btru on 5/10/15.
 */
public class Toolbelt {
    private int blazeNum,
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
    public Toolbelt(int b, int i, int l, int v, int w){
        blazeNum = b;
        iNum = i;
        lightNum = l;
        voidNum = v;
        waterNum = w;
    }

    /**
     * Add a block to the toolbelt. Should be called either when a block is acquired or returned from grid.
     * @param type Type of block to be added
     */
    public void add(int type){
        switch (type){
            case 1:
                blazeNum++;
                break;
            case 2:
                iNum++;
                break;
            case 3:
                lightNum++;
                break;
            case 4:
                voidNum++;
                break;
            case 5:
                waterNum++;
                break;
        }
    }

    /**
     * Use a block from the toolbelt. Should be called when a block is lost.
     * @param type
     */
    public void remove(int type){
        switch (type){
            case 1:
                if(blazeNum >= 1)
                    blazeNum--;
                break;
            case 2:
                if(iNum >= 1)
                    iNum--;
                break;
            case 3:
                if(lightNum >= 1)
                    lightNum--;
                break;
            case 4:
                if(voidNum >= 1)
                    voidNum--;
                break;
            case 5:
                if(waterNum >= 1)
                    waterNum--;
                break;
        }
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
