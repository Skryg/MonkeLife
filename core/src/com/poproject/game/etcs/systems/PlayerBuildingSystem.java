package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.poproject.game.ProjectGame;
import com.poproject.game.audio.AudioType;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.PlayerBuildingComponent;
import com.poproject.game.etcs.components.PlayerComponent;
import com.poproject.game.etcs.components.PlayerWeaponComponent;

public class PlayerBuildingSystem extends IteratingSystem {
    private final Camera camera;
    private final GameEngine gameEngine;
    public PlayerBuildingSystem(Camera camera, GameEngine gameEngine){
        super(Family.all(PlayerComponent.class).get());
        this.camera = camera;
        this.gameEngine = gameEngine;
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        final PlayerBuildingComponent pbc = GameEngine.playerBuildingComponentMapper.get(entity);
        if(pbc == null || !pbc.readyToBuild(v))return;
        if(!Gdx.input.isButtonPressed(Input.Buttons.RIGHT))return;

        build(pbc);
    }

    private void build(PlayerBuildingComponent pbc){
        gameEngine.entityBuilder.createTowerEntity(worldMousePosition().x, worldMousePosition().y);
        pbc.build();
        ProjectGame.getInstance().getAudioManager().playAudio(AudioType.TOWER);
    }

    private Vector2 worldMousePosition(){
        Vector3 ans = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return new Vector2(ans.x, ans.y);
    }
}
