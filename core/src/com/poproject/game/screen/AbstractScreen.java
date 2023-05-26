package com.poproject.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.poproject.game.ProjectGame;

public abstract class AbstractScreen implements Screen {
    protected final ProjectGame context;
    protected final FitViewport viewport;
//    protected final Box2DDebugRenderer box2DDebugRenderer;

    public AbstractScreen(final ProjectGame context){
        this.context = context;
        viewport = context.getScreenViewport();
//        box2DDebugRenderer = context.getBox2DDebugRenderer();
    }

    public void resize(final int width, final int height){
        viewport.update(width, height);
    }
}
