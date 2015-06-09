package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;

/**
 * Created by btru on 5/6/15.
 */
public class Grid {
    public static final int ABOVE = 1;
    public static final int RIGHT = 2;
    public static final int BELOW = 3;
    public static final int LEFT  = 4;
    public static final int HERE = 0;
    public static final int MAXROWS = 12;
    public static final int MAXCOLS = 18;

    private int rows;
    private int cols;
    public int xOffset;
    public int yOffset;

    public BlockSpace[][] g;
    public Toolbelt toolbelt;

    /**
     * Takes in map file and returns grid for use in Environment. File should contain dimensions, layout, and toolbelt
     * @param file
     */
    public Grid(String file){
        int step = 1;
        FileHandle f = Gdx.files.internal("maps/" + file);
        String text = f.readString();
        String[] lines = text.split("\n");
        toolbelt = new Toolbelt(0, 0, 0, 0, 0, 0);


        for(int i = 0; i < lines.length; i++){
            // Step 1 - dimensions
            if(step == 1){
                String[] dimensions = lines[i].split(",");
                cols = Integer.parseInt(dimensions[0]);
                rows = Integer.parseInt(dimensions[1]);
                g = new BlockSpace[18][12];

                for(int x = 0; x < 18; x++){
                    for(int y = 0; y < 12; y++){
                        g[x][y] = new BlockSpace(x, y);
                        g[x][y].add(BlockSpace.newBlock(Block.VOIDBLOCK, x, y));
                    }
                }
                // get offset for cols and rows
                xOffset = (int) ((18 - cols) / 2);
                yOffset = (int) ((12 - rows) / 2);

                // Add in empty blocks
                for (int k = 0; k < cols; k++) {
                    for (int j = 0; j < rows; j++) {
                        g[k + xOffset][j + yOffset].replace(BlockSpace.newBlock(Block.EMPTYBLOCK, k, j));
                    }
                }

                step = 2;
                continue;
            }

            // Step 2 - block layout
            else if(step == 2){
                if(lines[i].contains("Toolbelt")){
                    step++;
                    continue;
                }

                String[] block = lines[i].split(",");
                int x = Integer.parseInt(block[1]) - 1;
                int y = Integer.parseInt(block[2]) - 1;
                int type = getTypeNum(block[0]);

                g[x + xOffset][y + yOffset].add(BlockSpace.newBlock(type, x, y));
            }

            // Step 3 - toolbelt
            else if(step == 3){
                String[] piece = lines[i].split(",");

                String type = piece[0];
                int count = Integer.parseInt(piece[1]);
                toolbelt.add(type, count);
            }
        }
    }



    public int getTypeNum(String type){
        if(type.contains("blaze")){
            return Block.BLAZEBLOCK;
        } else if(type.contains("empty")) {
            return Block.EMPTYBLOCK;
        } else if(type.contains("food")){
            return Block.FOODBLOCK;
        } else if(type.contains("goal")){
            return Block.LIGHTBLOCK;
        } else if(type.contains("iblock")){
            return Block.IBLOCK;
        } else if(type.contains("light")){
            return Block.LIGHTBLOCK;
        } else if(type.contains("obstacle")){
            return Block.OBSTACLEBLOCK;
        } else if(type.contains("void")){
            return Block.VOIDBLOCK;
        } else if(type.contains("water")){
            return Block.WATERBLOCK;
        } else{
            return Block.EMPTYBLOCK;
        }
    }

    /**
     * Loop through and apply necessary changes
     */
    public void refresh(){
        for(int x = 0; x < getCols(); x++){
            for(int y = 0; y < getRows(); y++){

            }
        }
    }

    public void addProbability(int type, int x, int y, double factor){
        if(isNotOutOfBounds(x, y) && y < 12 && x < 18) {
            g[x][y].potentials[type] += factor;
        }
    }

    public void changeProbabilityTo(int type, int x, int y, double factor){
        Logger.CLTR("Changing at:");
        Logger.CLTR(x);
        Logger.CLTR(y);
        if(isNotOutOfBounds(x, y) && y < 12 && x < 18) {
            g[x][y].potentials[type] = factor;
        }
    }

    /**
     * Change the probability of a certain blocktype around given x,y position to grow by adding factor
     * @param type
     * @param x
     * @param y
     * @param factor
     * @param distance
     */
    public void changeProbabilityAround(int type, int x, int y, double factor, int distance){
        int[] top = getSpace(ABOVE, x, y, distance);
        int[] right = getSpace(RIGHT, x, y, distance);
        int[] bottom = getSpace(BELOW, x, y, distance);
        int[] left = getSpace(LEFT, x, y, distance);

        addProbability(type, top[0], top[1], factor);
        addProbability(type, right[0], right[1], factor);
        addProbability(type, bottom[0], bottom[1], factor);
        addProbability(type, left[0], left[1], factor);
        //TODO:case for each type of block saying if it should stay or go;
    }

    /**
     * Similar to changeProbabilityAround, but changes it to the given factor
     * @param type
     * @param x
     * @param y
     * @param factor
     * @param distance
     */
    public void changeProbabilityAroundTo(int type, int x, int y, double factor, int distance){
        int[] top = getSpace(ABOVE, x, y, distance);
        int[] right = getSpace(RIGHT, x, y, distance);
        int[] bottom = getSpace(BELOW, x, y, distance);
        int[] left = getSpace(LEFT, x, y, distance);

        //TODO: make only one actually change, and then the rest are super low.
        // May have expansion method to support fact
        changeProbabilityTo(type, top[0], top[1], factor);
        changeProbabilityTo(type, right[0], right[1], factor);
        changeProbabilityTo(type, bottom[0], bottom[1], factor);
        changeProbabilityTo(type, left[0], left[1], factor);
    }


    public void changeOneProbabilityAroundRandom(int type, int x, int y, double factor){
        double c = Sentinel.chance(1);
        Logger.CLTR(c);
        if(c <= .25){
            changeProbabilityTo(type, x, y + 1, factor);
        } else if(c > .25 && c <= .5){
            changeProbabilityTo(type, x, y - 1, factor);
        } else if(c > .5 && c <= .75){
            changeProbabilityTo(type, x + 1, y, factor);
        } else if(c > .5 && c < 1.0){
            changeProbabilityTo(type, x - 1, y, factor);
        } else {
            changeProbabilityTo(type, x, y, 1);
        }
    }

    /**
     * Changes probability of a given block randomly to stay the same or not
     * @param type
     * @param x
     * @param y
     */
    public void changeProbabilityRandom(int type, int x, int y){
        double c = Sentinel.chance(1);
        Logger.CLTR(c);
        if(c >= .5){
            changeProbabilityTo(type, x, y, 10);
        }
    }

    /**
     * Change the probability of a certain blocktype around given x,y position to grow with
     * decreasing gradient from center point
     * @param type
     * @param x
     * @param y
     * @param factor
     * @param distance
     */
    public void gradientChangeProbabilityAround(int type, int x, int y, int factor, int distance){

    }

    /**
     *
     * @param direction
     * @param x
     * @param y
     * @param distance
     * @return int[x, y] Space to edit
     */
    public int[] getSpace(int direction, int x, int y, int distance){
        switch(direction){
            case(HERE):
                return new int[]{x, y};
            case(ABOVE):
                if(isNotOutOfBounds(x, y + distance))
                    return new int[]{x, y + distance};
                else
                    return new int[]{x, y};
            case(RIGHT):
                if(isNotOutOfBounds(x + distance, y))
                    return new int[]{x + distance, y};
                else
                    return new int[]{x, y};
            case(BELOW):
                if(isNotOutOfBounds(x, y - distance))
                    return new int[]{x, y - distance};
                else
                    return new int[]{x, y};
            case(LEFT):
                if(isNotOutOfBounds(x - distance, y))
                    return new int[]{x - distance, y};
                else
                    return new int[]{x, y};
            default:
                return new int[]{x, y};
        }
    }

    public static int distance(int x, int y, int _x, int _y){
        return (int) Math.sqrt(Math.pow(Math.abs(x - _x), 2) + Math.pow(Math.abs(y - _y), 2));
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Checks whether a position is out of bounds of the grid
     * @param x X position of position in question
     * @param y Y position of position in question
     * @return Boolean value of whether the position is out of bounds
     */
    public boolean isNotOutOfBounds(int x, int y){
        return (y > this.rows || y < 0 || x > this.cols || x < 0) ? false : true;
    }


}
