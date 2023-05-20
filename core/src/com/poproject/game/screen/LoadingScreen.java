package com.poproject.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.poproject.game.ProjectGame;

public class LoadingScreen extends AbstractScreen {
    private final AssetManager assetManager;
    public LoadingScreen(ProjectGame context){
        super(context);

        assetManager = context.getAssetManager();
        assetManager.load("map/map1.tmx", TiledMap.class);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(assetManager.update())context.setScreen(ScreenType.GAME);
        //if(Gdx.input.isKeyPressed(Input.Keys.Q))context.setScreen(ScreenType.GAME);
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
