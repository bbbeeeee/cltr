package com.brandontruong.cltr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CltrGame extends Game {
	SpriteBatch batch;
    BitmapFont font;
	Texture teal, orange;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        teal = new Texture("teal.png");
        orange = new Texture("orange.png");
        font = new BitmapFont();
        font.setColor(Color.RED);
        BlockSpace[] blockspaces = new BlockSpace[2];
        blockspaces[0] = new BlockSpace(3, 2);
        //blockspaces[1] = new BlockSpace(2, 2);
        blockspaces[1] = new BlockSpace(1, 4);
        blockspaces[0].add(BlockSpace.newBlock("i", 3, 2));
        blockspaces[1].add(BlockSpace.newBlock("Blaze", 3, 2));
        Grid grid = new Grid("1.cltr");
        this.setScreen(new GameScreen(grid));
	}

	@Override
	public void render () {
        super.render();
	}

    public void resize (int width, int height) {

    }

    public void pause () {

    }

    public void resume () {

    }

    public void dispose () {
        batch.dispose();
        font.dispose();
    }
}
