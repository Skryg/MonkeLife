package com.poproject.game.audio;

public enum AudioType {
//    INTRO("audio/stado_goryli.wav", true, 0.3f),
    MAINTHEME("audio/JungleBoyz.mp3", true, 0.4f),
    SELECT("audio/stado_goryli.wav", false, 0.5f),
    TOWER("audio/stado_goryli.wav", false, 1f),
    NONE("audio/JungleBoyz.mp3", true, 0);

    private final String filePath;
    private final boolean isMusic;
    private final float volume;
    AudioType(final String filePath, final boolean isMusic, final float volume){
        this.filePath = filePath;
        this.isMusic = isMusic;
        this.volume = volume;
    }
    public float getVolume() {
        return volume;
    }
    public String getFilePath() {
        return filePath;
    }
    public boolean isMusic() {
        return isMusic;
    }
}
