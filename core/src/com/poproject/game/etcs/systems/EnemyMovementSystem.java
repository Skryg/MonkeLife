package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.EnemyComponent;
import com.poproject.game.etcs.components.MoveableComponent;

public class EnemyMovementSystem extends IteratingSystem {
    private int rot = 0;
    private float time = 0;
    public EnemyMovementSystem(){
        super(Family.all(MoveableComponent.class, EnemyComponent.class, BodyComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float v) {
        final MoveableComponent moveableComponent = GameEngine.moveableComponentMapper.get(entity);
        final BodyComponent b2body = GameEngine.bodyComponentComponentMapper.get(entity);
        time+=v;
        if(time >= 1f){
            time -= 1f;
            rot+=1;
            rot%=4;
        }

        float speedX = 0;
        float speedY = 0;
        if(rot == 0) speedX += -moveableComponent.speed;
        if(rot == 2) speedX += moveableComponent.speed;
        if(rot == 1) speedY += moveableComponent.speed;
        if(rot == 3) speedY += -moveableComponent.speed;

        b2body.applySpeed(speedX, speedY, moveableComponent.directionScale);
    }
}
