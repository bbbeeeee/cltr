package com.brandontruong.cltr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by btru on 5/5/15.
 */
public class MainMenuScreen implements Screen{

    // final CltrGame game;
    Skin goSkin;
    OrthographicCamera camera;
    private BitmapFont font;
    private ShapeRenderer sr = new ShapeRenderer();
    private Table table = new Table();
    private Stage stage = new Stage();
    private Label title;
    private Viewport viewport;

    TextButton goButton;
    public MainMenuScreen(Viewport v) {
        // this.game = gam;
        viewport = v;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.setCamera(camera);
        createSkin();

        goButton = new TextButton("Levels", goSkin);
    }

    private void createSkin(){
        BitmapFont font = new BitmapFont();
        goSkin = new Skin();
        goSkin.add("default", font);

        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth()/4,
                (int) Gdx.graphics.getHeight()/10,
                Pixmap.Format.RGB888);

        goSkin.add("background", new Texture(pixmap));

        TextButton.TextButtonStyle goButtonStyle = new TextButton.TextButtonStyle();
        goButtonStyle.up = goSkin.newDrawable("background", Color.GREEN);
        goButtonStyle.down = goSkin.newDrawable("background", Color.GREEN);
        goButtonStyle.checked = goSkin.newDrawable("background", Color.GREEN);
        goButtonStyle.over = goSkin.newDrawable("background", Color.GREEN);
        goButtonStyle.font = goSkin.getFont("default");

        // title = new Label("CLTR", goSkin);
        goSkin.add("default", goButtonStyle);
    }

    @Override
    public void show() {
        goButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //super.clicked(event, x, y);
                ((Game)Gdx.app.getApplicationListener()).setScreen(
                        new GameScreen(new Grid("1.cltr"), viewport));
            }
        });
        table.add(title).padBottom(40).row();
        table.add(goButton).size(150, 60).padBottom(20).row();
        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        // game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        goSkin.dispose();
    }
}
