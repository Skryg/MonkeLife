package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.MoveableComponent;
import com.poproject.game.etcs.components.PlayerComponent;

public class PlayerControlSystem extends IteratingSystem {

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class, MoveableComponent.class, BodyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final PlayerComponent playerComponent = GameEngine.playerComponentMapper.get(entity);
        final BodyComponent b2body = GameEngine.bodyComponentComponentMapper.get(entity);
        final MoveableComponent moveableComponent = GameEngine.moveableComponentMapper.get(entity);

        float speedX = 0;
        float speedY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) speedX += -moveableComponent.speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) speedX += moveableComponent.speed;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) speedY += moveableComponent.speed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) speedY += -moveableComponent.speed;

        if (speedX != 0 && speedY != 0) {
            float sqrt = (float) Math.sqrt(Math.abs(moveableComponent.speed));

            speedX *= sqrt / Math.abs(speedX);
            speedY *= sqrt / Math.abs(speedY);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            speedX *= 2;
            speedY *= 2;
        }

        b2body.applySpeed(speedX, speedY, 1);
    }
}
