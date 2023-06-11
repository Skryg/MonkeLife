package com.poproject.game.etcs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class ProjectileComponent implements Component, Pool.Poolable {
    public float destX = 0;
    public float destY = 0;
    public final float speed = 3f;
    public final float mass = 1f;
    @Override
    public void reset() {
        destX = 0;
        destY = 0;
    }
}