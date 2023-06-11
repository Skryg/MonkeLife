package com.poproject.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

import java.util.LinkedList;
import java.util.List;

public class WorldContactListener implements ContactListener {
//    public static List<Contact> contacts = new LinkedList<>();
    @Override
    public void beginContact(Contact contact) {
//        contacts.add(contact);
        final Fixture fixA = contact.getFixtureA();
        final Fixture fixB = contact.getFixtureB();

//        System.out.println("CONTACT FIXA: " + fixA.getBody().getUserData() + " " + fixA.isSensor());
//        System.out.println("CONTACT FIXB: " + fixB.getBody().getUserData() + " " + fixB.isSensor());

        Gdx.app.debug("CONTACT", "FIXA: " + fixA.getBody().getUserData() + " " + fixA.isSensor());
        Gdx.app.debug("CONTACT", "FIXA: " + fixB.getBody().getUserData() + " " + fixB.isSensor());
    }

    @Override
    public void endContact(Contact contact) {
//        contacts.remove(contact);
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
