package com.brandontruong.cltr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class CltrGame extends Game {
	SpriteBatch batch;
    BitmapFont font;
	Texture teal, orange;
    private Viewport viewport;
    OrthographicCamera camera;
	
	@Override
	public void create () {
        Grid grid = new Grid("1.cltr");
        camera = new OrthographicCamera();
        ExtendViewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        this.setScreen(new GameScreen(grid, viewport));
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

    }
}
