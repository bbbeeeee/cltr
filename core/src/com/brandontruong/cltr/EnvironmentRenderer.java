package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by btru on 5/10/15.
 */
public class EnvironmentRenderer {

    private Environment environment;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    // private float WORLD_WIDTH, WORLD_HEIGHT;
    private float blockWidth, blockHeight;
    private float aspectRatio;
    private Stage stage;

    public EnvironmentRenderer(Environment e){

        environment = e;
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 400, camera);
        viewport.apply(true);

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        aspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        blockHeight = Gdx.graphics.getHeight() /  environment.grid.getRows();
        blockWidth = blockHeight;

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        stage = new ToolbeltStage(new FitViewport(800, 400), e.toolbelt);

        Gdx.input.setInputProcessor(stage);

        BlockActor block = new BlockActor(Block.BLAZEBLOCK, blockWidth, blockHeight);

        stage.addActor(block);

    }

    /**
     * Render blockdata from environment
     */
    public void render() {
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Get grid dimensions in place.
        aspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        blockHeight = Gdx.graphics.getHeight() /  environment.grid.getRows();
        blockWidth = blockHeight;

        // Render basic grid map.
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 1; i < environment.grid.getCols(); i++){
            for(int j = 1; j < environment.grid.getRows(); j++){
                renderBlockSpace(environment.grid.g[i][j], i, j, blockWidth, blockHeight);
            }
        }
        shapeRenderer.end();

        stage.draw();
        // Toolbelt stage will interact call a placeBlock on where the end is.
    }

    public void resize(int width, int height){

        stage.getViewport().update( (int)(width * aspectRatio), height, false);
    }

    /**
     * Render individual blockspace
     * @param blockspace
     * @param x
     * @param y
     */
    public void renderBlockSpace(BlockSpace blockspace, int x, int y, float blockWidth, float blockHeight){
        for(int i = 0; i < blockspace.size(); i++){
            shapeRenderer.setColor(blockspace.get(i).color);
            shapeRenderer.rect((x-1) * blockWidth + (2 * blockWidth), (y-1) * blockHeight,  blockWidth, blockHeight);
        }
    }

}
