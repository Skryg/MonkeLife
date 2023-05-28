package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.PlayerComponent;
import com.poproject.game.etcs.components.PlayerWeaponComponent;
public class PlayerAttackSystem  extends IteratingSystem {
    public PlayerAttackSystem() {
        super(Family.all(PlayerComponent.class, PlayerWeaponComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT))return;

        final PlayerWeaponComponent pwc = GameEngine.PlayerWeaponComponentMapper.get(entity);
        if(pwc == null || !pwc.readyToAttack())return;
        attack(pwc);
    }

    private void attack(PlayerWeaponComponent pwc){
        if(pwc.isProjectile()){
            //spawn projectile, with parameters based on mouse world position

        }
    }
}
