package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/9/15.
 */
public class WaterBlock extends Block {
        public static final double attractiveness = .2;
        public static final Color color = new Color(102, 153, 153, 1);

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

