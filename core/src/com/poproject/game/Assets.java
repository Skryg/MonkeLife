package com.poproject.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.poproject.game.audio.AudioManager;
import com.poproject.game.audio.AudioType;

public class Assets {
    public static final String map = "map/mapaProjekt.tmx";
    public static final String player = "player.png";
    public static final String enemy = "enemy.png";
    public static final String skinUI = "skin/craftacular-ui.json";
    public static final String banana = "banana.png";
    public static final String gorilla = "gorilla.png";
    public static final String healthBall = "HealthBall.png";

    public static void loadSkin(AssetManager assetManager) {
        assetManager.load(skinUI, Skin.class);
        assetManager.finishLoading();
    }

    public static void load(AssetManager assetManager, AudioManager audioManager) {
        assetManager.load(player, Texture.class);
        assetManager.load(enemy, Texture.class);
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(assetManager.getFileHandleResolver()));
        assetManager.load(map, TiledMap.class);
        assetManager.load(banana, Texture.class);
        assetManager.load(gorilla, Texture.class);
        assetManager.load(healthBall, Texture.class);

        //audio
        for (AudioType audioType : AudioType.values()) {
            assetManager.load(audioType.getFilePath(), audioType.isMusic() ? Music.class : Sound.class);
        }
    }
}
