package com.brandontruong.cltr;

/**
 * Created by btru on 5/10/15.
 */
public class Environment {
    public Grid grid;
    public Toolbelt toolbelt;

    public Environment(Grid _grid, Toolbelt _toolbelt){
        grid = _grid;
        toolbelt = _toolbelt;
    }

    public void placeBlock(Block b, int x, int y){
        grid.g[x][y].add(b);
    }

    public void placeBlock(String type, int x, int y){
        grid.g[x][y].add(BlockSpace.newBlock(type, x, y));
    }


}
