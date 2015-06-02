package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.brandontruong.cltr.Blocks.BlazeBlock;

/**
 * Created by btru on 5/21/15.
 */
public class BlockActor extends Actor {
    Texture texture;

    public BlockActor(int type, float height, float width){
        switch(type){
            case Block.BLAZEBLOCK:
                texture = new Texture(Gdx.files.internal("blaze.png"));
                break;
            case Block.EMPTYBLOCK:
                break;
            case Block.GOALBLOCK:
                break;
            case Block.IBLOCK:
                break;
            case Block.LIGHTBLOCK:
                break;
            case Block.VOIDBLOCK:
                break;
            case Block.WATERBLOCK:
                break;
        }

        setBounds(getX(), getY(), width, height);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.getX(), this.getY());
    }

}
