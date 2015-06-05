package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by btru on 5/10/15.
 */
public class EnvironmentRenderer {

    private Environment environment;
    private Camera camera;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private int worldHeight, worldWidth;
    private float blockWidth, blockHeight;
    private float aspectRatio;
    private Stage stage;

    public EnvironmentRenderer(Environment e, Viewport _viewport){
        viewport = _viewport;
        environment = e;
        // camera = new OrthographicCamera();
        //viewport = new ExtendViewport(800, 400, camera);
        //viewport.apply(true);

        // camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        // aspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        blockHeight = Gdx.graphics.getHeight() /  12;
        // blockWidth = blockHeight;
        blockWidth = blockHeight;
        Logger.CLTR("Rendering environment");
        Logger.CLTR("Rows - " + Integer.toString(environment.grid.getRows()));
        Logger.CLTR("Cols - " + Integer.toString(environment.grid.getCols()));
        Logger.CLTR("Block height - " + Float.toString(blockHeight));
        camera = viewport.getCamera();
        shapeRenderer = new ShapeRenderer();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

//        stage = new ToolbeltStage(viewport, e.toolbelt);
//        stage.setViewport(viewport);
//        Gdx.input.setInputProcessor(stage);
//
//        BlockActor block = new BlockActor(Block.BLAZEBLOCK, blockWidth, blockHeight);

        // stage.addActor(block);
    }

    /**
     * Render blockdata from environment
     */
    public void render() {
        camera.update();
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        aspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        blockHeight = Gdx.graphics.getHeight() / 12;
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < 18; i++){
            for(int j = 0; j < 12; j++){
                renderBlockSpace(environment.grid.g[i][j], i, j, blockWidth, blockHeight);
            }
        }
        shapeRenderer.end();
    }

    public void resize(int width, int height){
        Logger.CLTR("resized: " + Integer.toString(height));
        blockHeight = Gdx.graphics.getHeight() /  12;
        viewport.update(width, height, true);
    }

    /**
     * Render individual blockspace --- will probably have to remake with stage and actors.
     * @param blockspace
     * @param x
     * @param y
     */
    public void renderBlockSpace(BlockSpace blockspace, int x, int y, float blockWidth, float blockHeight){
        // Render toolbelt/menu


        // Render grid
        for(int i = 0; i < blockspace.size(); i++){
            try {
                shapeRenderer.setColor(blockspace.get(i).color);
            } catch (NullPointerException e){
                Logger.CLTR(e.toString());
            } finally {
                // Come up with a way to show both.
                shapeRenderer.rect((x) * blockWidth, (y) * blockHeight,  blockWidth, blockHeight);
            }
        }
    }

}
