package com.poproject.game.ETCS.components;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {
    public enum Type{
        PLAYER,
        ENEMY,
        SCENERY,
        OTHER
    }
    public Type type = Type.OTHER;
}
