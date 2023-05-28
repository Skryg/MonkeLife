package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.PlayerComponent;

public class PlayerControlSystem extends IteratingSystem {

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final PlayerComponent playerComponent = GameEngine.playerComponentMapper.get(entity);
        final BodyComponent b2body = GameEngine.bodyComponentComponentMapper.get(entity);

        float speedX = 0;
        float speedY = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.A)) speedX += -3f;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) speedX += 3f;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) speedY += 3f;
        if(Gdx.input.isKeyPressed(Input.Keys.S)) speedY += -3f;

        if(speedX < 0) b2body.scale.x = Math.min(-b2body.scale.x, b2body.scale.x);
        if(speedX > 0) b2body.scale.x = Math.max(-b2body.scale.x, b2body.scale.x);
        b2body.body.applyLinearImpulse((speedX - b2body.body.getLinearVelocity().x)* b2body.body.getMass(),
                (speedY - b2body.body.getLinearVelocity().y)* b2body.body.getMass(),
                b2body.body.getWorldCenter().x, b2body.body.getWorldCenter().y, true);
    }
}
