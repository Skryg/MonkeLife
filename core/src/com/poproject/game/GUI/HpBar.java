package com.poproject.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HpBar {
    private NinePatch fillTexture;
    private NinePatch backTexture;

    HpBar(){
        fillTexture = new NinePatch (new Texture(Gdx.files.internal ("hp.png")),9,9,9,9);
        backTexture = new NinePatch (new Texture (Gdx.files.internal ("hpback.png")),9,9,9,9);
    }

    void render(SpriteBatch batch, int mHP, int aHP){
        int width =(int)( 256* ((aHP * Math.pow(mHP, -1))));
        batch.begin();
        backTexture.draw (batch, 19, 689, 258, 20);
        fillTexture.draw (batch, 20, 690, width, 18);
        batch.end();
    }
}
