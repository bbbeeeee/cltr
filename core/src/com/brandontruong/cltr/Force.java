package com.brandontruong.cltr;

import java.util.ArrayList;

/**
 * Created by btru on 6/4/15.
 */
public class Force {
    public static enum direction{
        ABOVE, BELOW, LEFT, RIGHT
    }

    //TODO: make interaction factors here
    public static double BLAZE_I = 1.0;

    private int dir;

    private double magnitude;

    /**
     * Force constructor for determining direction and magnitude based on two points.
     * @param x
     * @param y
     * @param _x
     * @param _y
     */
    public Force(int x, int y, int _x, int _y){
        int xDif = _x - x;
        int yDif = _y - y;
        int sign;

        String axis = (Math.max(Math.abs(xDif), Math.abs(yDif)) == xDif) ? "x" : "y";
        setMagnitude(Grid.distance(x, y, _x, _y));
        if(axis == "x"){
            sign = (int) Math.signum(xDif);

            if(sign == 1)
                setDir(Grid.RIGHT);
            else
                this.setDir(Grid.LEFT);
        } else { // y
            sign = (int) Math.signum(yDif);

            if(sign == 1)
                setDir(Grid.ABOVE);
            else
                setDir(Grid.BELOW);
        }
    }

    /**
     * Force constructor for given direction and magnitude.
     * @param _dir
     * @param _mag
     */
    public Force(int _dir, double _mag){
        dir = _dir;
        magnitude = _mag;
    }

//    public Force addTogether(ArrayList<Force> forces){
//        for(Force f : forces){
//
//        }
//    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

}
