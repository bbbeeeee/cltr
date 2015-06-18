package com.brandontruong.cltr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.Viewport;

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
    public Timer timer;
    private int food;
    private Viewport viewport;
    private Screen gamescreen;
    public boolean won = false;
    private boolean first = false;

    public Environment(Grid _grid, Viewport v, Screen gs){
        gamescreen = gs;
        grid = _grid;
        toolbelt = _grid.toolbelt;
        sentinels = new ArrayList<Sentinel>();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        }, 1000, 500);
        viewport = v;
    }

    /**
     * Update all block data. Basically the command function to analyze
     * all the changes necessary for the next iteration.
     */
    public void refresh(){
        sentinels.clear();
        // Loop through grid spaces.
        int highest;
        int toChangeType;
        int[] currentIPosition = new int[2];

        for(int x = grid.xOffset; x < grid.getCols() + grid.xOffset; x++){
            for(int y = grid.yOffset; y < grid.getRows() + grid.yOffset; y++){
                // Loop through each block in each space.
                grid.g[x][y].potentials = new int[Block.totalBlocks];
                for(Block b : grid.g[x][y]){
                    // Analyze the necessary changes
                    // Make changes to blockspaces as needed
                    switch(b.getType()){
                        case Block.OBSTACLEBLOCK:
                            grid.changeProbabilityTo(Block.OBSTACLEBLOCK, x, y, 10);
                            break;
                        case Block.BLAZEBLOCK:
                            grid.changeProbabilityTo(Block.BLAZEBLOCK, x, y, 10);

                            sentinels.add(new Sentinel(Block.BLAZEBLOCK, x, y));
                            break;
                        case Block.GOALBLOCK:
                            grid.changeProbabilityTo(Block.GOALBLOCK, x, y, 10);
                            break;
                        case Block.IBLOCK:
                            grid.changeProbabilityTo(Block.IBLOCK, x, y, 0);
                            break;
                        case Block.ELECTRICITYBLOCK:
                            grid.changeProbabilityTo(Block.ELECTRICITYBLOCK, x, y, 10);
                            sentinels.add(new Sentinel(Block.ELECTRICITYBLOCK, x, y));
                            break;
                        case Block.WATERBLOCK:
                            sentinels.add(new Sentinel(Block.WATERBLOCK, x, y));
                            break;
                        case Block.VOIDBLOCK:
                            grid.changeProbabilityTo(Block.VOIDBLOCK, x, y, 15);
                            break;
                    }
                }
            }
        }

        // Loop through each block again, and with changes taken into account to see potentials.
        for(int x = grid.xOffset; x < grid.getCols() + grid.xOffset; x++){
            for(int y = grid.yOffset; y < grid.getRows() + grid.yOffset; y++){
                // Loop through each block in each blockspace.
                for (Block b : grid.g[x][y]) {
                    // Depending on the block, loop through sentinel to increase potentials, then as
                    // long. Sentinels interact with blocks in question
                    switch (b.getType()) {
                        case Block.BLAZEBLOCK:
                            grid.changeProbabilityAroundRandom(Block.BLAZEBLOCK, x, y, 5.1f);
                            break;
                        case Block.GOALBLOCK:
                            // skip, stationary
                            break;
                            //continue blockspace;
                        case Block.IBLOCK:
                            currentIPosition = new int[]{x, y};
                            handleIForces(sentinels, x, y);
                            break;
                        case Block.ELECTRICITYBLOCK:
                            // skip, stationary
                            break;
                        case Block.VOIDBLOCK:
                            // skip, stationary
                            break;
                        case Block.WATERBLOCK:
                            // random movement, not attracted to anything, only to adjacents
                            grid.changeProbabilityAroundRandom(Block.WATERBLOCK, x, y, 5.3f);
                        case Block.OBSTACLEBLOCK:
                            // skip, stationary
                            break;
                    }
                }
            }
        }

        for(int x = grid.xOffset; x < grid.getCols() + grid.xOffset; x++){
            for(int y = grid.yOffset; y < grid.getRows() + grid.yOffset; y++) {
                highest = getHighestPotential(grid.g[x][y].potentials);
                toChangeType = grid.g[x][y].get(0).getType();
                if (highest == Block.IBLOCK) {
                    switch (toChangeType) {
                        case (Block.OBSTACLEBLOCK):
                        case (Block.ELECTRICITYBLOCK):
                            if(currentIPosition[0] != 0 && currentIPosition[1] != 0)
                                grid.g[currentIPosition[0]][currentIPosition[1]].replace(Block.IBLOCK);

                            L.CLTR("Hit obstacle");
                            break;
                        case (Block.GOALBLOCK):
                            won = true;
                            timer.cancel();
                            L.CLTR("Winner!");
                            break;
                        case (Block.FOODBLOCK):
                            // Acquire food.
                            food++;
                            L.CLTR("Food Acquired!");
                            break;
                        case (Block.BLAZEBLOCK):
                            // either
                            won = true;
                            timer.cancel();
                            L.CLTR("Hit fire, you lose!");
                            break;
                        default:
                            grid.g[x][y].replace(highest);
                            break;
                    }
                } else if(highest == Block.WATERBLOCK){
                    switch(toChangeType){
                        case (Block.ELECTRICITYBLOCK):
                            break;

                    }
                } else {
                    switch(toChangeType){
                        case (Block.OBSTACLEBLOCK):
                        case (Block.GOALBLOCK):
                        case (Block.ELECTRICITYBLOCK):
                            break;
                        default:
                            grid.g[x][y].replace(highest);
                            break;
                    }
                }
            }
        }
    }

    /**
     * Handle interactions for i with other blocks
     * @param sentinels Sentinels to be analyzed for the given blockspace
     * @param x iBlock x
     * @param y iBlock y
     */
    public void handleIForces(ArrayList<Sentinel> sentinels, int x, int y) {
        Force xF, yF;
        double xForce = 0, yForce = 0;
        for (Sentinel s : sentinels) {
            switch (s.blocktype) {
                case (Block.ELECTRICITYBLOCK):
                case (Block.BLAZEBLOCK):
                    if ((x - s.getX()) != 0)
                        xForce += (s.getX() - x);
                    if ((y - s.getY()) != 0)
                        yForce += (s.getY() - y);
                    break;
                case (Block.FOODBLOCK):
                    break;
                case (Block.WATERBLOCK):
                    break;
            }
        }

        int sign;
        int[] space;

        if (xForce == yForce && xForce != 0) {
            L.CLTR("The same");

            if (Math.signum(xForce) == 1) {
                xF = new Force(Grid.RIGHT, Math.abs(xForce));
            } else {
                xF = new Force(Grid.LEFT, Math.abs(xForce));
            }

            if (Math.signum(yForce) == 1) {
                yF = new Force(Grid.ABOVE, Math.abs(xForce));
            } else {
                yF = new Force(Grid.BELOW, Math.abs(xForce));
            }

        } else if (xForce != 0 || yForce != 0) {
            sign = (int) Math.signum(xForce);
            double c = Sentinel.chance(1);

            if (sign == 1){
                xF = new Force(Grid.RIGHT, Math.abs(xForce));
            } else if(sign == -1){
                xF = new Force(Grid.LEFT, Math.abs(xForce));
            } else {
                xF = new Force(Grid.HERE, 1);
            }

            sign = (int) Math.signum(yForce);

            if (sign == 1){
                yF = new Force(Grid.ABOVE, Math.abs(xForce));
            } else if(sign == -1){
                yF = new Force(Grid.BELOW, Math.abs(xForce));
            } else {
                yF = new Force(Grid.HERE, 1);
            }

            if(Math.abs(xForce) == 1){
                if(c > .5){
                    if (sign == 1){
                        xF = new Force(Grid.RIGHT, Math.abs(xForce));
                    } else if(sign == -1){
                        xF = new Force(Grid.LEFT, Math.abs(xForce));
                    }
                } else {
                    xF = new Force(Grid.HERE, 1);
                }
            }

            if(Math.abs(yForce) == 1){
                if(c < .5){
                    if (sign == 1){
                        yF = new Force(Grid.ABOVE, Math.abs(xForce));
                    } else if(sign == -1){
                        yF = new Force(Grid.BELOW, Math.abs(xForce));
                    } else {
                        yF = new Force(Grid.HERE, 1);
                    }
                }
            }
        } else {
            xF = new Force(Grid.HERE, 1);
            yF = new Force(Grid.HERE, 1);
        }

        if(first){
            space = grid.getValidISpace(xF.getDir(), x, y, 1);
            space = grid.getValidISpace(yF.getDir(), space[0], space[1], 1);

        } else {
            space = grid.getSpace(Grid.HERE, x, y, 1);
            first = true;
        }

        if (grid.isNotOutOfBounds(space[0], space[1])) {
            grid.changeProbabilityTo(Block.IBLOCK, space[0], space[1], 13);
        } else {
            L.CLTR("Out of bounds");
            grid.changeProbabilityTo(Block.IBLOCK, x, y, 11);
        }
    }

    /**
     *
     * @param potentials
     * @return Index of highest potential
     */
    public int getHighestPotential(int[] potentials){
        int max = 0;
        int maxPotential = 0;

        for(int i = 0; i < potentials.length; i++){
            if(potentials[i] > potentials[max]) {
                max = i;
                maxPotential = potentials[max];
            }
        }

        if(maxPotential > 0) {
            return max;
        } else {
            return Block.EMPTYBLOCK;
        }
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
     * @param b
     * @param x
     * @param y
     * @param _x
     * @param _y
     */
    public void moveBlock(Block b, int x, int y, int _x, int _y){
        grid.g[x][y].replace(b);
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
