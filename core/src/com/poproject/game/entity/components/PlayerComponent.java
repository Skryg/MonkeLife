package com.poproject.game.entity.components;

import com.badlogic.ashley.core.Component;

public class PlayerComponent implements Component{
    public String name;
    public int level;
    public int healthPoints;
    public int manaPoints;
    public int strength;
    public int speed;
    public PlayerComponent() {
        name = "";
        level = 1;
        healthPoints = 100;
        manaPoints = 100;
        strength = 10;
        speed = 20;
    }
}
