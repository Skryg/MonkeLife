package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.EnemyComponent;
import com.poproject.game.etcs.components.MoveableComponent;

public class EnemyMovementSystem extends IteratingSystem {

    public EnemyMovementSystem(){
        super(Family.all(MoveableComponent.class, EnemyComponent.class, BodyComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float v) {
        final MoveableComponent moveableComponent = GameEngine.moveableComponentMapper.get(entity);
        final BodyComponent bodyComponent = GameEngine.bodyComponentComponentMapper.get(entity);
        final EnemyComponent enemyComponent = GameEngine.enemyComponentMapper.get(entity);
        if(enemyComponent.focusBody == null){
            bodyComponent.applySpeed(0,0,moveableComponent.directionScale);
            return;
        }
        Vector2 focusPosition = enemyComponent.focusBody.getPosition();
        Vector2 enemyPosition = bodyComponent.body.getPosition();
        Vector2 destination = focusPosition.sub(enemyPosition);
        destination = destination.nor();
        bodyComponent.applySpeed(destination.x * moveableComponent.speed, destination.y * moveableComponent.speed, moveableComponent.directionScale);
    }
}
