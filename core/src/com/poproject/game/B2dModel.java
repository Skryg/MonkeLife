package com.poproject.game;

import com.badlogic.gdx.math.Octree;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class B2dModel {
    public World world;
    private Body bodyd;
    private Body bodyk;
    private Body bodys;


    public B2dModel(){
        world = new World(new Vector2(0,-10f),true);
        createFloor();
        createObject();
        createMovingObject();

        BodyFactory bodyFactory = BodyFactory.getInstance(world);
        bodyFactory.makeCirclePolyBody(1,1,2,BodyFactory.RUBBER, BodyDef.BodyType.DynamicBody, false);
        bodyFactory.makeCirclePolyBody(4,1,2,BodyFactory.STEEL, BodyDef.BodyType.DynamicBody, false);
        bodyFactory.makeCirclePolyBody(-4,1,2,BodyFactory.STONE, BodyDef.BodyType.DynamicBody, false);
    }

    public void logicStep(float delta){
        world.step(delta,3,3);
    }

    private void createObject(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);

        bodyd = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        bodyd.createFixture(fixtureDef);

        shape.dispose();

    }

    private void createFloor(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);
        bodys = world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(50,1);
        bodys.createFixture(polygonShape, 0.0f);
        polygonShape.dispose();

    }

    private void createMovingObject(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);

        bodyk = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        bodyk.createFixture(fixtureDef);
        shape.dispose();

        bodyk.setLinearVelocity(0,0.75f);

    }
}
