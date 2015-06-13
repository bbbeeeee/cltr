package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by brandon on 6/13/15.
 */
public class LevelsScreen {
    private OrthographicCamera camera;
    private Viewport viewport;
    private Table table = new Table();
    private BitmapFont font;
    private Stage stage = new Stage();

    public LevelsScreen(Viewport v){
        viewport = v;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.setCamera(camera);
    }

    public void createLevelButtons(){
        font = new BitmapFont();


    }
}
