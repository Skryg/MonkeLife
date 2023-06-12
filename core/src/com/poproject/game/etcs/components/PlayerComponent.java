package com.poproject.game.etcs.components;

public class PlayerComponent extends Damageable {
    public int level;
    public int xp;
    public int manaPoints;

    public PlayerComponent() {
        super("", 100, 10, true);
        level = 1;
        xp = 0;
        manaPoints = 100;
    }
}
