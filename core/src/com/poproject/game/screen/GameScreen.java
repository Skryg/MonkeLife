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
import com.badlogic.gdx.utils.Array;
import com.poproject.game.ETCS.GameEngine;
import com.poproject.game.ProjectGame;
import com.poproject.game.WorldContactListener;
import com.poproject.game.map.CollisionArea;
import com.poproject.game.map.Map;

import static com.poproject.game.ProjectGame.*;

public class GameScreen extends AbstractScreen {

//    private final Body player;
    private final AssetManager assetManager;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final OrthographicCamera gameCamera;
    private final GameEngine gameEngine;
    private final Box2DDebugRenderer box2DDebugRenderer; //moze lepiej w scenie???
    private final WorldContactListener worldContactListener;
    private final World world;
    private final Map map;
    private float accumulator;
    public GameScreen(final ProjectGame context){
        super(context);

        accumulator = 0f;
        assetManager = context.getAssetManager();
        mapRenderer = new OrthogonalTiledMapRenderer(null, UNIT_SCALE, context.getSpriteBatch());
        gameCamera = context.getGameCamera();
        box2DDebugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0f), false);
        worldContactListener = new WorldContactListener();
        world.setContactListener(worldContactListener);
        gameEngine = new GameEngine(this);
        gameEngine.spawnPlayer();
        map = new Map(assetManager.get("map/mapaProjekt.tmx", TiledMap.class));
        spawnCollisionAreas();
//        bodyDef = new BodyDef();
//        fixtureDef = new FixtureDef();
    }

    private void spawnCollisionAreas(){
        Array<CollisionArea> collisionAreas = map.getCollisionAreas();
        gameEngine.createCollisionAreas(collisionAreas);
    }
    @Override
    public void show() {
        mapRenderer.setMap(assetManager.get("map/mapaProjekt.tmx", TiledMap.class));
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameEngine.update(Gdx.graphics.getDeltaTime());
        accumulator += Math.min(0.25f, Gdx.graphics.getDeltaTime());
        while(accumulator >= FIXED_TIME_STEP){
            world.step(FIXED_TIME_STEP, 6, 2);
            accumulator -= FIXED_TIME_STEP;
        }

        viewport.apply(false);
        mapRenderer.setView(gameCamera);
        mapRenderer.render();
        box2DDebugRenderer.render(world, viewport.getCamera().combined);

//        if(Gdx.input.isKeyPressed(Input.Keys.Q))context.setScreen(ScreenType.LOADING);
    }
    public World getWorld(){return world;}
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
    public void dispose() {
        world.dispose();
        box2DDebugRenderer.dispose();
    }
}