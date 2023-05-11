package com.poproject.game.utils;

import com.badlogic.gdx.math.Vector2;

public class WorldGrid <T>{
    private final float cellSize;
    private final Vector2 origin;
    private final Grid<T> grid;
    public WorldGrid(int width, int height, float cellSize, Vector2 origin){
        this.cellSize = cellSize;
        this.origin = origin;
        grid = new Grid<T>(width, height);
    }
    public T getCell(Vector2 pos){
        int x = Math.round((pos.x - origin.x)/cellSize);
        int y = Math.round((pos.y - origin.y)/cellSize);
        return grid.getCell(x,y);
    }
    public void setCell(Vector2 pos, T val){
        int x = Math.round((pos.x - origin.x)/cellSize);
        int y = Math.round((pos.y - origin.y)/cellSize);
        grid.setCell(x,y,val);
    }

    public void resize(int width, int height){
        grid.resize(width, height);
    }
}
