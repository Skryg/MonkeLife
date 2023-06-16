package com.poproject.game.screen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.poproject.game.Assets;
import com.poproject.game.ProjectGame;

public class DeathScreen extends UIScreen {

    public DeathScreen(ProjectGame projectGame) {
        super(projectGame);
    }

    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        Table table = new Table();
        table.setFillParent(true);
        System.out.println(stage.getActors().toString(" "));
        stage.addActor(table);

        Skin skin = ProjectGame.getInstance().getAssetManager().get(Assets.skinUI);
        TextButton exit = createExitMenuButton(skin);
        Label label = new Label("You died! Congratulations!", skin);

        table.add(label);
        table.row();
        table.add(exit);
    }
}
