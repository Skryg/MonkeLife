package com.poproject.game.screen;

import com.badlogic.gdx.Screen;
import com.poproject.game.screen.ui.*;

public enum ScreenType {
    GAME(GameScreen.class), LOADING(LoadingScreen.class), MENU(MenuScreen.class), PREFERENCES(PreferencesScreen.class), PAUSE(PauseScreen.class), DEATH(DeathScreen.class);
    private final Class<? extends Screen> screenClass;

    ScreenType(final Class<? extends Screen> screenClass) {
        this.screenClass = screenClass;
    }

    public Class<? extends Screen> getScreenClass() {
        return screenClass;
    }
}
