package com.poproject.game.ETCS.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;

public class Box2dBodyComponent implements Component, Pool.Poolable {
    public Body body;
    @Override
    public void reset() {
        if(body == null)return;
        body.getWorld().destroyBody(body);
    }
}

