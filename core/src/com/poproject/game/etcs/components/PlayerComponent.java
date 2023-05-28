package com.poproject.game.etcs.components;

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
    }

    public void levelUp(){
        while(xp >= level*XP_MULTIPLY){
            xp -= level*XP_MULTIPLY;
            ++level;
        }
    }

    public void getXP(int xp){
        this.xp += xp;
    }
}
