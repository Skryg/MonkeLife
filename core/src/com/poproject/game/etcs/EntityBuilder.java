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
    private GameEngine engine;
    private ComponentBuilder componentBuilder;
    public EntityBuilder(GameEngine engine){
        this.engine = engine;
        componentBuilder = new ComponentBuilder(engine);
    }

    public Entity createPlayer(){
        Entity entity = engine.createEntity();
        entity.add(componentBuilder.createPlayerComponent());
        Body body = BodyFactory.getInstance().makePlayerBody(new Vector2(4.5f, 3f));
        entity.add(componentBuilder.createBodyComponent(body, new Vector2(0.03f, 0.03f)));
        entity.add(componentBuilder.createTextureComponent(Assets.player));
        entity.add(componentBuilder.createCameraComponent(ProjectGame.getInstance().getGameCamera()));

        return entity;
    }

}
