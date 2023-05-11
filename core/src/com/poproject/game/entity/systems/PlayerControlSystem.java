package com.poproject.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

import com.poproject.game.entity.components.Box2dBodyComponent;
import com.poproject.game.KeyboardController;
import com.poproject.game.entity.components.PlayerComponent;
import com.poproject.game.entity.components.StateComponent;


public class PlayerControlSystem extends IteratingSystem{

    ComponentMapper<PlayerComponent> pm;
    ComponentMapper<Box2dBodyComponent> bodm;
    ComponentMapper<StateComponent> sm;
    KeyboardController controller;


    @SuppressWarnings("unchecked")
    public PlayerControlSystem(KeyboardController keyCon) {
        super(Family.all(PlayerComponent.class).get());
        controller = keyCon;
        pm = ComponentMapper.getFor(PlayerComponent.class);
        bodm = ComponentMapper.getFor(Box2dBodyComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Box2dBodyComponent b2body = bodm.get(entity);
        StateComponent state = sm.get(entity);

//        if(b2body.body.getLinearVelocity().y > 0){
//            state.set(FALLING);
//        }

//        if(b2body.body.getLinearVelocity().y == 0){
//            if(state.get() == StateComponent.STATE_FALLING){
//                state.set(StateComponent.STATE_NORMAL);
//            }
//            if(b2body.body.getLinearVelocity().x != 0){
//                state.set(StateComponent.STATE_MOVING);
//            }
//        }


        if(controller.left){
            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, -5f, 0.2f),b2body.body.getLinearVelocity().y);
        }
        if(controller.right){
            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, 5f, 0.2f),b2body.body.getLinearVelocity().y);
        }
        if(controller.up){
            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().y, 5f, 0.2f),b2body.body.getLinearVelocity().y);
        }
        if(controller.down){
            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().y, 5f, 0.2f),b2body.body.getLinearVelocity().y);

        }

        if(!controller.left && !controller.right && !controller.up && !controller.down){
            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, 0, 0.1f),b2body.body.getLinearVelocity().y);
        }

        if(controller.up &&
                (state.get() == StateComponent.State.NORMAL || state.get() == StateComponent.State.MOVING)){
            //b2body.body.applyForceToCenter(0, 3000,true);
            b2body.body.applyLinearImpulse(0, 75f, b2body.body.getWorldCenter().x,b2body.body.getWorldCenter().y, true);
            //state.set(StateComponent.STATE_JUMPING);
        }
    }
}