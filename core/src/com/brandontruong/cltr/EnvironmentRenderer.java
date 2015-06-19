package com.brandontruong.cltr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by btru on 5/10/15.
 */
public class EnvironmentRenderer {

    private Environment environment;
    private Camera camera;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private int worldHeight, worldWidth;
    private float aspectRatio;
    public Stage stage;

    private boolean rendered;
    public float leftOffset;
    public float blockWidth, blockHeight;

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
        L.CLTR("Rendering environment");
        L.CLTR("Rows - " + Integer.toString(environment.grid.getRows()));
        L.CLTR("Cols - " + Integer.toString(environment.grid.getCols()));
        L.CLTR("Block height - " + Float.toString(blockHeight));
        camera = viewport.getCamera();
        shapeRenderer = new ShapeRenderer();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        leftOffset = Gdx.graphics.getWidth() - blockWidth * 18;

        stage = new Stage(viewport);
        // stage.addActor(new BlockActor(Block.WATERBLOCK, 50, 50, 100, 100));
    }

    /**
     * Render blockdata from environment
     */
    public void render() {
        if(environment.won){
            environment.timer.cancel();
            ((Game) Gdx.app.getApplicationListener()).setScreen(
                    new LevelsScreen(viewport));
        }
        camera.update();
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        aspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        blockHeight = Gdx.graphics.getHeight() / 12;
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = environment.grid.xOffset; i < environment.grid.getCols() + environment.grid.xOffset; i++){
            for(int j = environment.grid.yOffset; j < environment.grid.getRows() + environment.grid.yOffset; j++){
                renderBlockSpace(environment.grid.g[i][j], i, j, blockWidth, blockHeight);
            }
        }
        shapeRenderer.end();

        stage.act();
        stage.draw();
    }

    public void addBlockActor(int type, float x, float y){

        stage.addActor(new BlockActor(type, x, y, blockWidth, blockHeight));
    }

    public void resize(int width, int height){
        L.CLTR("resized: " + Integer.toString(height));
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
        // Render grid

        for(int i = 0; i < blockspace.size(); i++){
            try {
                shapeRenderer.setColor(blockspace.get(i).color);
            } catch (NullPointerException e){
                L.CLTR("Something went wrong");
                L.CLTR(e.toString());
            } finally {
                // Come up with a way to show both.
                shapeRenderer.rect(leftOffset + (x) * blockWidth, (y) * blockHeight,  blockWidth, blockHeight);
            }
        }
    }

}
