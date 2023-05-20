package com.poproject.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.*;
import com.poproject.game.ProjectGame;

import static com.poproject.game.ProjectGame.*;

public class GameScreen extends AbstractScreen {
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;

    public GameScreen(final ProjectGame context){
        super(context);

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        ///circle
        bodyDef.position.set(4.5f, 15f);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_CIRCLE;
        fixtureDef.filter.maskBits = BIT_GROUND | BIT_BOX;
        CircleShape circleShape = new CircleShape();
        fixtureDef.shape = circleShape;
        circleShape.setRadius(0.5f);
        body.createFixture(fixtureDef);
        circleShape.dispose();

    /////BOX
        bodyDef.position.set(5.3f, 6f);
        bodyDef.gravityScale = 1f;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_BOX;
        fixtureDef.filter.maskBits = BIT_GROUND | BIT_CIRCLE;
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = polygonShape;
        body.createFixture(fixtureDef);
        polygonShape.dispose();

        //platform
        bodyDef.position.set(4.5f, 2f);
        bodyDef.gravityScale = 1f;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);

        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.42f;
        fixtureDef.filter.categoryBits = BIT_GROUND;
        fixtureDef.filter.maskBits = -1;
        polygonShape = new PolygonShape();
        polygonShape.setAsBox(4f, 0.5f);
        fixtureDef.shape = polygonShape;
        body.createFixture(fixtureDef);
        polygonShape.dispose();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply(true);
        box2DDebugRenderer.render(world, viewport.getCamera().combined);
//        world.step(delta, 6, 2);

        if(Gdx.input.isKeyPressed(Input.Keys.A))context.setScreen(ScreenType.LOADING);
    }
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
