package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.CollisionComponent;
import com.poproject.game.etcs.components.EnemyComponent;
import com.poproject.game.etcs.components.PlayerComponent;

public class CollisionSystem extends IteratingSystem {
    GameEngine engine;

    @SuppressWarnings("unchecked")
    public CollisionSystem(GameEngine engine) {
        super(Family.all(CollisionComponent.class, EnemyComponent.class).get());
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // get player collision component
        CollisionComponent collisionComponent = GameEngine.collisionComponentMapper.get(entity);
        EnemyComponent enemyComponent = GameEngine.enemyComponentMapper.get(entity);
        Entity collidedEntity = collisionComponent.collisionEntity;
        if (collidedEntity != null) {
            PlayerComponent playerComponent = collidedEntity.getComponent(PlayerComponent.class);
            playerComponent.getDamage(enemyComponent.strength * deltaTime);

        }

    }

}
