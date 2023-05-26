package com.poproject.game.ETCS.entities;

import com.badlogic.ashley.core.Component;

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
