package com.brandontruong.cltr.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.brandontruong.cltr.Block;

/**
 * Created by btru on 5/9/15.
 */
public class WaterBlock extends Block {
        public final double attractiveness = .2;
        // public static final Color color = new Color(102/255f, 153/255f, 153/255f, 1);

        /**
         * Standard WaterBlock constructor.
         * @param x X position
         * @param y Y position
         */
        public WaterBlock(int x, int y){
            setColor(new Color(102/255f, 153/255f, 153/255f, 1));
            setX(x);
            setY(y);
            setType(WATERBLOCK);
            setSymbiosis(0);
            setGrowthFactor(5.5f);
        }

}

