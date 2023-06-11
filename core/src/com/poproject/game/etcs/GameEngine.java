package com.poproject.game.etcs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
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
    private final GameScreen gameScreen;
    private final EntityFactory entityFactory;
    private final World world;
    public GameEngine(GameScreen gameScreen) { //KeyboardController controller
//        this.addSystem(new AnimationSystem());
//        this.addSystem(new CollisionSystem());
        this.gameScreen = gameScreen;
        this.world = gameScreen.getWorld();
        this.entityFactory = new EntityFactory(this);

        this.addSystem(new PlayerControlSystem());//controller
        this.addSystem(new CamFollowPlayerSystem());
        this.addSystem(new RenderingSystem(gameScreen.getMapRenderer(), gameScreen.getGameCamera()));
        this.addSystem(new PlayerAttackSystem(gameScreen.getGameCamera()));
    }
    public void spawnPlayer(){
        Entity playerEntity = createEntity();
        Body body = BodyFactory.getInstance().makePlayerBody(new Vector2(4.5f, 3f));

        BodyComponent box2dBodyComponent = this.createComponent(BodyComponent.class);
        box2dBodyComponent.body = body;
        box2dBodyComponent.scale.set(0.03f, 0.03f);
        playerEntity.add(box2dBodyComponent);

        PlayerComponent playerComponent = createComponent(PlayerComponent.class);
        playerEntity.add(playerComponent);

        //Texture
        Texture texture = ProjectGame.getInstance().getAssetManager().get(Assets.player,Texture.class);
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.region = new TextureRegion(texture);

        playerEntity.add(textureComponent);

        CameraComponent camCmp = createComponent(CameraComponent.class);
        camCmp.camera = gameScreen.getGameCamera();
        playerEntity.add(camCmp);

        addEntity(playerEntity);
    }

    public World getWorld(){
        return world;
    }
}
