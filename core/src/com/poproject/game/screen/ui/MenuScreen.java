package com.poproject.game.screen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.poproject.game.Assets;
import com.poproject.game.ProjectGame;

public class MenuScreen extends UIScreen {

    public MenuScreen(ProjectGame projectGame) {
        super(projectGame);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        Table table = new Table();
        table.setFillParent(true);
        System.out.println(stage.getActors().toString(" "));
        stage.addActor(table);

        Skin skin = projectGame.getAssetManager().get(Assets.skinUI);

        TextButton newGame = createNewGameButton(skin);
        TextButton preferences = createPreferencesButton(skin);
        TextButton exit = createExitGameButton(skin);

        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();
    }
}
