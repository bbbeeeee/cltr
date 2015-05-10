package com.brandontruong.cltr.Blocks;

import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/9/15.
 */
public class WaterBlock extends Block {
        private final double attractiveness = .2;

        /**
         * Standard WaterBlock constructor.
         * @param x X position
         * @param y Y position
         */
        public WaterBlock(int x, int y){
            setX(x);
            setY(y);
            setSymbiosis(1);
        }

}

