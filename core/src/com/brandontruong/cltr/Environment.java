package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;

/**
 * Created by btru on 5/10/15.
 */
public class Environment {
    public Grid grid;
    public Toolbelt toolbelt;

    public Environment(){

    }

    public Environment(Grid _grid){
        grid = _grid;
        // toolbelt = _toolbelt;
    }

    /**
     * Update all block data
     * @param delta
     */
    public void update(float delta){

    }

    public void placeBlock(Block b, int x, int y){
        grid.g[x][y].add(b);
    }

    public void placeBlock(String type, int x, int y){
        grid.g[x][y].add(BlockSpace.newBlock(type, x, y));
    }

}
