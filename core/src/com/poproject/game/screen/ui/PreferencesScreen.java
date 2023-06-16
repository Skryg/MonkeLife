package com.poproject.game.screen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.poproject.game.Assets;
import com.poproject.game.ProjectGame;
import com.poproject.game.State;
import com.poproject.game.screen.ScreenType;

public class PreferencesScreen extends UIScreen {
    public PreferencesScreen(ProjectGame projectGame) {
        super(projectGame);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        Skin skin = projectGame.getAssetManager().get(Assets.skinUI);

        final Slider volumeMusicSlider = createMusicVolumeSlider(skin);
        final Slider volumeSoundSlider = createSoundVolumeSlider(skin);

        // return to main screen button
        final TextButton backButton = createBackButton(skin);

        // our new fields
        Label titleLabel = new Label("Preferences", skin);
        Label volumeMusicLabel = new Label("Music Volume", skin);
        Label volumeSoundLabel = new Label("Sound Volume", skin);
        table.add(titleLabel).colspan(2);
        table.row();
        table.add(volumeSoundLabel);
        table.add(volumeSoundSlider);
        table.row();
        table.add(volumeMusicLabel);
        table.add(volumeMusicSlider);
        table.row();
        table.add(backButton).colspan(2);
    }

    TextButton createBackButton(Skin skin) {
        final TextButton backButton = new TextButton("Back", skin);
        switch (ProjectGame.state) {
            case RUNNING:
                backButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        projectGame.setScreen(ScreenType.MENU);
                    }
                });
                break;
            case PAUSE:
                backButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        projectGame.setScreen(ScreenType.PAUSE);
                    }
                });
                break;
        }
        return backButton;
    }
}
