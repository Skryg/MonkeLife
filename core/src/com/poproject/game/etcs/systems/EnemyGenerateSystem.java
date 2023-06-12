package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.Vector2;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.utils.RandomVectorGenerator;

public class EnemyGenerateSystem extends IntervalSystem {
    GameEngine engine;
    Vector2 position;

    int maxEnemies = 10;

    public static void enemyDecrementCount() {
        --enemyCount;
    }

    static int enemyCount = 0;


    public EnemyGenerateSystem(float interval, GameEngine engine, int maxEnemies) {
        super(interval);
        this.engine = engine;
        this.maxEnemies = maxEnemies;
        enemyCount = 0;
    }

    @Override
    protected void updateInterval() {
        if (position == null) {
            Entity player = engine.getPlayer();
            if (player == null) return;
            position = player.getComponent(BodyComponent.class).body.getPosition();
        }
        System.out.println("sth");
        if (enemyCount <= maxEnemies) {
            System.out.println("sth2");

            ++enemyCount;
            engine.spawnEnemy(RandomVectorGenerator.getNotClose(position, 3));
        }
    }
}
