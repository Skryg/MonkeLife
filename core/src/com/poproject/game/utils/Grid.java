package com.poproject.game.utils;

import java.util.Arrays;

public class Grid<T> {
    private int height;
    private int width;
    private T[][] gridArray;
    @SuppressWarnings("unchecked")
    public Grid(int width, int height){
        this.height = height;
        this.width = width;
        gridArray = (T[][])new Object[width][height];
    }
    public T getCell(int x, int y){
        if(x < 0 || x >= width || y < 0 || y >=height)return null;
        return gridArray[x][y];
    }
    public void setCell(int x, int y, T value){
        if(x < 0 || x >= width || y < 0 || y >=height)return;
        gridArray[x][y] = value;
    }
    public void clear(){
        for(int x = 0; x < width; x++) {
            Arrays.fill(gridArray[x], null);
        }
    }
    @SuppressWarnings("unchecked")
    public void resize(int width, int height){
        T[][] temp = (T[][])new Object[width][height];
        for(int x = 0; x < width && x < this.width; x++)
            for(int y = 0; y < height && y < this.height; y++)
                    temp[x][y] = gridArray[x][y];
        gridArray = temp;
    }
}
