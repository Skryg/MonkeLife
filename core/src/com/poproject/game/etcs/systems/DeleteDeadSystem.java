package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.EnemyComponent;

public class DeleteDeadSystem extends IteratingSystem {
    private final GameEngine engine;

    public DeleteDeadSystem(GameEngine engine) {
        super(Family.all(EnemyComponent.class).get());
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        final EnemyComponent enemyComponent = GameEngine.enemyComponentMapper.get(entity);
        if (!enemyComponent.isAlive) {
            engine.removeEntity(entity);
            EnemyGenerateSystem.enemyDecrementCount();
        }
    }
}
