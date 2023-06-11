package com.poproject.game.etcs.components;

import com.badlogic.gdx.physics.box2d.Body;

public class EnemyComponent extends Damageable {
    public Body focusBody;
    public EnemyComponent() {
        super("", 30, 10, true);
    }
}
