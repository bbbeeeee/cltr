package com.brandontruong.cltr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by brandon on 6/13/15.
 */
public class LevelsScreen implements Screen{
    private OrthographicCamera camera;
    private Viewport viewport;
    private Table table;
    private BitmapFont font;
    private Stage stage;
    private Skin buttonSkin;
    private ScrollPane scrollPane;
    public LevelsScreen() {
        super();
    }

    public LevelsScreen(Viewport v){
        viewport = v;
        table = new Table();
        stage = new Stage();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.setCamera(camera);

        createLevelButtons();
    }

    public void createLevelButtons(){
        font = new BitmapFont(Gdx.files.internal("fonts/hn.fnt"),
                Gdx.files.internal("fonts/hn.png"), false);

        //font.getData().setScale(L.fontSize(28)/28, L.fontSize(28)/28);
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth()/4,
                (int) Gdx.graphics.getHeight()/10,
                Pixmap.Format.RGB888);

        buttonSkin.add("background", new Texture(pixmap));

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = buttonSkin.newDrawable("background", Color.GREEN);
        buttonStyle.down = buttonSkin.newDrawable("background", Color.GREEN);
        buttonStyle.checked = buttonSkin.newDrawable("background", Color.GREEN);
        buttonStyle.over = buttonSkin.newDrawable("background", Color.GREEN);

        buttonSkin.add("default", buttonStyle);
    }

    @Override
    public void show() {
        Table subContainer = new Table();
        for(int i = 1; i < 20; i++){
            table.row();
            Button b = new Button(buttonSkin);
            Table t = new Table();

            final int _i = i;
            t.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(
                            new GameScreen(new Grid(Integer.toString(_i) + ".cltr"),
                                    viewport));
                }
            });

            b.add(new Label(Integer.toString(_i),
                    new Label.LabelStyle(font, Color.WHITE)));

            t.add(b);
            // t.setFillParent(true);

            subContainer.add(t).padBottom(20);
            subContainer.row();
        }


        //table.setFillParent(true);
        scrollPane = new ScrollPane(subContainer);
        table.add(scrollPane);
        table.setFillParent(true);

        scrollPane.setWidth(Gdx.graphics.getWidth());
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

    }

    @Override
    public void dispose() {

    }
}
