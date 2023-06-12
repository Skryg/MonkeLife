package com.poproject.game.map;

import com.poproject.game.ProjectGame;

public class CollisionArea {
    private final float startX;
    private final float startY;
    private final float[] vertices;

    public CollisionArea(final float startX, final float startY, final float[] vertices) {
        this.startX = startX * ProjectGame.UNIT_SCALE;
        this.startY = startY * ProjectGame.UNIT_SCALE;
        this.vertices = vertices;
        for (int i = 0; i < vertices.length; i++) {
            this.vertices[i] *= ProjectGame.UNIT_SCALE;
        }
    }

    public float[] getVertices() {
        return vertices;
    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }
}
