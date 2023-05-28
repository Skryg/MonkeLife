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
import com.badlogic.gdx.utils.Select;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.poproject.game.screen.LoadingScreen;
import com.poproject.game.screen.ScreenType;

import java.util.EnumMap;

public class ProjectGame extends Game {
	private EnumMap<ScreenType, Screen> screenCache;

	private AssetManager assetManager;
	private OrthographicCamera gameCamera;
	private SpriteBatch spriteBatch;
	private float accumulator;
	private AppPreferences preferences;

	private static ProjectGame instance;
	public static final float UNIT_SCALE = 1/1024f;
	public static final short BIT_GROUND = 1 << 2;
	public static final short BIT_PLAYER = 1 << 3;
	public static final float FIXED_TIME_STEP = 1/60f;
	public static State state = State.RUNNING;

	public static ProjectGame getInstance(){
		return instance;
	}
	public void create(){
		if(instance == null)instance = this;
		spriteBatch = new SpriteBatch();
		accumulator = 0f;
		preferences = new AppPreferences();

		//assets
		assetManager = new AssetManager();
		Assets.loadSkin(assetManager);

		screenCache = new EnumMap<>(ScreenType.class);
		setScreen(ScreenType.MENU);
	}

	public void setScreen(final ScreenType screenType){
		final Screen screen = screenCache.get(screenType);
		if(screen == null){
			try{
				final Object newScreen = ClassReflection.getConstructor(screenType.getScreenClass(), ProjectGame.class).newInstance(this);
				screenCache.put(screenType, (Screen)newScreen);
				setScreen((Screen) newScreen);
			}catch(ReflectionException e){
				throw new GdxRuntimeException("Screen" + screen + "was not created", e.getCause());
			}
		}else setScreen(screen);
	}
	public SpriteBatch getSpriteBatch(){return spriteBatch;}
	public AssetManager getAssetManager(){return assetManager;}

	public void dispose(){
		super.dispose();
		assetManager.dispose();
		spriteBatch.dispose();
	}
	public void render(){
		super.render();
	}
	public AppPreferences getPreferences(){
		return preferences;
	}


}
