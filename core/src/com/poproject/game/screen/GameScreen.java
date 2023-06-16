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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.poproject.game.Assets;
import com.poproject.game.State;
import com.poproject.game.audio.AudioType;
import com.poproject.game.*;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.map.CollisionArea;
import com.poproject.game.map.Map;
import com.poproject.game.screen.AbstractScreen;
import com.poproject.game.screen.ScreenType;
import com.poproject.game.utils.BodyFactory;

import static com.poproject.game.ProjectGame.*;

public class GameScreen extends AbstractScreen {
    private final AssetManager assetManager;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private GameEngine gameEngine;
    private final World world;
    private final Map map;
    private float accumulator;

    public GameScreen(final ProjectGame projectGame) {
        super(new FitViewport(16, 9, new OrthographicCamera()),projectGame);

        projectGame.setGameCamera((OrthographicCamera) getScreenViewport().getCamera());
        accumulator = 0f;
        assetManager = projectGame.getAssetManager();
        mapRenderer = new OrthogonalTiledMapRenderer(null, UNIT_SCALE, projectGame.getSpriteBatch());

        world = new World(new Vector2(0, 0f), false);
        BodyFactory.setWorld(world);

        world.setContactListener(new B2dContactListener());

        gameEngine = new GameEngine(this);
        map = new Map(assetManager.get(Assets.map, TiledMap.class));
        spawnCollisionAreas();
    }

    private void spawnCollisionAreas() {
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch (state) {
            case RUNNING:
                if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) pause();

                gameEngine.update(Gdx.graphics.getDeltaTime());

                accumulator += Math.min(0.25f, Gdx.graphics.getDeltaTime());
                while (accumulator >= FIXED_TIME_STEP) {
                    world.step(FIXED_TIME_STEP, 6, 2);
                    accumulator -= FIXED_TIME_STEP;
                }
                break;
            case PAUSE:
                projectGame.setScreen(ScreenType.PAUSE);
                break;
        }
    }

    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

    @Override
    public void pause() {
        super.pause();
        ProjectGame.getInstance().getAudioManager().playAudio(AudioType.NONE);
    }

    @Override
    public void resume() {
        super.resume();
        ProjectGame.getInstance().getAudioManager().playAudio(AudioType.MAINTHEME);
    }

    @Override
    public void hide() {
        ProjectGame.getInstance().getAudioManager().playAudio(AudioType.NONE);
    }

    public void resize(final int width, final int height) {
        getScreenViewport().update(width, height);
    }

    @Override
    public void dispose() {
        world.dispose();
        mapRenderer.dispose();
    }

    @Override
    public void reset() {
        gameEngine.dispose();
        gameEngine = new GameEngine(this);
    }
}