package com.brandontruong.cltr;

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
    public ToolbeltStage toolbeltStage;

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

//
//        BlockActor block = new BlockActor(Block.BLAZEBLOCK, blockWidth, blockHeight);
        // generate actors based on what's in the toolbelt.
        // display numbers next to them
        // when active, put a white or black border around them.
        // stage.addActor(block);
    }

    /**
     * Render blockdata from environment
     */
    public void render() {
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
        // Render toolbelt/menu


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
