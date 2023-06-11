package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.poproject.game.etcs.EntityFactory;
import com.poproject.game.etcs.GameEngine;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.PlayerComponent;
import com.poproject.game.etcs.components.PlayerWeaponComponent;
public class PlayerAttackSystem  extends IteratingSystem {
    private final Camera camera;
    public PlayerAttackSystem(Camera camera) {
        super(Family.all(PlayerComponent.class, PlayerWeaponComponent.class).get());
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT))return;

        final PlayerWeaponComponent pwc = GameEngine.playerWeaponComponentMapper.get(entity);
        if(pwc == null || !pwc.readyToAttack())return;
        attack(pwc, entity.getComponent(BodyComponent.class).body.getPosition());
    }

    private void attack(PlayerWeaponComponent pwc, Vector2 playerPosition){
        if(pwc.isProjectile()){
            //spawn projectile, with parameters based on mouse world position
            EntityFactory.getInstance().createProjectileEntity(worldMousePosition().x, worldMousePosition().y, playerPosition);
        }
    }

    private Vector2 worldMousePosition(){
        Vector3 ans = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return new Vector2(ans.x, ans.y);
    }
}
