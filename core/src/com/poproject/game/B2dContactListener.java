package com.poproject.game;

import com.badlogic.gdx.physics.box2d.ContactListener;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.poproject.game.etcs.components.CollisionComponent;
import com.poproject.game.etcs.components.EnemyComponent;
import com.poproject.game.etcs.components.PlayerComponent;

public class B2dContactListener implements ContactListener {

    public B2dContactListener() {
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa.getBody().getUserData() instanceof Entity && fb.getBody().getUserData() instanceof Entity) {
            Entity a = (Entity) fa.getBody().getUserData();
            Entity b = (Entity) fb.getBody().getUserData();

            PlayerComponent pc = a.getComponent(PlayerComponent.class);
            EnemyComponent ec = b.getComponent(EnemyComponent.class);
            if (pc == null || ec == null) {
                pc = b.getComponent(PlayerComponent.class);
                ec = a.getComponent(EnemyComponent.class);
                Entity temp = a;
                a = b;
                b = temp;
            }

            if (pc != null && ec != null) {
                entityCollision(a, b);
            }
        }
    }

    private void entityCollision(Entity player, Entity enemy) {
        CollisionComponent colb = enemy.getComponent(CollisionComponent.class);

        if (colb != null) {
            colb.collisionEntity = player;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        if (fa.getBody().getUserData() instanceof Entity && fb.getBody().getUserData() instanceof Entity) {
            Entity a = (Entity) fa.getBody().getUserData();
            Entity b = (Entity) fb.getBody().getUserData();

            PlayerComponent pc = a.getComponent(PlayerComponent.class);
            EnemyComponent ec = b.getComponent(EnemyComponent.class);
            if (pc == null || ec == null) {
                pc = b.getComponent(PlayerComponent.class);
                ec = a.getComponent(EnemyComponent.class);
                Entity temp = a;
                a = b;
                b = temp;
            }

            if (pc != null && ec != null) {
                entityEndCollision(a, b);
            }
        }
    }

    private void entityEndCollision(Entity player, Entity enemy) {
        CollisionComponent colb = enemy.getComponent(CollisionComponent.class);

        if (colb != null) {
            colb.collisionEntity = null;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

}
