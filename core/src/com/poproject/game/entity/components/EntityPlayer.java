package com.poproject.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class EntityPlayer{

    //akcje gracza
    public class InputComponent implements Component {
        public boolean upPressed;
        public boolean downPressed;
        public boolean leftPressed;
        public boolean rightPressed;
    }

    public class MovementComponent implements Component {
        public float speed;
        public float direction;
    }





}
