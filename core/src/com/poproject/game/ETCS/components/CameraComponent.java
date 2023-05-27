package com.poproject.game.ETCS.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Pool;

public class CameraComponent implements Component, Pool.Poolable {
    public Camera camera;
    @Override
    public void reset() {
        camera = null;
    }
}
