package com.poproject.game.etcs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.poproject.game.Assets;
import com.poproject.game.ProjectGame;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.HealthBallComponent;
import com.poproject.game.etcs.components.ProjectileComponent;
import com.poproject.game.etcs.components.TextureComponent;
import com.poproject.game.screen.ScreenType;
import com.poproject.game.utils.BodyFactory;

public class EntityBuilder {
    private final GameEngine engine;
    private final ComponentBuilder componentBuilder;
    public EntityBuilder(GameEngine engine){
        this.engine = engine;
        componentBuilder = new ComponentBuilder(engine);
    }

    public Entity createPlayer(Vector2 startPosition){
        Entity entity = engine.createEntity();
        entity.add(componentBuilder.createPlayerComponent());
        Body body = BodyFactory.getInstance().makePlayerBody(startPosition);
        final Vector2 scale = new Vector2(0.03f, 0.03f);

        entity.add(componentBuilder.createBodyComponent(body, scale));
        entity.add(componentBuilder.createTextureComponent(Assets.player));
        entity.add(componentBuilder.createMoveableComponent(3f,1));
        entity.add(componentBuilder.createCameraComponent(ProjectGame.getInstance().getGameCamera()));
        entity.add(componentBuilder.craetePlayerWeaponComponent());
        entity.add(componentBuilder.createPlayerBuildingComponent());

        createHealthBall(entity, startPosition);
        return entity;
    }

    public void createHealthBall(Entity followed, Vector2 startPos){
        final Vector2 pos = new Vector2(startPos.x - 0.3f, startPos.y + 0.6f);
        Entity entity = engine.createEntity();
        entity.add(componentBuilder.createBodyComponent(BodyFactory.getInstance().makeEntityBody(pos, (short)0, (short)0),
                new Vector2(0.2f, 0.2f)));
        entity.add(componentBuilder.createHealthBallComponent(followed));
        TextureComponent textureComponent = componentBuilder.createTextureComponent(Assets.healthBall);
        textureComponent.priority = true;
        entity.add(textureComponent);

        engine.addEntity(entity);
    }
    public Entity createEnemy(Vector2 startPosition, Body followBody){
        Entity entity = engine.createEntity();
        entity.add(componentBuilder.createEnemyComponent(followBody));
        Body body = BodyFactory.getInstance().makeEnemyBody(startPosition);
        final Vector2 scale = new Vector2(0.05f, 0.05f);

        entity.add(componentBuilder.createBodyComponent(body, scale));
        entity.add(componentBuilder.createMoveableComponent(1.5f,-1));
        entity.add(componentBuilder.createTextureComponent(Assets.enemy));

        createHealthBall(entity, startPosition);
        return entity;
    }

    public void createTowerEntity(float x, float y){
//        Entity enem = createEnemy(new Vector2(x,y));
//        engine.addEntity(enem);
        Entity towerEntity = engine.createEntity();

        towerEntity.add(componentBuilder.createTowerComponent());
        towerEntity.add(componentBuilder.createBodyComponent(BodyFactory.getInstance().makeTowerBody(new Vector2(x, y)), new Vector2(0.05f,0.05f)));
        towerEntity.add(componentBuilder.createTextureComponent(Assets.gorilla));

        engine.addEntity(towerEntity);
    }

    public void createProjectileEntity(float destX, float destY, Vector2 startPos){
        Entity projectile = engine.createEntity();

        //projectileComponent
        ProjectileComponent projectileComponent = engine.createComponent(ProjectileComponent.class);
        projectileComponent.destX = destX;
        projectileComponent.destY = destY;
        projectile.add(projectileComponent);

        //setting life-time
        projectileComponent.maxFlightTime = ((new Vector2(destX, destY)).sub(startPos)).len() / projectileComponent.speed/6f;
        projectileComponent.flightTime = 0f;

        //Body component
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        Body projectileBody = BodyFactory.getInstance().createProjectileBody(startPos);
        bodyComponent.scale = new Vector2(0.25f, 0.25f);

        //setting velocity
        Vector2 velocity = (new Vector2(destX, destY)).sub(startPos);
        velocity.nor().scl(projectileComponent.speed).scl(projectileComponent.mass);
        projectileBody.applyLinearImpulse(velocity, projectileBody.getWorldCenter(), true);
        projectileBody.applyAngularImpulse(200f, true);

        bodyComponent.body = projectileBody;
        projectile.add(bodyComponent);

        //sprite
        projectile.add(componentBuilder.createTextureComponent(Assets.banana));

        engine.addEntity(projectile);

//        System.out.println("X: " + destX + "Y: " + destY);
//        System.out.println(projectileComponent.maxFlightTime + " " + projectileComponent.flightTime);
    }
}
