package com.poproject.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.poproject.game.Assets;
import com.poproject.game.ProjectGame;
import com.poproject.game.State;
public class PauseScreen extends AbstractScreen {
    Stage stage;
    ProjectGame parent;
    public PauseScreen(ProjectGame projectGame){
        super(new ScreenViewport());
        parent = projectGame;
        stage = new Stage(getScreenViewport());
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        System.out.println(stage.getActors().toString(" "));
        stage.addActor(table);

        Skin skin = parent.getAssetManager().get(Assets.skinUI);

        TextButton newGame = new TextButton("Continue", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit Main Menu", skin);

        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0,10,0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ProjectGame.state = State.RUNNING;
                parent.setScreen(ScreenType.MENU);
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                resume();
                parent.setScreen(ScreenType.LOADING);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(ScreenType.PREFERENCES);
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {
        ProjectGame.state = State.RUNNING;
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }

}
