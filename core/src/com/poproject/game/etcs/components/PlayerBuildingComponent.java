package com.poproject.game.etcs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PlayerBuildingComponent implements Pool.Poolable, Component {
    private float timePassed = 0f;
    private final float rechargeTime = 1f;
    @Override
    public void reset() {
        timePassed = 0f;
    }
    public boolean readyToBuild(float deltaTime){
        timePassed += deltaTime;
        return timePassed >= rechargeTime;
    }
    public boolean readyToBuild(){
        return timePassed >= rechargeTime;
    }
    public void build(){
        timePassed = 0f;
    }
}
