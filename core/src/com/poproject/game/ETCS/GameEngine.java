package com.poproject.game.ETCS;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.poproject.game.ETCS.components.Box2dBodyComponent;
import com.poproject.game.ETCS.components.PlayerComponent;
import com.poproject.game.KeyboardController;
import com.poproject.game.ETCS.systems.AnimationSystem;
import com.poproject.game.ETCS.systems.CollisionSystem;
import com.poproject.game.ETCS.systems.PlayerControlSystem;
import com.poproject.game.ETCS.systems.RenderingSystem;
import com.poproject.game.WorldContactListener;
import com.poproject.game.screen.GameScreen;

import static com.poproject.game.ProjectGame.BIT_GROUND;
import static com.poproject.game.ProjectGame.BIT_PLAYER;

public class GameEngine extends PooledEngine {
    public static ComponentMapper<PlayerComponent> playerComponentMapper = ComponentMapper.getFor(PlayerComponent.class);
    public static ComponentMapper<Box2dBodyComponent> box2dBodyComponentComponentMapper = ComponentMapper.getFor(Box2dBodyComponent.class);
    private final Vector2 playerStartPosition = new Vector2(4.5f, 3f);
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;
    private final GameScreen gameScreen;
    private final World world;
    public GameEngine(GameScreen gameScreen) { //KeyboardController controller
//        this.addSystem(new AnimationSystem());
//        this.addSystem(new RenderingSystem());
//        this.addSystem(new CollisionSystem());
        this.gameScreen = gameScreen;
        this.world = gameScreen.getWorld();
        Box2D.init();
        fixtureDef = new FixtureDef();
        bodyDef = new BodyDef();
        this.addSystem(new PlayerControlSystem());//controller
    }

    public void spawnPlayer(){
        Entity playerEntity = createEntity();
        playerEntity.add(this.createComponent(PlayerComponent.class));

        //BOX2d Body
        Box2dBodyComponent box2dBodyComponent = this.createComponent(Box2dBodyComponent.class);
        resetBodyAndFixtureDef();

        bodyDef.position.set(playerStartPosition);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        Body playerBody = world.createBody(bodyDef);
        playerBody.setUserData("PLAYER");
//        player.setUserData("PLAYER");

        fixtureDef.density = 1;
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.69f;
        fixtureDef.filter.categoryBits = BIT_PLAYER;
        fixtureDef.filter.maskBits = BIT_GROUND;

        final PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = pShape;
        playerBody.createFixture(fixtureDef);
        pShape.dispose();

        box2dBodyComponent.body = playerBody;
        playerEntity.add(box2dBodyComponent);
        addEntity(playerEntity);
    }

    private void resetBodyAndFixtureDef(){
        bodyDef.gravityScale = 1;
        bodyDef.position.set(0,0);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.fixedRotation = true;
        bodyDef.active = true;

        fixtureDef.density = 1;
        fixtureDef.filter.maskBits = 0;
        fixtureDef.filter.categoryBits = 0;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.isSensor = false;
        fixtureDef.shape = null;
    }
}