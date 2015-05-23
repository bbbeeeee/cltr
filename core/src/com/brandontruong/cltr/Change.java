package com.brandontruong.cltr;

import java.util.Random;

/**
 * Created by btru on 5/22/15.
 */
public class Change {

    public static double chance(double probability){
        Random random = new Random();
        return random.nextDouble() * probability;
    }
}
