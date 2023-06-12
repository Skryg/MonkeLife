package com.poproject.game.etcs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.poproject.game.ProjectGame;
import com.poproject.game.etcs.components.*;

public class ComponentBuilder {
    private final GameEngine engine;

    public ComponentBuilder(GameEngine engine) {
        this.engine = engine;
    }

    public TextureComponent createTextureComponent(String assetName) {
        Texture texture = ProjectGame.getInstance().getAssetManager().get(assetName, Texture.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.region = new TextureRegion(texture);
        return textureComponent;
    }

    public PlayerComponent createPlayerComponent() {
        return engine.createComponent(PlayerComponent.class);
    }

    public BodyComponent createBodyComponent(Body body, Vector2 scale) {
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        bodyComponent.body = body;
        bodyComponent.scale.set(scale);
        return bodyComponent;
    }

    public CameraComponent createCameraComponent(Camera camera) {
        CameraComponent cameraComponent = engine.createComponent(CameraComponent.class);
        cameraComponent.camera = camera;
        return cameraComponent;
    }

    public MoveableComponent createMoveableComponent(float speed, int directionScale) {
        MoveableComponent moveableComponent = engine.createComponent(MoveableComponent.class);
        moveableComponent.speed = speed;
        moveableComponent.directionScale = directionScale;
        return moveableComponent;
    }

    public PlayerWeaponComponent craetePlayerWeaponComponent() {
        return engine.createComponent(PlayerWeaponComponent.class);
    }

    public PlayerBuildingComponent createPlayerBuildingComponent() {
        return engine.createComponent(PlayerBuildingComponent.class);
    }

    public TowerComponent createTowerComponent() {
        return engine.createComponent(TowerComponent.class);
    }

    public EnemyComponent createEnemyComponent(Body followBody) {
        EnemyComponent enemyComponent = engine.createComponent(EnemyComponent.class);
        enemyComponent.focusBody = followBody;
        return enemyComponent;
    }

    public HealthBallComponent createHealthBallComponent(Entity followed) {
        HealthBallComponent hbc = engine.createComponent(HealthBallComponent.class);
        hbc.followedEntity = followed;
        return hbc;
    }

    public CollisionComponent createCollisionComponent() {
        return engine.createComponent(CollisionComponent.class);
    }
}
