package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.*;

public class HealthBallSystem extends IteratingSystem {
    public HealthBallSystem(){
        super(Family.all(HealthBallComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float v) {
        Body healthBallBody = GameEngine.bodyComponentComponentMapper.get(entity).body;
        HealthBallComponent healthBallComponent = GameEngine.healthBallComponentMapper.get(entity);
        if(healthBallComponent.followedEntity == null){
            getEngine().removeEntity(entity);
            return;
        }
        BodyComponent follwedBodyComponent = GameEngine.bodyComponentComponentMapper.get(healthBallComponent.followedEntity);
        if(follwedBodyComponent == null){
            getEngine().removeEntity(entity);
            return;
        }

        Body followedBody = follwedBodyComponent.body;

//        healthBallBody.getPosition().set(new Vector2(0, 0.5f).add(followedBody.getPosition()));
        Vector2 dir = new Vector2(-0.3f, 0.6f).add(followedBody.getPosition()).sub(healthBallBody.getPosition());

        healthBallBody.setLinearVelocity(10*dir.x,10* dir.y);

        EnemyComponent ec = GameEngine.enemyComponentMapper.get(healthBallComponent.followedEntity);
        PlayerComponent pc = GameEngine.playerComponentMapper.get(healthBallComponent.followedEntity);

        Damageable dmc;
        if(pc != null)  dmc = pc;
        else dmc = ec;

        boolean notNull = dmc != null;
        if(notNull)healthBallComponent.maxHealth = dmc.maxHealth;
        if(notNull)healthBallComponent.health = dmc.healthPoints;

        float scale = (float)healthBallComponent.health / (float)healthBallComponent.maxHealth;
        GameEngine.bodyComponentComponentMapper.get(entity).scale = new Vector2(healthBallComponent.baseScale).scl(scale);
    }
}
