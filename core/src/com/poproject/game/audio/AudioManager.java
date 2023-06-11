package com.poproject.game.audio;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
    private AudioType currentMusicType;
    private Music currentMusic;
    private final AssetManager assetManager;

    public AudioManager(AssetManager assetManager){
        this.assetManager = assetManager;
    }

    public void playAudio(final AudioType audioType){
        if(!audioType.isMusic()) {
            assetManager.get(audioType.getFilePath(), Sound.class).play(audioType.getVolume());
            return;
        }
        if(currentMusicType == audioType)return;
        if(currentMusic != null)currentMusic.stop();
        currentMusicType = audioType;
        currentMusic = assetManager.get(audioType.getFilePath(), Music.class);
        currentMusic.setLooping(true);
        currentMusic.setVolume(audioType.getVolume());
        currentMusic.play();
    }
}
