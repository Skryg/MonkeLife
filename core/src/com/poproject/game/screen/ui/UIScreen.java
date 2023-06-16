package com.poproject.game.screen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.poproject.game.ProjectGame;
import com.poproject.game.State;
import com.poproject.game.screen.AbstractScreen;
import com.poproject.game.screen.ScreenType;

public abstract class UIScreen extends AbstractScreen {
    protected Stage stage;

    UIScreen(ProjectGame projectGame) {
        super(new ScreenViewport(), projectGame);
        this.projectGame = projectGame;
        stage = new Stage(getScreenViewport());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    protected TextButton createExitGameButton(Skin skin) {
        TextButton exit = new TextButton("Exit", skin);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        return exit;
    }

    protected TextButton createExitMenuButton(Skin skin) {
        TextButton exit = new TextButton("Exit Main Menu", skin);

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ProjectGame.state = State.RUNNING;
                projectGame.setScreen(ScreenType.MENU);
                projectGame.resetScreen(ScreenType.GAME);
                projectGame.resetScreen(ScreenType.LOADING);
            }
        });
        return exit;
    }

    protected TextButton createContinueButton(Skin skin) {
        TextButton continueButton = new TextButton("Continue", skin);

        continueButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ProjectGame.state = State.RUNNING;
                projectGame.setScreen(ScreenType.LOADING);
            }
        });
        return continueButton;
    }

    protected TextButton createPreferencesButton(Skin skin) {
        TextButton preferencesButton = new TextButton("Preferences", skin);
        preferencesButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                projectGame.setScreen(ScreenType.PREFERENCES);
            }
        });
        return preferencesButton;
    }

    protected TextButton createNewGameButton(Skin skin) {
        TextButton newGameButton = new TextButton("New game", skin);

        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                projectGame.setScreen(ScreenType.LOADING);
            }
        });
        return newGameButton;
    }

    protected Slider createMusicVolumeSlider(Skin skin) {
        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeMusicSlider.setValue(projectGame.getPreferences().getMusicVolume());
        volumeMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                projectGame.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
                projectGame.getPreferences().setMusicEnabled(true);
                return false;
            }
        });
        return volumeMusicSlider;
    }

    protected Slider createSoundVolumeSlider(Skin skin) {
        final Slider volumeSoundSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeSoundSlider.setValue(projectGame.getPreferences().getSoundVolume());
        volumeSoundSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                projectGame.getPreferences().setSoundVolume(volumeSoundSlider.getValue());
                projectGame.getPreferences().setSoundEffectsEnabled(true);
                return false;
            }
        });
        return volumeSoundSlider;
    }
}
