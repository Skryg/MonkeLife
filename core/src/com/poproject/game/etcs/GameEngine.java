package com.poproject.game.etcs;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.poproject.game.etcs.components.*;
import com.poproject.game.etcs.systems.*;
import com.poproject.game.screen.GameScreen;

import java.lang.reflect.InvocationTargetException;

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
        this.addSystem(new EnemyGenerateSystem(3, this));
    }


    public void spawnPlayer(){
        Entity playerEntity = entityBuilder.createPlayer(new Vector2(4.5f, 3f));
        addEntity(playerEntity);
    }
    public Entity getPlayer(){
        try{
            return getEntitiesFor(Family.one(PlayerComponent.class).get()).get(0);
        }
        catch(Exception e){
            return null;
        }
    }

    public void spawnEnemy(Vector2 startPosition){
        BodyComponent bc;
        Entity player = getPlayer();
        if(player == null) throw new RuntimeException("Player must be spawned first!");

        bc = getPlayer().getComponent(BodyComponent.class);
        Entity enemyEntity = entityBuilder.createEnemy(startPosition, bc.body);
        addEntity(enemyEntity);
    }





}
