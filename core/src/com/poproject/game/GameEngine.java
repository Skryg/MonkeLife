package com.poproject.game;

import com.badlogic.ashley.core.Engine;

public class GameEngine extends Engine {
    private Engine engine;

    public GameEngine(KeyboardController controller) {
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderingSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new PlayerControlSystem(controller));
    }
}
