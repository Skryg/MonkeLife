package com.poproject.game.etcs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.ProjectileComponent;
import com.poproject.game.utils.BodyFactory;

public class EntityFactory {
    private static EntityFactory instance;
    private GameEngine gameEngine;
    public EntityFactory(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        if(instance == null)instance = this;
    }
    public static EntityFactory getInstance() {
        return instance;
    }
    public void createProjectileEntity(float destX, float destY, Vector2 startPos){
        Entity projectile = gameEngine.createEntity();

        //projectileComponent
        ProjectileComponent projectileComponent = gameEngine.createComponent(ProjectileComponent.class);
        projectileComponent.destX = destX;
        projectileComponent.destY = destY;
        projectile.add(projectileComponent);

        //Body component
        BodyComponent bodyComponent = gameEngine.createComponent(BodyComponent.class);
        Body projectileBody = BodyFactory.getInstance().createProjectileBody(startPos);

        //setting velocity
        Vector2 velocity = (new Vector2(destX, destY)).sub(startPos);
        velocity.nor().scl(projectileComponent.speed).scl(projectileComponent.mass);
        projectileBody.applyLinearImpulse(velocity, projectileBody.getWorldCenter(), true);

        bodyComponent.body = projectileBody;
    }
}
