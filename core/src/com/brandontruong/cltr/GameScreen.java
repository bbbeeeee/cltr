package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by btroo on 5/18/15.
 */
public class GameScreen implements Screen, InputProcessor{
    private Environment environment;
    private EnvironmentRenderer environmentRenderer;
    OrthographicCamera camera;
    private BitmapFont font;
    private ShapeRenderer sr = new ShapeRenderer();
    private Stage stage;

    public GameScreen(Grid grid, Viewport viewport) {
        environment = new Environment(grid);
        environmentRenderer = new EnvironmentRenderer(environment, viewport);

        Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    environment.refresh();
                }
            }, 1000, 1000
        );
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        environment.update(delta);
        environmentRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        environmentRenderer.resize(width, height);
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}