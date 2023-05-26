package com.poproject.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.poproject.game.ProjectGame;

import static com.poproject.game.ProjectGame.*;

public class GameScreen extends AbstractScreen {
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;
    private final Vector2 playerStartPosition = new Vector2(4.5f, 3f);
    private final Body player;
    private final AssetManager assetManager;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final OrthographicCamera gameCamera;
    public GameScreen(final ProjectGame context){
        super(context);

        assetManager = context.getAssetManager();
        mapRenderer = new OrthogonalTiledMapRenderer(null, UNIT_SCALE, context.getSpriteBatch());
        gameCamera = context.getGameCamera();

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        //create Player
        bodyDef.position.set(playerStartPosition);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        player = world.createBody(bodyDef);
        player.setUserData("PLAYER");

        fixtureDef.density = 1;
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.69f;
        fixtureDef.filter.categoryBits = BIT_PLAYER;
        fixtureDef.filter.maskBits = BIT_GROUND;

        final PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = pShape;
        player.createFixture(fixtureDef);
        pShape.dispose();

        //create room
        
        bodyDef.position.set(0,0);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        final Body body = world.createBody(bodyDef);
        player.setUserData("GROUND");

        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.69f;
        fixtureDef.filter.categoryBits = BIT_GROUND;
        fixtureDef.filter.maskBits = -1;
        final ChainShape chainShape = new ChainShape();
        chainShape.createLoop(new float[]{1,1,1,15,8,15,8,1});
        fixtureDef.shape = chainShape;
        body.createFixture(fixtureDef);
        chainShape.dispose();
    }
    @Override
    public void show() {
        mapRenderer.setMap(assetManager.get("map/mapaProjekt.tmx", TiledMap.class));
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        float speedX = 0;
//        float speedY = 0;
//
//        if(Gdx.input.isKeyPressed(Input.Keys.A)) speedX += -3f;
//        if(Gdx.input.isKeyPressed(Input.Keys.D)) speedX += 3f;
//        if(Gdx.input.isKeyPressed(Input.Keys.W)) speedY += 3f;
//        if(Gdx.input.isKeyPressed(Input.Keys.S)) speedY += -3f;

//        player.applyLinearImpulse((speedX - player.getLinearVelocity().x)*player.getMass(),
//                (speedY - player.getLinearVelocity().y)*player.getMass(),
//                player.getWorldCenter().x, player.getWorldCenter().y, true);

        viewport.apply(true);
        mapRenderer.setView(gameCamera);
        mapRenderer.render();
        box2DDebugRenderer.render(world, viewport.getCamera().combined);

//        if(Gdx.input.isKeyPressed(Input.Keys.Q))context.setScreen(ScreenType.LOADING);
    }
    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    public void resize(final int width, final int height){
        context.getScreenViewport().update(width, height);
    }
    @Override
    public void dispose() {}
}
