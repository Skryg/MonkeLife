package com.poproject.game.screen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.poproject.game.Assets;
import com.poproject.game.ProjectGame;
import com.poproject.game.audio.AudioManager;
import com.poproject.game.screen.ScreenType;

public class LoadingScreen extends UIScreen {
    private final AssetManager assetManager;
    private final AudioManager audioManager;
    private Label loadingText;

    public LoadingScreen(ProjectGame projectGame) {
        super(projectGame);
        assetManager = projectGame.getAssetManager();
        audioManager = projectGame.getAudioManager();
    }

    public void show() {
        super.show();
        Gdx.input.setInputProcessor(null);
        Skin skin = assetManager.get(Assets.skinUI);
        loadingText = new Label("Loading: ", skin);
        table.add(loadingText);

        Assets.load(assetManager, audioManager);
    }

    @Override
    public void render(final float delta) {
        super.render(delta);
        loadingText.setText("Loading: " + (int) (assetManager.getProgress() * 100) + "%");

        if (assetManager.update()) ProjectGame.getInstance().setScreen(ScreenType.GAME);
    }
}
