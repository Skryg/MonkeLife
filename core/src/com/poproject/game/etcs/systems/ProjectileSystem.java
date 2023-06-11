package com.poproject.game.etcs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.poproject.game.etcs.components.BodyComponent;
import com.poproject.game.etcs.components.ProjectileComponent;

public class ProjectileSystem extends IteratingSystem {
    ProjectileSystem(){
        super(Family.all(ProjectileComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent bodyComponent = entity.getComponent(BodyComponent.class);
        ProjectileComponent projectileComponent = entity.getComponent(ProjectileComponent.class);


    }
}
