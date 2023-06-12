package com.poproject.game.etcs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class TowerComponent implements Component, Pool.Poolable {
    public float lifeTime = 10f;
    public float rechargeTime = 1.5f;
    public float timer = 0.2f;
    public float range = 5f;

    @Override
    public void reset() {
        lifeTime = 10f;
        rechargeTime = 1.5f;
        timer = 0f;
    }

    public boolean dead(float deltaT) {
        lifeTime -= deltaT;
        return lifeTime <= 0;
    }

    public boolean ready(float deltaT) {
        timer -= deltaT;
        return ready();
    }

    public boolean ready() {
        return timer <= 0f;
    }

    public void fire() {
        timer = rechargeTime;
    }
}
