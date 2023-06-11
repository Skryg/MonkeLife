package com.poproject.game.etcs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.poproject.game.Assets;
import com.poproject.game.ProjectGame;
import com.poproject.game.etcs.components.*;
import com.poproject.game.etcs.systems.*;
import com.poproject.game.screen.GameScreen;
import com.poproject.game.utils.BodyFactory;

public class GameEngine extends PooledEngine {
    public static ComponentMapper<PlayerComponent> playerComponentMapper = ComponentMapper.getFor(PlayerComponent.class);
    public static ComponentMapper<BodyComponent> bodyComponentComponentMapper = ComponentMapper.getFor(BodyComponent.class);
    public static ComponentMapper<CameraComponent> cameraComponentMapper = ComponentMapper.getFor(CameraComponent.class);
    public static ComponentMapper<PlayerWeaponComponent> playerWeaponComponentMapper = ComponentMapper.getFor(PlayerWeaponComponent.class);
    public final EntityBuilder entityBuilder = new EntityBuilder(this);

    public GameEngine(GameScreen gameScreen) { //KeyboardController controller
//        this.addSystem(new AnimationSystem());
//        this.addSystem(new CollisionSystem());
        this.addSystem(new PlayerControlSystem());//controller
        this.addSystem(new CamFollowPlayerSystem());
        this.addSystem(new RenderingSystem(gameScreen.getMapRenderer(), (OrthographicCamera) gameScreen.getCamera()));
    }


    public void spawnPlayer(){
        Entity playerEntity = entityBuilder.createPlayer();
        addEntity(playerEntity);
    }





}
