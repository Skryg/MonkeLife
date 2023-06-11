package com.poproject.game.etcs.components;

import com.poproject.game.GUI.GUI;

import static java.lang.Math.max;

public class PlayerComponent extends Damageable {
    public final int XP_MULTIPLY = 100;
    public int level;
    public int xp;
    public int manaPoints;

    public PlayerComponent() {
        super("", 100, 10, 20, true);
        level = 1;
        xp = 0;
        manaPoints = 100;
        GUI.setHP(healthPoints);
    }

    public void levelUp(){
        while(xp >= level*XP_MULTIPLY){
            xp -= level*XP_MULTIPLY;
            ++level;
        }
    }

    @Override
    public void getDamage(int damage){
        super.getDamage(damage);
        GUI.setHP(max(healthPoints,0));
    }

    public void getXP(int xp){
        this.xp += xp;
    }
}
