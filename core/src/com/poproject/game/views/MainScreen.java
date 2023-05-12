//package com.poproject.game.views;
//
//import com.badlogic.ashley.core.Engine;
//import com.badlogic.ashley.core.PooledEngine;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Camera;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.World;
//import com.poproject.game.KeyboardController;
//import com.poproject.game.entity.systems.AnimationSystem;
//import com.poproject.game.entity.systems.CollisionSystem;
//import com.poproject.game.entity.systems.PlayerControlSystem;
//import com.poproject.game.entity.systems.RenderingSystem;
//
//public class MainScreen implements Screen {
//    Game parent;
//    KeyboardController controller;
//    World world;
//    Engine engine;
//    SpriteBatch sb;
//    Camera cam;
//    public MainScreen(Game game){
//        parent = game;
//        controller = new KeyboardController();
//        world = new World(new Vector2(0,-10f), true);
//        //world.setContactListener(new B2dContactListener());
////        bodyFactory = BodyFactory.getInstance(world);
//
////        parent.assMan.queueAddSounds();
////        parent.assMan.manager.finishLoading();
////        atlas = parent.assMan.manager.get("images/game.atlas", TextureAtlas.class);
////        ping = parent.assMan.manager.get("sounds/ping.wav",Sound.class);
////        boing = parent.assMan.manager.get("sounds/boing.wav",Sound.class);
//
//        sb = new SpriteBatch();
//        // Create our new rendering system
//        RenderingSystem renderingSystem = new RenderingSystem(sb);
//        cam = renderingSystem.getCamera();
//        sb.setProjectionMatrix(cam.combined);
//
//        //create a pooled engine
//        engine = new PooledEngine();
//
//        // add all the relevant systems our engine should run
//        engine.addSystem(new AnimationSystem());
//        engine.addSystem(renderingSystem);
////        engine.addSystem(new PhysicsSystem(world));
////        engine.addSystem(new PhysicsDebugSystem(world, renderingSystem.getCamera()));
//        engine.addSystem(new CollisionSystem());
//        engine.addSystem(new PlayerControlSystem(controller));
//    }
//    @Override
//    public void show() {
//
//    }
//
//    @Override
//    public void render(float delta) {
//
//    }
//
//    @Override
//    public void resize(int width, int height) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//}
