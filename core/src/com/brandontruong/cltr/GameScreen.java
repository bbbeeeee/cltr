package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by btroo on 5/18/15.
 */
public class GameScreen implements Screen{
    private Environment environment;
    private EnvironmentRenderer renderer;

    OrthographicCamera camera;
    private BitmapFont font;
    private ShapeRenderer sr = new ShapeRenderer();

    public GameScreen(Grid grid) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 800;
        float gameHeight = 480;
        int midpointY = (int) (gameHeight / 2);
        environment = new Environment(grid);
        renderer = new EnvironmentRenderer(environment);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        environment.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
