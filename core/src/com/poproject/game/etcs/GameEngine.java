package com.poproject.game.etcs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.poproject.game.etcs.components.*;
import com.poproject.game.etcs.systems.*;
import com.poproject.game.screen.GameScreen;

public class GameEngine extends PooledEngine {
    public static ComponentMapper<PlayerComponent> playerComponentMapper = ComponentMapper.getFor(PlayerComponent.class);
    public static ComponentMapper<BodyComponent> bodyComponentComponentMapper = ComponentMapper.getFor(BodyComponent.class);
    public static ComponentMapper<CameraComponent> cameraComponentMapper = ComponentMapper.getFor(CameraComponent.class);
    public static ComponentMapper<PlayerWeaponComponent> playerWeaponComponentMapper =
            ComponentMapper.getFor(PlayerWeaponComponent.class);
    public static ComponentMapper<MoveableComponent> moveableComponentMapper =
            ComponentMapper.getFor(MoveableComponent.class);
    public static ComponentMapper<EnemyComponent> enemyComponentMapper = ComponentMapper.getFor(EnemyComponent.class);
    public static ComponentMapper<ProjectileComponent> projectileComponentMapper = ComponentMapper.getFor(ProjectileComponent.class);
    public static ComponentMapper<PlayerBuildingComponent> playerBuildingComponentMapper = ComponentMapper.getFor(PlayerBuildingComponent.class);
    public final EntityBuilder entityBuilder = new EntityBuilder(this);

    public GameEngine(GameScreen gameScreen) { //KeyboardController controller
//        this.addSystem(new AnimationSystem());
//        this.addSystem(new CollisionSystem());
        this.addSystem(new PlayerControlSystem());//controller
        this.addSystem(new CamFollowPlayerSystem());
        this.addSystem(new RenderingSystem(gameScreen.getMapRenderer(), (OrthographicCamera) gameScreen.getCamera()));
        this.addSystem(new EnemyMovementSystem());
        this.addSystem(new PlayerAttackSystem(gameScreen.getCamera(), this));
        this.addSystem(new ProjectileSystem(this));
        this.addSystem(new PlayerBuildingSystem(gameScreen.getCamera(), this));
    }


    public void spawnPlayer(){
        Entity playerEntity = entityBuilder.createPlayer(new Vector2(4.5f, 3f));
        addEntity(playerEntity);
    }

    public void spawnEnemy(Vector2 startPosition){
        Entity enemyEntity = entityBuilder.createEnemy(startPosition);
        addEntity(enemyEntity);
    }





}
