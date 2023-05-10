package com.poproject.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends GameObject{
    SpriteBatch batch;
    Texture img;
    void start(){
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }
    void update(){
        batch.begin();
        batch.draw(img, 25, 25);
        batch.end();
    }
}
