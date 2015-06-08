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
                            //grid.changeProbabilityAround(Block.BLAZEBLOCK, x, y, .1, 1);
                            //grid.changeProbabilityAround(Block.BLAZEBLOCK, x, y, .05, 2);
                            // add change position to pull iblocks in this direction
                            grid.changeProbabilityTo(Block.BLAZEBLOCK, x, y, 10);
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
                            //grid.changeProbabilityAround(Block.IBLOCK, x, y, .5, 1);
                            //grid.changeProbabilityAround(Block.IBLOCK, x, y, 0.1, 2);
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
                            //grid.changeProbabilityAround(Block.WATERBLOCK, x, y, .01, 1);

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
                    Logger.CLTR(b.getType());
                    switch (b.getType()) {
                        case Block.BLAZEBLOCK:
                            // Other blazeblocks pull a little more potential, water is also attractive
                            for(Sentinel s : sentinels){

                            }

                            break;
                            //continue blockspace;
                        case Block.EMPTYBLOCK:
                            break;
                        case Block.GOALBLOCK:
                            // skip, stationary
                            break;
                            //continue blockspace;
                        case Block.IBLOCK:
                            // Other iblocks pull a little more potential

                            for(Sentinel s : sentinels){
                                handleIForce(s, grid, x, y);
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

                // Whichever one is highest.
                int highest = getHighestPotential(grid.g[x][y].potentials);
                grid.g[x][y].replace(highest);


            }
        }
    }


    public void applyForceToPotentials(Force f, BlockSpace b){

    }

    /**
     * Handle interactions for i with other block
     * @param s Sentinel to be analyzed for the given blockspace
     * @param x iBlock x
     * @param y iBlock y
     */
    public void handleIForce(Sentinel s, Grid g, int x, int y){
        switch(s.blocktype){
            case(Block.BLAZEBLOCK):
                Force f = new Force(x, y, s.getX(), s.getY());
                int[] space = g.getSpace(f.getDir(), x, y, 1);
                double factor = f.getMagnitude() * 2;
                g.changeProbability(Block.IBLOCK, space[0], space[1], factor);
                /*
                check distance for 1 from the original when you change it. then add
                a moved already and then reduce the probability of the other surrounding blocks.
                Or ---
                 */
        }

    }

    /**
     *
     * @param potentials
     * @return Index of highest potential
     */
    public int getHighestPotential(double[] potentials){
        int max = 0;

        for(int i = 0; i < potentials.length; i++){
            if(potentials[i] > potentials[max])
                max = i;
        }

        return max;
    }

    /**
     * Checks if given space is valid to be placed down on
     * @param type
     * @param x
     * @param y
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
