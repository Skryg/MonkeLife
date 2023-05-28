package com.poproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    private static Skin uiSkin;
    public static final String map = "map/mapaProjekt.tmx";
    public static final String player = "player.png";
    public static final String skinUI = "skin/craftacular-ui.json";
    public static void loadSkin(AssetManager assetManager){
        assetManager.load(skinUI, Skin.class);
        assetManager.finishLoading();
    }
    public static void load(AssetManager assetManager){
        assetManager.load(player, Texture.class);
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(assetManager.getFileHandleResolver()));
        assetManager.load(map, TiledMap.class);
    }

}
