package com.brandontruong.cltr;

import java.util.ArrayList;

/**
 * Created by btru on 5/6/15.
 */
public class Grid {
    private int rows;
    private int cols;
    public BlockSpace[][] g;

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
    public boolean isOutOfBounds(int x, int y){
        return (x > this.rows || x < 0 || y > this.cols || y < 0) ? false : true;
    }


}
