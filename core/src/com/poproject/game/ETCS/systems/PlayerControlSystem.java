package com.poproject.game.ETCS.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.poproject.game.ETCS.GameEngine;
import com.poproject.game.KeyboardController;
import com.poproject.game.ETCS.components.Box2dBodyComponent;
import com.poproject.game.ETCS.components.PlayerComponent;
import com.poproject.game.ETCS.components.StateComponent;
import com.poproject.game.ProjectGame;

public class PlayerControlSystem extends IteratingSystem {
//    ComponentMapper<StateComponent> sm;
//    KeyboardController controller;


    @SuppressWarnings("unchecked")
    public PlayerControlSystem() { //KeyboardController keyCon
        super(Family.all(PlayerComponent.class).get());
//        ProjectGame.instance.
//        controller = keyCon;
//        pm = ComponentMapper.getFor(PlayerComponent.class);
//        playerBody = ComponentMapper.getFor(Box2dBodyComponent.class);
//        sm = ComponentMapper.getFor(StateComponent.class);
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
//        Box2dBodyComponent b2body = playerBody.get(entity);
//        StateComponent state = sm.get(entity);

        final PlayerComponent playerComponent = GameEngine.playerComponentMapper.get(entity);
        final Box2dBodyComponent b2body = GameEngine.box2dBodyComponentComponentMapper.get(entity);

        float speedX = 0;
        float speedY = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.A)) speedX += -3f;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) speedX += 3f;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) speedY += 3f;
        if(Gdx.input.isKeyPressed(Input.Keys.S)) speedY += -3f;

        b2body.body.applyLinearImpulse((speedX - b2body.body.getLinearVelocity().x)* b2body.body.getMass(),
                (speedY - b2body.body.getLinearVelocity().y)* b2body.body.getMass(),
                b2body.body.getWorldCenter().x, b2body.body.getWorldCenter().y, true);

        float lerp = 2f;
        Vector2 playerPosition = b2body.body.getPosition();
        OrthographicCamera camera = ProjectGame.getInstance().getGameCamera();
        Vector3 position = camera.position;
        position.x += (playerPosition.x - position.x) * lerp * deltaTime;
        position.y += (playerPosition.y - position.y) * lerp * deltaTime;
        camera.update();
    }
}
