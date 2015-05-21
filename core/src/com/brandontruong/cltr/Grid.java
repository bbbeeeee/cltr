package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by btru on 5/6/15.
 */
public class Grid {
    private int rows;
    private int cols;
    public BlockSpace[][] g;
    public Toolbelt toolbelt;

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
                g = new BlockSpace[cols][rows];
                for (int k = 0; k < rows; k++) {
                    for (int j = 0; j < cols; j++) {
                        g[k][j] = new BlockSpace(k, j);
                        g[k][j].add(BlockSpace.newBlock("Empty", k, j));
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
                int x = Integer.parseInt(block[1]);
                int y = Integer.parseInt(block[2]);
                String type = block[0];

                g[x][y].add(BlockSpace.newBlock(type, x, y));
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
     * Basic grid constructor
     * @param rows Number of rows in grid
     * @param cols Number of columns in grid
     */
    public Grid(int rows, int cols){
        setRows(rows);
        setCols(cols);
        g = new BlockSpace[cols][rows];

        // Let each block space know what its coordinates are.
        for(int _x = 0; _x < rows; _x++){
            for(int _y = 0; _y < cols; _y++){
                g[_x][_y].x = _x;
                g[_x][_y].y = _y;

            }
        }
    }

    /**
     * Grid constructor with starting layout
     * @param rows Number of rows in grid
     * @param cols Number of columns in grid
     * @param start array of the layout of the starting grid.
     */
    public Grid(int rows, int cols, BlockSpace[][] start){
        g = start; // need to validate that it has same rows and columns
        setRows(rows);
        setCols(cols);
    }

    /**
     * Initialize the grid based on array of starting blockspaces.
     * @param rows
     * @param cols
     * @param start
     */
    public Grid(int rows, int cols, BlockSpace[] start) {
        setRows(rows);
        setCols(cols);
        g = new BlockSpace[rows][cols];

        // Fill in the rest as empty
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                g[i][j] = new BlockSpace(i, j);
                g[i][j].add(BlockSpace.newBlock("Empty", i, j));
            }
        }

        // Add in the starting blocks
        for (int i = 0; i < start.length; i++) {
            BlockSpace space = start[i];

            if (isNotOutOfBounds(space.x, space.y)) {
                g[space.x][space.y] = space;
            }
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
