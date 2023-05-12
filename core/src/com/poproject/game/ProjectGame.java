package com.poproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.poproject.game.screen.LoadingScreen;
import com.poproject.game.screen.ScreenType;

import java.util.EnumMap;

public class ProjectGame extends Game {
	private EnumMap<ScreenType, Screen> screenCache;
	private FitViewport screenViewport;
	private World world;
	private Box2DDebugRenderer box2DDebugRenderer;
	private WorldContactListener worldContactListener;
	private AssetManager assetManager;
	private OrthographicCamera gameCamera;
	private SpriteBatch spriteBatch;
	private float accumulator;

	public static final float UNIT_SCALE = 1/1024f;
	public static final short BIT_BOX = 1 << 0;
	public static final short BIT_CIRCLE = 1 << 1;
	public static final short BIT_GROUND = 1 << 2;
	public static final short BIT_PLAYER = 1 << 3;
	public static final float FIXED_TIME_STEP = 1/60f;

	public void create(){

		spriteBatch = new SpriteBatch();
		accumulator = 0f;

		//box2d
		Box2D.init();
		world = new World(new Vector2(0, 0f), false);
		worldContactListener = new WorldContactListener();
		world.setContactListener(worldContactListener);
		box2DDebugRenderer = new Box2DDebugRenderer();

		//assets

		assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(assetManager.getFileHandleResolver()));

		//first screen

		gameCamera = new OrthographicCamera();
		screenViewport = new FitViewport(9, 16, gameCamera);
		screenCache = new EnumMap<>(ScreenType.class);
		setScreen(new LoadingScreen(this));
	}

	public void setScreen(final ScreenType screenType){
		final Screen screen = screenCache.get(screenType);
		if(screen == null){
			try{
				final Object newScreen = ClassReflection.getConstructor(screenType.getScreenClass(), ProjectGame.class).newInstance(this);
				screenCache.put(screenType, (Screen)newScreen);
				setScreen((Screen) newScreen);
			}catch(ReflectionException e){
				throw new GdxRuntimeException("Screen" + screen + "was not created");
			}
		}else setScreen(screen);
	}
	public SpriteBatch getSpriteBatch(){return spriteBatch;}
	public AssetManager getAssetManager(){return assetManager;}
	public OrthographicCamera getGameCamera(){return gameCamera;}
	public WorldContactListener getWorldContactListener(){return worldContactListener;}

	public FitViewport getScreenViewport() {
		return screenViewport;
	}

	public World getWorld(){
		return world;
	}

	public Box2DDebugRenderer getBox2DDebugRenderer(){
		return box2DDebugRenderer;
	}

	public void dispose(){
		super.dispose();
		world.dispose();
		box2DDebugRenderer.dispose();
		assetManager.dispose();
	}

	public void render(){
		super.render();

		accumulator += Math.min(0.25f, Gdx.graphics.getDeltaTime());
		while(accumulator >= FIXED_TIME_STEP){
			world.step(FIXED_TIME_STEP, 6, 2);
			accumulator -= FIXED_TIME_STEP;
		}
		//for interpolation
		final float alpha = accumulator / FIXED_TIME_STEP;
	}

	//	SpriteBatch batch;
//	Texture img;
////	List<GameObject> gameObjects = new LinkedList<>();
//	FitViewport viewport;
//	static Game instance = null;
//	@Override
//	public void create () {
//		if(instance == null)instance = this;
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//		viewport = new FitViewport(800, 450);
//	}
//
//	@Override
//	public void render () {
//		ScreenUtils.clear(0, 0, 0, 1);
//		viewport.apply(true);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
//
	@Override
	public void resize(final int width, final int height){
		screenViewport.update(width, height);
	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
}
