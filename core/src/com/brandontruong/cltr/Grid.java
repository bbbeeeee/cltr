package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by btru on 5/6/15.
 */
public class Grid {
    public static final int ABOVE = 1;
    public static final int RIGHT = 2;
    public static final int BELOW = 3;
    public static final int LEFT  = 4;
    public static final int MAXROWS = 12;
    public static final int MAXCOLS = 18;

    private int rows;
    private int cols;
    private int xOffset;
    private int yOffset;

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
                int type = Integer.parseInt(block[0]);
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

    /**
     * Loop through and apply necessary changes
     */
    public void refresh(){
        for(int x = 0; x < getCols(); x++){
            for(int y = 0; y < getRows(); y++){

            }
        }
    }

    public void changeProbability(int type, int x, int y, double factor){
        if(isNotOutOfBounds(x, y) && y < 12 && x < 18)
            g[x][y].potentials[type] += factor;
    }

    public void changeProbabilityTo(int type, int x, int y, double factor){
        g[x][y].potentials[type] = factor;
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

        changeProbability(type, top[0], top[1], factor);
        changeProbability(type, right[0], right[1], factor);
        changeProbability(type, bottom[0], bottom[1], factor);
        changeProbability(type, left[0], left[1], factor);
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

        changeProbabilityTo(type, top[0], top[1], factor);
        changeProbabilityTo(type, right[0], right[1], factor);
        changeProbabilityTo(type, bottom[0], bottom[1], factor);
        changeProbabilityTo(type, left[0], left[1], factor);
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
        return (x > this.rows || x < 0 || y > this.cols || y < 0) ? false : true;
    }


}
