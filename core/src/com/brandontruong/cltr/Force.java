package com.brandontruong.cltr;

/**
 * Created by btru on 6/4/15.
 */
public class Force {
    public static enum direction{
        ABOVE, BELOW, LEFT, RIGHT
    }

    private direction dir;

    private double magnitude;

    public Force(){

    }

    public Force(direction _dir, double _mag){
        dir = _dir;
        magnitude = _mag;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public direction getDir() {
        return dir;
    }

    public void setDir(direction dir) {
        this.dir = dir;
    }

}
