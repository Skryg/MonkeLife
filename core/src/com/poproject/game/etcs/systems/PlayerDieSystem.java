package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.poproject.game.ProjectGame;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.PlayerComponent;
import com.poproject.game.screen.ScreenType;

public class PlayerDieSystem extends IteratingSystem {
    public PlayerDieSystem() {
        super(Family.all(PlayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        PlayerComponent playerComponent = GameEngine.playerComponentMapper.get(entity);
        if (!playerComponent.isAlive) {
            ProjectGame.getInstance().setScreen(ScreenType.DEATH);
        }
    }
}
