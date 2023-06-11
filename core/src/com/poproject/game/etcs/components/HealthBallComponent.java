package com.poproject.game.etcs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class HealthBallComponent implements Pool.Poolable, Component {
    public Entity followedEntity;
    private float scaleNow = 1;
    public float health = 10;
    public float maxHealth = 10;
    public Vector2 baseScale = new Vector2(0.2f,0.2f);
    @Override
    public void reset() {
        scaleNow = 1f;
        health = 10;
        maxHealth = 10;
        baseScale = new Vector2(0.2f,0.2f);
    }
}
