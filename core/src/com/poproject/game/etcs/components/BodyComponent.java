package com.poproject.game.etcs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;

public class BodyComponent implements Component, Pool.Poolable {
    public Body body;
    public Vector2 scale = new Vector2(1, 1);
    public int positionZ = 0;

    @Override
    public void reset() {
        if (body == null) return;
        body.getWorld().destroyBody(body);
        scale = new Vector2(1, 1);
    }

    public void applySpeed(float speedX, float speedY, int scaleMulti) {
        if (speedX < 0) scale.x = Math.min(-scale.x, scale.x) * scaleMulti;
        if (speedX > 0) scale.x = Math.max(-scale.x, scale.x) * scaleMulti;
        body.applyLinearImpulse((speedX - body.getLinearVelocity().x) * body.getMass(), (speedY - body.getLinearVelocity().y) * body.getMass(), body.getWorldCenter().x, body.getWorldCenter().y, true);
    }
}

