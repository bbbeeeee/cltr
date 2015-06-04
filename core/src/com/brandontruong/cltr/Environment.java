package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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
        toolbelt = _grid.toolbelt;
    }

    /**
     * Update all block data. Basically the command function to analyze all the changes necessary for the next iteration.
     */
    public void refresh(){
        // Mode: goal oriented.
        // Loop through grid spaces.
        for(int x = 0; x < grid.getCols(); x++){
            for(int y = 0; y < grid.getRows(); y++){
                // Loop through each block in each space.
                for(Block b : grid.g[x][y]){
                    // Analyze the necessary changes
                    // Make changes to blockspaces as needed
                    switch(b.getType()){
                        case Block.BLAZEBLOCK:
                            // Increase Blaze probability around this block
                            grid.changeProbabilityAround(Block.BLAZEBLOCK, x, y, 2, 1); // should increase i in gradient.
                            // add change position to pull iblocks in this direction
                            break;
                        case Block.EMPTYBLOCK:
                            // Do nothing
                            break;
                        case Block.GOALBLOCK:
                            // Do nothing
                            break;
                        case Block.IBLOCK:
                            // Increase probability around to become i
                            grid.changeProbabilityAround(Block.IBLOCK, x, y, 1, 1);
                            break;
                        case Block.LIGHTBLOCK:
                            // Increase probability of i in the direction of this light when you
                            // analyze for an iBlock.
                            
                            break;
                        case Block.VOIDBLOCK:
                            // Do nothing
                            break;
                        case Block.WATERBLOCK:
                            // Has small chance of growing, increases probability of i in direction,
                            // less strong of a pull than light
                            break;
                        case Block.OBSTACLEBLOCK:
                            // Do nothing
                            break;
                        default:
                            // Do nothing
                            break;
                    }
                }
            }
        }
    }


    /**
     * Place a new block in a blockspace.
     * @param b Block to be placed
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void placeBlock(Block b, int x, int y){
        grid.g[x][y].add(b);
    }

    /**
     * Place a new block in a blockspace.
     * @param type Type of block to be placed.
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void placeBlock(int type, int x, int y){
        grid.g[x][y].add(BlockSpace.newBlock(type, x, y));
    }

    /**
     * Move block to indicated space.
     * @param index
     * @param x
     * @param y
     * @param _x
     * @param _y
     */
    public void moveBlock(int index, int x, int y, int _x, int _y){

    }

    /**
     * Move an entire blockspace.
     * @param x
     * @param y
     * @param _x
     * @param _y
     */
    public void moveBlockSpace(int x, int y, int _x, int _y){

    }

    public void update(float delta){

    }

}
