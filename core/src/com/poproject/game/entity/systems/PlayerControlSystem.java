package com.poproject.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.poproject.game.KeyboardController;
import com.poproject.game.entity.components.Box2dBodyComponent;
import com.poproject.game.entity.components.PlayerComponent;
import com.poproject.game.entity.components.StateComponent;

public class PlayerControlSystem extends IteratingSystem {

    ComponentMapper<PlayerComponent> pm;
    ComponentMapper<Box2dBodyComponent> playerBody;
    ComponentMapper<StateComponent> sm;
    KeyboardController controller;


    @SuppressWarnings("unchecked")
    public PlayerControlSystem(KeyboardController keyCon) {
        super(Family.all(PlayerComponent.class).get());
        controller = keyCon;
        pm = ComponentMapper.getFor(PlayerComponent.class);
        playerBody = ComponentMapper.getFor(Box2dBodyComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Box2dBodyComponent b2body = playerBody.get(entity);
        StateComponent state = sm.get(entity);

        float speedX = 0;
        float speedY = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.A)) speedX += -3f;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) speedX += 3f;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) speedY += 3f;
        if(Gdx.input.isKeyPressed(Input.Keys.S)) speedY += -3f;

        b2body.body.applyLinearImpulse((speedX - b2body.body.getLinearVelocity().x)* b2body.body.getMass(),
                (speedY - b2body.body.getLinearVelocity().y)* b2body.body.getMass(),
                b2body.body.getWorldCenter().x, b2body.body.getWorldCenter().y, true);


//        if(controller.left){
//            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, -5f, 0.2f),b2body.body.getLinearVelocity().y);
//        }
//        if(controller.right){
//            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, 5f, 0.2f),b2body.body.getLinearVelocity().y);
//        }
//        if(controller.up){
//            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().y, 5f, 0.2f),b2body.body.getLinearVelocity().y);
//        }
//        if(controller.down){
//            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().y, 5f, 0.2f),b2body.body.getLinearVelocity().y);
//        }
//
//        if(!controller.left && !controller.right && !controller.up && !controller.down){
//            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, 0, 0.1f),b2body.body.getLinearVelocity().y);
//        }
//
//        if(controller.up &&
//                (state.get() == StateComponent.State.NORMAL || state.get() == StateComponent.State.MOVING)){
//            b2body.body.applyLinearImpulse(0, 75f, b2body.body.getWorldCenter().x,b2body.body.getWorldCenter().y, true);
//        }
    }
}
