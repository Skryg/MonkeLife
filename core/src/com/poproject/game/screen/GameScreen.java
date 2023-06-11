package com.poproject.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.poproject.game.Assets;
import com.poproject.game.State;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.ProjectGame;
import com.poproject.game.WorldContactListener;
import com.poproject.game.map.CollisionArea;
import com.poproject.game.map.Map;
import com.poproject.game.utils.BodyFactory;
import com.sun.org.apache.xml.internal.dtm.ref.CoroutineManager;

import static com.poproject.game.ProjectGame.*;

public class GameScreen extends AbstractScreen {

//    private final Body player;
    private final AssetManager assetManager;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final GameEngine gameEngine;
    private final Box2DDebugRenderer box2DDebugRenderer;
    private final WorldContactListener worldContactListener;
    private final World world;
    private final Map map;
    private float accumulator;

    public GameScreen(final ProjectGame context){
        super(new FitViewport(16, 9, new OrthographicCamera()));
        ProjectGame.getInstance().setGameCamera((OrthographicCamera) getScreenViewport().getCamera());
        accumulator = 0f;
        assetManager = context.getAssetManager();
        mapRenderer = new OrthogonalTiledMapRenderer(null, UNIT_SCALE, context.getSpriteBatch());

        box2DDebugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0f), false);
        BodyFactory.setWorld(world);

        worldContactListener = new WorldContactListener();
        world.setContactListener(worldContactListener);

        gameEngine = new GameEngine(this);
        gameEngine.spawnPlayer();
        gameEngine.spawnEnemy(new Vector2(7.5f, 3f));
        map = new Map(assetManager.get(Assets.map, TiledMap.class));
        spawnCollisionAreas();
    }

    private void spawnCollisionAreas(){
        Array<CollisionArea> collisionAreas = map.getCollisionAreas();
        BodyFactory.getInstance().createCollisionAreas(collisionAreas);
    }

    @Override
    public void show() {
        mapRenderer.setMap(assetManager.get(Assets.map, TiledMap.class));
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch (state){
            case RUNNING:
                if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) pause();

                gameEngine.update(Gdx.graphics.getDeltaTime());

                accumulator += Math.min(0.25f, Gdx.graphics.getDeltaTime());
                while(accumulator >= FIXED_TIME_STEP){
                    world.step(FIXED_TIME_STEP, 6, 2);
                    accumulator -= FIXED_TIME_STEP;
                }
                box2DDebugRenderer.render(world, getCamera().combined);
                break;
            case PAUSE:
                ProjectGame.getInstance().setScreen(ScreenType.PAUSE);
                break;
        }
    }

    public Box2DDebugRenderer getDebugRenderer(){return box2DDebugRenderer;}
    public OrthogonalTiledMapRenderer getMapRenderer(){return mapRenderer;}
    public World getWorld(){return world;}
    @Override
    public void pause() {
        state = State.PAUSE;
    }
    @Override
    public void resume() {state = State.RUNNING;}
    @Override
    public void hide() {}

    public void resize(final int width, final int height){
        getScreenViewport().update(width, height);
    }
    @Override
    public void dispose() {
        world.dispose();
        box2DDebugRenderer.dispose();
    }

}