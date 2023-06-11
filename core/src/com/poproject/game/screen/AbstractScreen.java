package com.poproject.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class AbstractScreen implements Screen {
    private final Viewport viewport;
    AbstractScreen(Viewport viewport){
        this.viewport  = viewport;
    }
    public Camera getCamera(){
        return viewport.getCamera();
    }
    public Viewport getScreenViewport() {
        return viewport;
    }
}
