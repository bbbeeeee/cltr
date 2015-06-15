package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.brandontruong.cltr.Blocks.BlazeBlock;

/**
 * Created by btru on 5/21/15.
 */
public class BlockActor extends Actor {
    Texture texture;

    public BlockActor(int type){

    }

    public BlockActor(int type, float x, float y, float width, float height){
        switch(type){
            case Block.BLAZEBLOCK:
                texture = new Texture(Gdx.files.internal("blaze.png"));
                break;
            case Block.EMPTYBLOCK:
                texture = new Texture(Gdx.files.internal("empty.png"));
                break;
            case Block.GOALBLOCK:
                texture = new Texture(Gdx.files.internal("goal.png"));
                break;
            case Block.IBLOCK:
                texture = new Texture(Gdx.files.internal("i.png"));
                break;
            case Block.LIGHTBLOCK:
                texture = new Texture(Gdx.files.internal("light.png"));
                break;
            case Block.VOIDBLOCK:
                texture = new Texture(Gdx.files.internal("void.png"));
                break;
            case Block.WATERBLOCK:
                texture = new Texture(Gdx.files.internal("water.png"));
                break;
            case Block.OBSTACLEBLOCK:
                texture = new Texture(Gdx.files.internal("obstacle.png"));
                break;
            case Block.FOODBLOCK:
                texture = new Texture(Gdx.files.internal("food.png"));
                break;
            case Block.SELECTEDBLOCK:
                texture = new Texture(Gdx.files.internal("selected.png"));
                break;
            case Block.XBLOCK:
                texture = new Texture(Gdx.files.internal("x.png"));
                break;
            default:
                texture = new Texture(Gdx.files.internal("empty.png"));
        }

        setBounds(x, y, width, height);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

}
