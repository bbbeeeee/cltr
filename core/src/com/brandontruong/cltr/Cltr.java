package com.brandontruong.cltr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cltr extends Game {
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
        this.setScreen(new MainMenuScreen(this));
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
