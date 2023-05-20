package com.poproject.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TransformComponent implements Component {
    public TransformComponent(){
        position = new Vector3();
    }
    public TransformComponent(Vector3 position){
        this.position = position;
    }
    public final Vector3 position;
    public final Vector2 scale = new Vector2();
    public float rotation = 0.0f;
    public boolean isHidden = false;
}
