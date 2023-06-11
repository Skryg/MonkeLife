package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.poproject.game.WorldContactListener;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.Damageable;
import com.poproject.game.etcs.components.EnemyComponent;
import com.poproject.game.etcs.components.ProjectileComponent;
import com.poproject.game.etcs.entities.ProjectileEntity;

public class ProjectileSystem extends IteratingSystem {
    private final GameEngine gameEngine;
    public ProjectileSystem(GameEngine gameEngine){
        super(Family.all(ProjectileComponent.class).get());
        this.gameEngine = gameEngine;
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //usuwanie pocisku jak dojdzie do celu
        ProjectileComponent projectileComponent = GameEngine.projectileComponentMapper.get(entity);
        projectileComponent.flightTime += deltaTime;
        if(projectileComponent.flightTime > projectileComponent.maxFlightTime){
            System.out.println("Destination");
            projectileComponent.hit = true;
            gameEngine.removeEntity(entity);
            return;
        }

        if(projectileComponent.hit)return;

//        System.out.println(entity.toString());
        BodyComponent bodyComponent = GameEngine.bodyComponentComponentMapper.get(entity);//entity.getComponent(BodyComponent.class);
        //entity.getComponent(ProjectileComponent.class);
        Body projectileBody = bodyComponent.body;

        //kolizje z przeciwnikami
        //collidery bardzo sie psuja, wiec robimy po staremu, czyli musza byc odpowiednio blisko siebie (hitDistance)
        final float hitDistance = 0.9f;

        for(Entity enemy : gameEngine.getEntitiesFor(Family.all(EnemyComponent.class, BodyComponent.class).get())){
//            System.out.println(enemy.toString());
            if(GameEngine.bodyComponentComponentMapper.get(enemy).body.getPosition().sub(projectileBody.getPosition()).len() < hitDistance){
                Collision(projectileComponent, enemy.getComponent(EnemyComponent.class), entity);
                return;
            }
        }
//        for(Entity enemy : gameEngine.getEntitiesFor(Family.all(EnemyComponent.class, BodyComponent.class).get())){
//            Body enemyBody = GameEngine.bodyComponentComponentMapper.get(enemy).body;
//            for(Contact contact : WorldContactListener.contacts){
//                Body a = contact.getFixtureA().getBody();
//                Body b = contact.getFixtureB().getBody();
//                if((a == enemyBody && b == projectileBody) || (a == projectileBody && b == enemyBody)){
//                    Collision(projectileComponent, enemy.getComponent(EnemyComponent.class), entity);
//                    return;
//                }
//            }
//        }
    }

    void Collision(ProjectileComponent pc, Damageable dm, Entity projectileEntity){
//        System.out.println("REMOVED!");
        pc.hit = true;
        dm.getDamage(pc.dmg);
        gameEngine.removeEntity(projectileEntity);
    }
}
