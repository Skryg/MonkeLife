package com.poproject.game.etcs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public abstract class Damageable implements Component, Pool.Poolable{
    public String name;
    public int healthPoints;
    public int strength;
    public boolean isFriendly;
    public boolean isAlive = true;

    protected Damageable(String name, int hp, int strength, boolean friendly){
        this.name = name;
        this.healthPoints = hp;
        this.strength = strength;
        this.isFriendly = friendly;
    }

    public void getDamage(int damage){
        healthPoints -= damage;
        if(healthPoints < 0){
            isAlive = false;
        }
    }
    public void reset(){

    }
}
