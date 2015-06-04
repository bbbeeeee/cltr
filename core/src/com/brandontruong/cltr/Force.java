package com.brandontruong.cltr;

/**
 * Created by btru on 6/4/15.
 */
public class Force {
    private enum direction{
        ABOVE, BELOW, LEFT, RIGHT
    }

    private direction dir;
    private double magnitude;

    public Force(direction _dir, double _mag){
        dir = _dir;
        magnitude = _mag;
    }

}
