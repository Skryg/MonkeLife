package com.poproject.game.etcs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PlayerWeaponComponent implements Component, Pool.Poolable {
    private float timePassed = 0f;
    private final float rechargeTime = 0.420f;
    @Override
    public void reset() {
        timePassed = 0f;
    }
    public boolean readyToAttack(float deltaTime){
        timePassed += deltaTime;
        return timePassed >= rechargeTime;
    }
    public boolean readyToAttack(){
        return true;
    }
    public boolean isProjectile(){
        return true;
    }
    public void fire(){
        timePassed = 0f;
    }
}
