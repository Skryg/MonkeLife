package com.poproject.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.poproject.game.map.CollisionArea;

import static com.poproject.game.ProjectGame.BIT_GROUND;
import static com.poproject.game.ProjectGame.BIT_PLAYER;

public class BodyFactory {

    public static final int STEEL = 0;
    public static final int WOOD = 1;
    public static final int RUBBER = 2;
    public static final int STONE = 3;
    public World world;
    private static BodyFactory instance;
    private static BodyDef bodyDef;
    private static FixtureDef fixtureDef;

    private BodyFactory(World world){
        this();
        this.world = world;
    }

    private BodyFactory(){
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
    }

    public static void setWorld(World world){
        getInstance().world = world;
    }

    public static BodyFactory getInstance(){
        if(instance == null) instance = new BodyFactory();
        return instance;
    }

//    public static BodyFactory getInstance(World world){
//        if(instance == null) instance = new BodyFactory(world);
//        return instance;
//    }
//
//    public static FixtureDef makeFixture(int material, Shape shape){
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//
//        switch(material){
//            case 0:
//                fixtureDef.density = 1f;
//                fixtureDef.friction = 0.3f;
//                fixtureDef.restitution = 0.1f;
//                break;
//            case 1:
//                fixtureDef.density = 0.5f;
//                fixtureDef.friction = 0.7f;
//                fixtureDef.restitution = 0.3f;
//                break;
//            case 2:
//                fixtureDef.density = 1f;
//                fixtureDef.friction = 0f;
//                fixtureDef.restitution = 1f;
//                break;
//            case 3:
//                fixtureDef.density = 1f;
//                fixtureDef.friction = 0.9f;
//                fixtureDef.restitution = 0.01f;
//                break;
//            default:
//                fixtureDef.density = 7f;
//                fixtureDef.friction = 0.5f;
//                fixtureDef.restitution = 0.3f;
//
//        }
//        return fixtureDef;
//    }
//
//    public Body makeCirclePolyBody(float posx, float posy, float radius, int material, BodyDef.BodyType bodyType, boolean fixedRotation){
//        BodyDef boxBodyDef = new BodyDef();
//        boxBodyDef.type = bodyType;
//        boxBodyDef.position.x = posx;
//        boxBodyDef.position.y = posy;
//        boxBodyDef.fixedRotation = fixedRotation;
//
//        Body boxBody = world.createBody(boxBodyDef);
//        CircleShape circleShape = new CircleShape();
//        circleShape.setRadius(radius/2);
//        boxBody.createFixture(makeFixture(material, circleShape));
//        circleShape.dispose();
//        return boxBody;
//    }

    public Body makePlayerBody(Vector2 playerStartPosition){
        resetBodyAndFixtureDef();
        bodyDef.position.set(playerStartPosition);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        Body playerBody = world.createBody(bodyDef);
        playerBody.setUserData("PLAYER");

        fixtureDef.density = 1;
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.69f;
        fixtureDef.filter.categoryBits = BIT_PLAYER;
        fixtureDef.filter.maskBits = BIT_GROUND;

        final PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = pShape;
        playerBody.createFixture(fixtureDef);
        pShape.dispose();
        return playerBody;
    }

    private void createCollisionArea(CollisionArea ca){
        resetBodyAndFixtureDef();
        bodyDef.position.set(ca.getStartX(), ca.getStartY());
        bodyDef.gravityScale = 0;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        final Body body = world.createBody(bodyDef);

        fixtureDef.filter.categoryBits = BIT_GROUND;
        fixtureDef.filter.maskBits = -1;
        final ChainShape chainShape = new ChainShape();
        chainShape.createChain(ca.getVertices());
        fixtureDef.shape = chainShape;
        body.createFixture(fixtureDef);

        chainShape.dispose();
    }
    public void createCollisionAreas(Array<CollisionArea> collisionAreas){
        for(CollisionArea ca : collisionAreas)createCollisionArea(ca);
    }
    private void resetBodyAndFixtureDef(){
        bodyDef.gravityScale = 1;
        bodyDef.position.set(0,0);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.fixedRotation = true;

        fixtureDef.density = 1;
        fixtureDef.filter.maskBits = 0;
        fixtureDef.filter.categoryBits = 0;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.isSensor = false;
        fixtureDef.shape = null;
    }
}
