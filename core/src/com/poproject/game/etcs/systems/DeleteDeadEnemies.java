package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.EnemyComponent;
import com.poproject.game.etcs.components.MoveableComponent;

public class DeleteDeadEnemies extends IteratingSystem {
    private final GameEngine engine;

    public DeleteDeadEnemies(GameEngine engine){
        super(Family.all(EnemyComponent.class).get());
        this.engine = engine;
    }
    @Override
    protected void processEntity(Entity entity, float v) {
        final EnemyComponent enemyComponent = GameEngine.enemyComponentMapper.get(entity);
        if(!enemyComponent.isAlive) {
            engine.removeEntity(entity);
            EnemyGenerateSystem.enemyDecrementCount();
        }


    }
}
