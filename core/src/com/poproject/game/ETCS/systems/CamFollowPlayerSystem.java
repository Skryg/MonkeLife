package com.poproject.game.ETCS.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.poproject.game.ETCS.GameEngine;
import com.poproject.game.ETCS.components.Box2dBodyComponent;
import com.poproject.game.ETCS.components.CameraComponent;
import com.poproject.game.ETCS.components.PlayerComponent;
import com.poproject.game.ProjectGame;

public class CamFollowPlayerSystem extends IteratingSystem {
    public CamFollowPlayerSystem(){
        super(Family.all(CameraComponent.class, PlayerComponent.class, Box2dBodyComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float lerp = 2f;

        final Box2dBodyComponent b2body = GameEngine.box2dBodyComponentComponentMapper.get(entity);
        Vector2 playerPosition = b2body.body.getPosition();
        Camera camera = GameEngine.cameraComponentMapper.get(entity).camera;
        Vector3 position = camera.position;
        position.x += (playerPosition.x - position.x) * lerp * deltaTime;
        position.y += (playerPosition.y - position.y) * lerp * deltaTime;
        camera.update();
    }
}
