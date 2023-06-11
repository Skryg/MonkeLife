package com.poproject.game.etcs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public abstract class Damageable implements Component, Pool.Poolable{
    public final float maxHealth;
    public final int maxStrength;
    public String name;
    public float healthPoints;
    public int strength;
    public boolean isFriendly;
    public boolean isAlive = true;

    protected Damageable(String name, int hp, int strength, boolean friendly){
        this.name = name;
        this.maxHealth = hp;
        this.maxStrength = strength;
        this.isFriendly = friendly;

        this.healthPoints = hp;
        this.strength = strength;
    }

    public void getDamage(float damage){
        healthPoints -= damage;
        if(healthPoints < 0){
            isAlive = false;
        }
    }
    public void reset(){
        isAlive = true;
        healthPoints = maxHealth;
        strength = maxStrength;
    }
}
