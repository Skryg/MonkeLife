package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.EnemyComponent;
import com.poproject.game.etcs.components.TowerComponent;

public class TowerShootingSystem extends IteratingSystem {
    GameEngine gameEngine;

    public TowerShootingSystem(GameEngine gameEngine) {
        super(Family.all(TowerComponent.class).get());
        this.gameEngine = gameEngine;
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        TowerComponent tc = GameEngine.towerComponentMapper.get(entity);
        if (tc.dead(v)) {
            gameEngine.removeEntity(entity);
            return;
        }
        if (!tc.ready(v)) return;

        tryShooting(entity, tc);
    }

    private void tryShooting(Entity tower, TowerComponent tc) {
        Vector2 towerPos = GameEngine.bodyComponentComponentMapper.get(tower).body.getPosition();
        Vector2 enemyPos;

        for (Entity enemy : gameEngine.getEntitiesFor(Family.all(EnemyComponent.class, BodyComponent.class).get())) {
            enemyPos = GameEngine.bodyComponentComponentMapper.get(enemy).body.getPosition();
            if (Vector2.dst(towerPos.x, towerPos.y, enemyPos.x, enemyPos.y) > tc.range) continue;

            gameEngine.entityBuilder.createProjectileEntity(enemyPos.x, enemyPos.y, towerPos);
            tc.fire();
            return;
        }
    }
}
