package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.utils.RandomVectorGenerator;

import java.util.Random;

public class EnemyGenerateSystem extends IntervalSystem {
    GameEngine engine;
    Vector2 position;

    public EnemyGenerateSystem(float interval, GameEngine engine){
        super(interval);
        this.engine = engine;

    }
    @Override
    protected void updateInterval() {
        if(position == null){
            Entity player = engine.getPlayer();
            if(player == null) return;
            position = player.getComponent(BodyComponent.class).body.getPosition();
        }
        engine.spawnEnemy(RandomVectorGenerator.getNotClose(position, 3));

    }
}
