package com.poproject.game.etcs;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.poproject.game.etcs.components.*;
import com.poproject.game.etcs.systems.*;
import com.poproject.game.screen.GameScreen;

public class GameEngine extends PooledEngine {
    public static ComponentMapper<PlayerComponent> playerComponentMapper = ComponentMapper.getFor(PlayerComponent.class);
    public static ComponentMapper<BodyComponent> bodyComponentComponentMapper = ComponentMapper.getFor(BodyComponent.class);
    public static ComponentMapper<CameraComponent> cameraComponentMapper = ComponentMapper.getFor(CameraComponent.class);
    public static ComponentMapper<PlayerWeaponComponent> playerWeaponComponentMapper = ComponentMapper.getFor(PlayerWeaponComponent.class);
    public static ComponentMapper<MoveableComponent> moveableComponentMapper = ComponentMapper.getFor(MoveableComponent.class);
    public static ComponentMapper<EnemyComponent> enemyComponentMapper = ComponentMapper.getFor(EnemyComponent.class);
    public static ComponentMapper<ProjectileComponent> projectileComponentMapper = ComponentMapper.getFor(ProjectileComponent.class);
    public static ComponentMapper<PlayerBuildingComponent> playerBuildingComponentMapper = ComponentMapper.getFor(PlayerBuildingComponent.class);
    public static ComponentMapper<TowerComponent> towerComponentMapper = ComponentMapper.getFor(TowerComponent.class);
    public static ComponentMapper<HealthBallComponent> healthBallComponentMapper = ComponentMapper.getFor(HealthBallComponent.class);
    public static ComponentMapper<CollisionComponent> collisionComponentMapper = ComponentMapper.getFor(CollisionComponent.class);

    public final EntityBuilder entityBuilder = new EntityBuilder(this);

    public GameEngine(GameScreen gameScreen) {
        this.addSystem(new CollisionSystem(this));
        this.addSystem(new PlayerControlSystem());
        this.addSystem(new CamFollowPlayerSystem());
        this.addSystem(new RenderingSystem(gameScreen.getMapRenderer(), (OrthographicCamera) gameScreen.getCamera()));
        this.addSystem(new EnemyMovementSystem());
        this.addSystem(new PlayerAttackSystem(gameScreen.getCamera(), this));
        this.addSystem(new ProjectileSystem(this));
        this.addSystem(new PlayerBuildingSystem(gameScreen.getCamera(), this));
        this.addSystem(new TowerShootingSystem(this));
        this.addSystem(new HealthBallSystem());
        this.addSystem(new EnemyGenerateSystem(3, this, 15));
        this.addSystem(new DeleteDeadSystem(this));
        this.addSystem(new PlayerDieSystem());

        spawnPlayer();
    }

    public void spawnPlayer() {
        Entity playerEntity = entityBuilder.createPlayer(new Vector2(20.5f, 20f));
        addEntity(playerEntity);
    }

    public Entity getPlayer() {
        try {
            return getEntitiesFor(Family.one(PlayerComponent.class).get()).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public void spawnEnemy(Vector2 startPosition) {
        BodyComponent bc;
        Entity player = getPlayer();
        if (player == null) throw new RuntimeException("Player must be spawned first!");

        bc = getPlayer().getComponent(BodyComponent.class);
        Entity enemyEntity = entityBuilder.createEnemy(startPosition, bc.body);
        addEntity(enemyEntity);
    }

    public void dispose() {
        removeAllSystems();
        removeAllEntities();
    }

}
