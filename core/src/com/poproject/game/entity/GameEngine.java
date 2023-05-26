package com.poproject.game.entity;

import com.badlogic.ashley.core.Engine;
import com.poproject.game.KeyboardController;
import com.poproject.game.entity.systems.AnimationSystem;
import com.poproject.game.entity.systems.CollisionSystem;
import com.poproject.game.entity.systems.PlayerControlSystem;
import com.poproject.game.entity.systems.RenderingSystem;

public class GameEngine extends Engine {
    private Engine engine;

    public GameEngine(KeyboardController controller) {
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderingSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new PlayerControlSystem(controller));
    }
}
