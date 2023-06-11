package com.poproject.game.etcs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PlayerWeaponComponent implements Component, Pool.Poolable {
    @Override
    public void reset() {}
    public boolean readyToAttack(){
        return true;
    }
    public boolean isProjectile(){
        return true;
    }
}
