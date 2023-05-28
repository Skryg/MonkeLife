package com.poproject.game.etcs.components;

public class EnemyComponent extends Damageable {
    public EnemyComponent(String name, int hp, int strength, int speed) {
        super(name, hp, strength, speed, false);
    }
}
