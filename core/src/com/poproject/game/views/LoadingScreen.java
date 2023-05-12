package com.poproject.game.views;

import com.badlogic.gdx.Screen;
import com.poproject.game.ProjectGame;

public class LoadingScreen implements Screen {
    ProjectGame parent;
    public LoadingScreen(ProjectGame projectGame){
        this.parent = projectGame;
    }
    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        parent.changeScreen(ProjectGame.ScreenType.MENU);
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
