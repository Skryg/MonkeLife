package com.poproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        final Fixture fixA = contact.getFixtureA();
        final Fixture fixB = contact.getFixtureB();

        Gdx.app.debug("CONTACT", "FIXA: " + fixA.getBody().getUserData() + " " + fixA.isSensor());
        Gdx.app.debug("CONTACT", "FIXA: " + fixB.getBody().getUserData() + " " + fixB.isSensor());
    }

    @Override
    public void endContact(Contact contact) {
        final Fixture fixA = contact.getFixtureA();
        final Fixture fixB = contact.getFixtureB();

        Gdx.app.debug("CONTACT", "FIXA: " + fixA.getBody().getUserData() + " " + fixA.isSensor());
        Gdx.app.debug("CONTACT", "FIXA: " + fixB.getBody().getUserData() + " " + fixB.isSensor());

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
