package com.poproject.game.etcs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.poproject.game.Assets;
import com.poproject.game.ProjectGame;
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
        entity.add(componentBuilder.createBodyComponent(body, new Vector2(0.03f, 0.03f)));
        entity.add(componentBuilder.createTextureComponent(Assets.player));
        entity.add(componentBuilder.createMoveableComponent(3f,1));
        entity.add(componentBuilder.createCameraComponent(ProjectGame.getInstance().getGameCamera()));
        return entity;
    }

    public Entity createEnemy(Vector2 startPosition){
        Entity entity = engine.createEntity();
        entity.add(componentBuilder.createEnemyComponent());
        Body body = BodyFactory.getInstance().makePlayerBody(startPosition);
        entity.add(componentBuilder.createBodyComponent(body, new Vector2(0.05f, 0.05f)));
        entity.add(componentBuilder.createMoveableComponent(3f,-1));
        entity.add(componentBuilder.createTextureComponent(Assets.enemy));
        return entity;
    }

}
