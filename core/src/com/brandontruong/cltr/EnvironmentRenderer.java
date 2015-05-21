package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by btru on 5/10/15.
 */
public class EnvironmentRenderer {

    private Environment environment;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    public EnvironmentRenderer(Environment e){
        environment = e;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 800, 480);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    /**
     * Render blockdata from environment
     */
    public void render(){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < environment.grid.getRows(); i++){
            for(int j = 0; j < environment.grid.getCols(); j++){
                renderBlockSpace(environment.grid.g[i][j], i, j);
            }
        }
        shapeRenderer.end();
    }

    public void renderBlockSpace(BlockSpace blockspace, int x, int y){
        for(int i = 0; i < blockspace.size(); i++){
            shapeRenderer.setColor(blockspace.get(i).color);
            shapeRenderer.rect((x - 1) * 50, (y - 1) * 50, 50, 50);
        }
    }

}
