package com.poproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.poproject.game.audio.AudioManager;
import com.poproject.game.audio.AudioType;
import com.poproject.game.screen.AbstractScreen;
import com.poproject.game.screen.ScreenType;
import com.poproject.game.utils.RandomVectorGenerator;

import java.util.EnumMap;

public class ProjectGame extends Game {
    private EnumMap<ScreenType, AbstractScreen> screenCache;
    private AssetManager assetManager;
    private AudioManager audioManager;
    private SpriteBatch spriteBatch;
    private AppPreferences preferences;
    private OrthographicCamera gameCamera;

    private static ProjectGame instance;
    public static final float UNIT_SCALE = 1 / 1024f;
    public static final short BIT_ENEMY = 1 << 1;
    public static final short BIT_GROUND = 1 << 2;
    public static final short BIT_PLAYER = 1 << 3;
    public static final float FIXED_TIME_STEP = 1 / 60f;
    public static State state = State.RUNNING;

    public static ProjectGame getInstance() {
        return instance;
    }

    public void create() {
        if (instance == null) instance = this;
        spriteBatch = new SpriteBatch();
        preferences = new AppPreferences();
        RandomVectorGenerator.setInterval(new Vector2(10, 10), new Vector2(50, 50));
        //assets + audio
        assetManager = new AssetManager();
        audioManager = new AudioManager(assetManager);
        Assets.loadSkin(assetManager);

        screenCache = new EnumMap<>(ScreenType.class);
        setScreen(ScreenType.MENU);
    }

    public void setScreen(final ScreenType screenType) {
        if (screenType == ScreenType.GAME) audioManager.playAudio(AudioType.MAINTHEME);
        AbstractScreen screen = screenCache.get(screenType);
        if (screen == null) {
            try {
                screen = (AbstractScreen) ClassReflection.getConstructor(screenType.getScreenClass(), ProjectGame.class).newInstance(this);
                screenCache.put(screenType, screen);
                setScreen(screen);
            } catch (ReflectionException e) {
                throw new GdxRuntimeException("Screen" + "was not created", e.getCause());
            }
        } else setScreen(screen);
    }

    public void resetScreen(ScreenType type) {
        AbstractScreen screen = screenCache.get(type);
        if (screen != null) screen.reset();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public OrthographicCamera getGameCamera() {
        return gameCamera;
    }

    public void setGameCamera(OrthographicCamera gameCamera) {
        this.gameCamera = gameCamera;
    }

    public void dispose() {
        super.dispose();
        assetManager.dispose();
        spriteBatch.dispose();
    }

    public void render() {
        super.render();
    }

    public AppPreferences getPreferences() {
        return preferences;
    }


}
