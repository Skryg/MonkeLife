package com.poproject.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.poproject.game.ProjectGame;
import com.poproject.game.State;

public abstract class AbstractScreen implements Screen {
    private final Viewport viewport;
    protected ProjectGame projectGame;

    protected AbstractScreen(Viewport viewport, ProjectGame projectGame) {
        this.projectGame = projectGame;
        this.viewport = viewport;
    }

    public Camera getCamera() {
        return viewport.getCamera();
    }

    public Viewport getScreenViewport() {
        return viewport;
    }

    public void reset() {
    }

    @Override
    public void pause() {
        ProjectGame.state = State.PAUSE;
    }

    @Override
    public void resume() {
        ProjectGame.state = State.RUNNING;
    }

    @Override
    public void resize(int width, int height) {
        getScreenViewport().update(width, height, true);
    }

    @Override
    public void hide() {
    }
}
