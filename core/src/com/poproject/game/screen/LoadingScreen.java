package com.poproject.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.poproject.game.ProjectGame;

public class LoadingScreen extends AbstractScreen {
    private final AssetManager assetManager;
    private final Stage stage;
    private Label loadingText;
    public LoadingScreen(ProjectGame context){
        super(context);
        stage = new Stage(new ScreenViewport());

        assetManager = context.getAssetManager();
        assetManager.load("map/mapaProjekt.tmx", TiledMap.class);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);
        stage.clear();
        Skin skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        stage.clear();
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        loadingText = new Label("Loading: ", skin);
        table.add(loadingText);
    }

    @Override
    public void render(final float delta) {
        loadingText.setText("Loading: "+ (int)(assetManager.getProgress()*100)+"%");
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if(assetManager.update())context.setScreen(ScreenType.GAME);
        //if(Gdx.input.isKeyPressed(Input.Keys.Q))context.setScreen(ScreenType.GAME);
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
