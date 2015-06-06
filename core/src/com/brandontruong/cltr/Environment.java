package com.brandontruong.cltr;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by btru on 5/10/15.
 */
public class Environment {
    public Grid grid;
    public Toolbelt toolbelt;
    public ArrayList<Sentinel> sentinels;
    private Timer timer;

    public Environment(Grid _grid){
        grid = _grid;
        toolbelt = _grid.toolbelt;
        sentinels = new ArrayList<Sentinel>();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        }, 1000, 1000);
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
                            grid.changeProbabilityAround(Block.BLAZEBLOCK, x, y, 3, 1);
                            grid.changeProbabilityAround(Block.BLAZEBLOCK, x, y, 1, 2);
                            // add change position to pull iblocks in this direction
                            sentinels.add(new Sentinel(Block.BLAZEBLOCK, x, y));
                            break;
                        case Block.EMPTYBLOCK:
                            // Do nothing
                            break;
                        case Block.GOALBLOCK:
                            // Do nothing
                            break;
                        case Block.IBLOCK:
                            // Increase probability around to become i, distance 1 is higher, distance two is lower
                            grid.changeProbabilityAround(Block.IBLOCK, x, y, 3, 1);
                            grid.changeProbabilityAround(Block.IBLOCK, x, y, 0.5, 2);
                            break;
                        case Block.LIGHTBLOCK:
                            // Increase probability of i in the direction of this light when you
                            // analyze for an iBlock.
                            sentinels.add(new Sentinel(Block.BLAZEBLOCK, x, y));
                            break;
                        case Block.VOIDBLOCK:
                            // Do nothing
                            break;
                        case Block.WATERBLOCK:
                            // Has small chance of growing, increases probability of i in direction,
                            // less strong of a pull than light
                            grid.changeProbabilityAround(Block.WATERBLOCK, x, y, 1, 1);

                            sentinels.add(new Sentinel(Block.WATERBLOCK, x, y));
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

        double c;
        // Loop through each block again, and with changes taken into account to see potentials.
        for(int x = 0; x < grid.getCols(); x++) {
            for (int y = 0; y < grid.getRows(); y++) {
                // Loop through each block in each blockspace.
                for (Block b : grid.g[x][y]) {
                    // Depending on the block, loop through sentinel to increase potentials, then as
                    // long. Sentinels interact with blocks in question
                    // This is for the
                    switch (b.getType()) {
                        case Block.BLAZEBLOCK:
                            // Other blazeblocks pull a little more potential, water is also attractive
                            for(Sentinel s : sentinels){
                                if(s.blocktype == Block.BLAZEBLOCK) {

                                }
                                if(s.blocktype == Block.WATERBLOCK){

                                }
                            }


                            break;
                        case Block.EMPTYBLOCK:

                        case Block.GOALBLOCK:
                            // skip, stationary
                            break;
                        case Block.IBLOCK:
                            // Other iblocks pull a little more potential
                            for(Sentinel s : sentinels){

                            }
                            break;
                        case Block.LIGHTBLOCK:
                            // skip, stationary
                            break;
                        case Block.VOIDBLOCK:
                            // skip, stationary
                            break;
                        case Block.WATERBLOCK:
                            // random movement, not attracted to anything, only to adjacents
                        case Block.OBSTACLEBLOCK:
                            // skip, stationary
                            break;
                        default:
                            break;
                    }
                }

                // Check potentials and decide what this block should become.
                // Chance to become two blocks will come later.

                c = Sentinel.chance(100);
                if(c > 50)
                    grid.g[x][y].replace(BlockSpace.newBlock(1, x, y));
                else
                    grid.g[x][y].replace(BlockSpace.newBlock(2, x, y));
            }
        }
    }

    public void handleI(Sentinel c, int x, int y){

    }

    public Force pullForce(int x, int y, int _x, int _y, double magnitude){
        int xDif = _x - x;
        int yDif = _y - y;
        Force f = new Force();
        int sign;

        String axis = (Math.max(Math.abs(xDif), Math.abs(yDif)) == xDif) ? "x" : "y";
        f.setMagnitude(magnitude);

        if(axis == "x"){
            sign = (int) Math.signum(xDif);

            if(sign == 1)
                f.setDir(Force.direction.RIGHT);
            else
                f.setDir(Force.direction.LEFT);

            return f;
        } else { // y
            sign = (int) Math.signum(yDif);

            if(sign == 1)
                f.setDir(Force.direction.ABOVE);
            else
                f.setDir(Force.direction.RIGHT);

            return f;
        }
    }

    /**
     * Checks if given space is valid to be placed down on
     * @param type
     * @param x
     * @param y
     * @return
     */
    public boolean isValidSpace(int type, int x, int y) {
        if (grid.isNotOutOfBounds(x, y)) {
            // Later check for symbiosis, but for now keep it simple
            // Check if it's one of these blocks:
            if(        type == Block.VOIDBLOCK
                    || type == Block.GOALBLOCK
                    || type == Block.OBSTACLEBLOCK) {

                return false;
            } else {
                return true;
            }
        }

        return false;
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
