package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.poproject.game.ProjectGame;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.CameraComponent;
import com.poproject.game.etcs.components.PlayerComponent;

public class CamFollowPlayerSystem extends IteratingSystem {
    public CamFollowPlayerSystem(){
        super(Family.all(CameraComponent.class, PlayerComponent.class, BodyComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float lerp = 2f;

        final BodyComponent bodyComponent = GameEngine.bodyComponentComponentMapper.get(entity);
        Vector2 playerPosition = bodyComponent.body.getPosition();
        Camera camera = GameEngine.cameraComponentMapper.get(entity).camera;
        Vector3 position = camera.position;
        position.x += (playerPosition.x - position.x) * lerp * deltaTime;
        position.y += (playerPosition.y - position.y) * lerp * deltaTime;

        camera.update();
        ProjectGame.getInstance().getSpriteBatch().setProjectionMatrix(camera.combined);
    }
}
