package com.poproject.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.poproject.game.Assets;
import com.poproject.game.ProjectGame;
import com.poproject.game.audio.AudioManager;

public class LoadingScreen extends AbstractScreen {
    private final AssetManager assetManager;
    private final AudioManager audioManager;
    private final Stage stage;
    private Label loadingText;
    public LoadingScreen(ProjectGame context){
        super(new ScreenViewport());
        stage = new Stage(getScreenViewport());
        assetManager = context.getAssetManager();
        audioManager = context.getAudioManager();
    }

    public void show() {
        Gdx.input.setInputProcessor(null);
        stage.clear();
        Skin skin = assetManager.get(Assets.skinUI);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        loadingText = new Label("Loading: ", skin);
        table.add(loadingText);

        Assets.load(assetManager, audioManager);
    }

    @Override
    public void render(final float delta) {
        loadingText.setText("Loading: "+ (int)(assetManager.getProgress()*100)+"%");
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if(assetManager.update()) ProjectGame.getInstance().setScreen(ScreenType.GAME);
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void reset() {}
}
