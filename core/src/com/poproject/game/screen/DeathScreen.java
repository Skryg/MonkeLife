package com.poproject.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.poproject.game.Assets;
import com.poproject.game.ProjectGame;
import com.poproject.game.State;

public class DeathScreen extends AbstractScreen {

    private final Stage stage;

    public DeathScreen(ProjectGame projectGame) {
        super(new ScreenViewport());
        stage = new Stage(getScreenViewport());
    }

    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        System.out.println(stage.getActors().toString(" "));
        stage.addActor(table);

        Skin skin = ProjectGame.getInstance().getAssetManager().get(Assets.skinUI);

        TextButton exit = new TextButton("Exit Main Menu", skin);
        Label label = new Label("You died! Congratulations!", skin);
        table.add(label);
        table.row();
        table.add(exit);

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ProjectGame.state = State.RUNNING;
                ProjectGame.getInstance().setScreen(ScreenType.MENU);
                ProjectGame.getInstance().resetScreen(ScreenType.GAME);
            }
        });

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void reset() {

    }
}
