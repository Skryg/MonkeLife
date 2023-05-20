package com.poproject.game.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.poproject.game.entity.components.TextureComponent;
import com.poproject.game.entity.components.TransformComponent;

public class WorldObjectEntity extends Entity {
    public WorldObjectEntity(){
        add(new TransformComponent());
        add(new TextureComponent());

        getComponent(TextureComponent.class).region = new TextureRegion( new Texture(Gdx.files.internal("playerHB.png")));
    }
    WorldObjectEntity(Vector3 position){
        add(new TransformComponent(position));
    }
}
