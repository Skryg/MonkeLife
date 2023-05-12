package com.poproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poproject.game.views.LoadingScreen;
import com.poproject.game.views.MainScreen;
import com.poproject.game.views.MenuScreen;
import com.poproject.game.views.PreferencesScreen;

public class ProjectGame extends Game {
	public enum ScreenType {
		MENU,
		PREFERENCES,
		APPLICATION
	}

	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private PreferencesScreen preferencesScreen;

	private AppPreferences preferences;

	SpriteBatch batch;

	@Override
	public void create () {
		LoadingScreen loadingScreen = new LoadingScreen(this);
		preferences = new AppPreferences();
		setScreen(loadingScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}

	public void changeScreen(ScreenType screen){
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case APPLICATION:
				if(mainScreen == null) mainScreen = new MainScreen(this);
				this.setScreen(mainScreen);
				break;
			case PREFERENCES:
				if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
				this.setScreen(preferencesScreen);
				break;
		}
	}

	public AppPreferences getPreferences(){
		return preferences;
	}
}
