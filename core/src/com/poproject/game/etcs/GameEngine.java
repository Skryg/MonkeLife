package com.poproject.game.etcs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.poproject.game.Assets;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.CameraComponent;
import com.poproject.game.etcs.components.PlayerComponent;
import com.poproject.game.etcs.components.TextureComponent;
import com.poproject.game.etcs.systems.*;
import com.poproject.game.ProjectGame;
import com.poproject.game.map.CollisionArea;
import com.poproject.game.screen.GameScreen;

import static com.poproject.game.ProjectGame.BIT_GROUND;
import static com.poproject.game.ProjectGame.BIT_PLAYER;

public class GameEngine extends PooledEngine {
    public static ComponentMapper<PlayerComponent> playerComponentMapper = ComponentMapper.getFor(PlayerComponent.class);
    public static ComponentMapper<BodyComponent> bodyComponentComponentMapper = ComponentMapper.getFor(BodyComponent.class);
    public static ComponentMapper<CameraComponent> cameraComponentMapper = ComponentMapper.getFor(CameraComponent.class);
    private final Vector2 playerStartPosition = new Vector2(4.5f, 3f);
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;
    private final GameScreen gameScreen;
    private final World world;
    public GameEngine(GameScreen gameScreen) { //KeyboardController controller
//        this.addSystem(new AnimationSystem());
//        this.addSystem(new CollisionSystem());
        this.gameScreen = gameScreen;
        this.world = gameScreen.getWorld();
        Box2D.init();
        fixtureDef = new FixtureDef();
        bodyDef = new BodyDef();
        this.addSystem(new PlayerControlSystem());//controller
        this.addSystem(new CamFollowPlayerSystem());
        this.addSystem(new RenderingSystem(gameScreen.getMapRenderer()));
    }

    public void spawnPlayer(){
        Entity playerEntity = createEntity();
        playerEntity.add(this.createComponent(PlayerComponent.class));

        //BOX2d Body
        BodyComponent box2dBodyComponent = this.createComponent(BodyComponent.class);
        resetBodyAndFixtureDef();

        bodyDef.position.set(playerStartPosition);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        Body playerBody = world.createBody(bodyDef);
        playerBody.setUserData("PLAYER");

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
        box2dBodyComponent.scale.set(0.03f, 0.03f);
        playerEntity.add(box2dBodyComponent);

        //Texture
        Texture texture = ProjectGame.getInstance().getAssetManager().get(Assets.player,Texture.class);
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.region = new TextureRegion(texture);

        playerEntity.add(textureComponent);

        //camera system
        CameraComponent camCmp = createComponent(CameraComponent.class);
        camCmp.camera = ProjectGame.getInstance().getGameCamera();
        playerEntity.add(camCmp);

        addEntity(playerEntity);
    }


    private void createCollisionArea(CollisionArea ca){
        resetBodyAndFixtureDef();
        bodyDef.position.set(ca.getStartX(), ca.getStartY());
        bodyDef.gravityScale = 0;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        final Body body = world.createBody(bodyDef);

        fixtureDef.filter.categoryBits = BIT_GROUND;
        fixtureDef.filter.maskBits = -1;
        final ChainShape chainShape = new ChainShape();
        chainShape.createChain(ca.getVertices());
        fixtureDef.shape = chainShape;
        body.createFixture(fixtureDef);

        chainShape.dispose();
    }
    public void createCollisionAreas(Array<CollisionArea> collisionAreas){
        for(CollisionArea ca : collisionAreas)createCollisionArea(ca);
    }
    private void resetBodyAndFixtureDef(){
        bodyDef.gravityScale = 1;
        bodyDef.position.set(0,0);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.fixedRotation = true;

        fixtureDef.density = 1;
        fixtureDef.filter.maskBits = 0;
        fixtureDef.filter.categoryBits = 0;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.isSensor = false;
        fixtureDef.shape = null;
    }


}
